package com.ali.domain.activity.service.trial.node;

import com.ali.domain.activity.model.entity.MarketProductEntity;
import com.ali.domain.activity.model.entity.TrialBalanceEntity;
import com.ali.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.ali.domain.activity.model.valobj.SkuVO;
import com.ali.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.ali.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.ali.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountVOThreadTask;
import com.ali.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import com.ali.types.design.framework.tree.StrategyHandler;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private EndNode endNode;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 异步查询活动配置
        QueryGroupBuyActivityDiscountVOThreadTask queryGroupBuyActivityDiscountVOThreadTask = new QueryGroupBuyActivityDiscountVOThreadTask(requestParameter.getSource(), requestParameter.getChannel(), repository);
        FutureTask<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOFutureTask = new FutureTask<>(queryGroupBuyActivityDiscountVOThreadTask);
        threadPoolExecutor.execute(groupBuyActivityDiscountVOFutureTask);

        // 异步查询商品信息 - 在实际生产中，商品有同步库或者调用接口查询。这里暂时使用DB方式查询。
        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask = new QuerySkuVOFromDBThreadTask(requestParameter.getGoodsId(), repository);
        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        threadPoolExecutor.execute(skuVOFutureTask);

        try {
            // 获取异步结果并写入上下文 - 对于一些复杂场景，获取数据的操作，有时候会在下N个节点获取，这样前置查询数据，可以提高接口响应效率
            GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = groupBuyActivityDiscountVOFutureTask.get(timeout, TimeUnit.MINUTES);
            SkuVO skuVO = skuVOFutureTask.get(timeout, TimeUnit.MINUTES);
            
            // 添加空值检查
            dynamicContext.setGroupBuyActivityDiscountVO(groupBuyActivityDiscountVO);
            dynamicContext.setSkuVO(skuVO);
            
            log.info("拼团商品查询试算服务-MarketNode userId:{} 异步线程加载数据「GroupBuyActivityDiscountVO、SkuVO」完成", requestParameter.getUserId());
        } catch (Exception e) {
            log.error("拼团商品查询试算服务-MarketNode 获取异步结果失败 userId:{} error:{}", requestParameter.getUserId(), e.getMessage(), e);
            // 即使获取数据失败，也继续流程，避免整个请求失败
        }
    }

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-MarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // todo lsp 拼团优惠试算

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }

}

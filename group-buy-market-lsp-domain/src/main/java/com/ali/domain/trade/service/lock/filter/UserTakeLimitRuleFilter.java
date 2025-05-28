package com.ali.domain.trade.service.lock.filter;


import com.ali.domain.trade.adapter.repository.ITradeRepository;
import com.ali.domain.trade.model.entity.GroupBuyActivityEntity;
import com.ali.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.ali.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.ali.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.ali.types.design.framework.link.model2.handler.ILogicHandler;
import com.ali.types.enums.ResponseCode;
import com.ali.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserTakeLimitRuleFilter implements ILogicHandler<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> {


    @Resource
    private ITradeRepository repository;

    public TradeLockRuleFilterBackEntity apply(TradeLockRuleCommandEntity requestParameter, TradeLockRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-用户参与次数校验{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());

        GroupBuyActivityEntity groupBuyActivity = dynamicContext.getGroupBuyActivity();

        // 查询用户在一个拼团活动上参与的次数
        Integer count = repository.queryOrderCountByActivityId(requestParameter.getActivityId(), requestParameter.getUserId());

        if (null != groupBuyActivity.getTakeLimitCount() && count >= groupBuyActivity.getTakeLimitCount()) {
            log.info("用户参与次数校验，已达可参与上限 activityId:{}", requestParameter.getActivityId());
            throw new AppException(ResponseCode.E0103);
        }

        return TradeLockRuleFilterBackEntity.builder()
                .userTakeOrderCount(count)
                .build();
    }
}

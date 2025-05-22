package com.ali.test.domain.activity;


import com.ali.domain.activity.adapter.repository.IActivityRepository;
import com.ali.domain.activity.model.entity.MarketProductEntity;
import com.ali.domain.activity.model.entity.TrialBalanceEntity;
import com.ali.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.ali.domain.activity.model.valobj.SkuVO;
import com.ali.domain.activity.service.IIndexGroupBuyMarketService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest

public class IIndexGroupBuyMarketServiceTest {
    @Resource
    private IIndexGroupBuyMarketService indexGroupBuyMarketService;
    @Resource
    private IActivityRepository repository;

    @Test
    public void test_indexMarketTrial() throws Exception {
        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("xiaofuge2");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890001");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(marketProductEntity);
        log.info("请求参数:{}", JSON.toJSONString(marketProductEntity));
        log.info("返回结果:{}", JSON.toJSONString(trialBalanceEntity));
    }
    @Test
    public void test_querySkuByGoodsId() {
        String goodsId = "9890001";
        SkuVO skuVO = repository.querySkuByGoodsId(goodsId);
        log.info("查询结果: {}", JSON.toJSONString(skuVO));
        Assert.assertNotNull(skuVO);
    }
    @Test
    public void test_indexMarketTrial_error() throws Exception {
        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("xiaofuge");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890002");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(marketProductEntity);
        log.info("请求参数:{}", JSON.toJSONString(marketProductEntity));
        log.info("返回结果:{}", JSON.toJSONString(trialBalanceEntity));
    }
//    @Test
//    public void test_queryGroupBuyActivityDiscountVO() {
//        // 准备测试数据
//        String source = "s01";
//        String channel = "c01";
//
//        // 执行测试
//        log.info("查询参数: source={}, channel={}", source, channel);
//        GroupBuyActivityDiscountVO result = repository.queryGroupBuyActivityDiscountVO(source, channel);
//        // 详细断言
//        log.info("查询结果: {}", JSON.toJSONString(result));
//        Assert.assertNotNull(result);
//        // 添加更多断言验证返回对象的具体属性
//        // Assert.assertEquals(expectedValue, result.getSomeProperty());
//    }




}

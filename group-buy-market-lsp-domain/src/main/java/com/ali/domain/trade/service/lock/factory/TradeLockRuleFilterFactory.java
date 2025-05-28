package com.ali.domain.trade.service.lock.factory;


import com.ali.domain.trade.model.entity.GroupBuyActivityEntity;
import com.ali.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.ali.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.ali.domain.trade.service.lock.filter.ActivityUsabilityRuleFilter;
import com.ali.domain.trade.service.lock.filter.UserTakeLimitRuleFilter;
import com.ali.types.design.framework.link.model2.LinkArmory;
import com.ali.types.design.framework.link.model2.chain.BusinessLinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TradeLockRuleFilterFactory {


    @Bean("tradeRuleFilter")
    public BusinessLinkedList<TradeLockRuleCommandEntity, DynamicContext, TradeLockRuleFilterBackEntity> tradeRuleFilter(ActivityUsabilityRuleFilter activityUsabilityRuleFilter, UserTakeLimitRuleFilter userTakeLimitRuleFilter) {
        // 组装链
        LinkArmory<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> linkArmory =
                new LinkArmory<>("交易规则过滤链", activityUsabilityRuleFilter, userTakeLimitRuleFilter);

        // 链对象
        return linkArmory.getLogicLink();
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {

        private GroupBuyActivityEntity groupBuyActivity;

    }
}

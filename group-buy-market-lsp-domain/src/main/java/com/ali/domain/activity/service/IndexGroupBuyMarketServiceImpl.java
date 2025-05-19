package com.ali.domain.activity.service;

import com.ali.domain.activity.model.entity.MarketProductEntity;
import com.ali.domain.activity.model.entity.TrialBalanceEntity;
import com.ali.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.ali.types.design.framework.tree.StrategyHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexGroupBuyMarketServiceImpl implements IIndexGroupBuyMarketService {

    @Resource
    private DefaultActivityStrategyFactory defaultActivityStrategyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception {

        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler = defaultActivityStrategyFactory.strategyHandler();

        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProductEntity, new DefaultActivityStrategyFactory.DynamicContext());

        return trialBalanceEntity;
    }

}

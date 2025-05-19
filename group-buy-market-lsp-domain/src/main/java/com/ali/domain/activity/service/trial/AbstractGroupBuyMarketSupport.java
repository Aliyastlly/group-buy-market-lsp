package com.ali.domain.activity.service.trial;

import com.ali.domain.activity.adapter.repository.IActivityRepository;
import com.ali.domain.activity.model.entity.MarketProductEntity;
import com.ali.domain.activity.model.entity.TrialBalanceEntity;
import com.ali.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.ali.types.design.framework.tree.AbstractMultiThreadStrategyRouter;
import com.ali.types.design.framework.tree.AbstractStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<com.ali.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, com.ali.domain.activity.model.entity.TrialBalanceEntity> {
    public long timeout = 1000;
    @Resource
    protected IActivityRepository repository;
    @Override
    protected void multiThread(com.ali.domain.activity.model.entity.MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {

    }
}


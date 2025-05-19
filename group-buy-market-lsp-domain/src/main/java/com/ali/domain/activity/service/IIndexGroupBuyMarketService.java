package com.ali.domain.activity.service;

import com.ali.domain.activity.model.entity.MarketProductEntity;
import com.ali.domain.activity.model.entity.TrialBalanceEntity;

public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

}

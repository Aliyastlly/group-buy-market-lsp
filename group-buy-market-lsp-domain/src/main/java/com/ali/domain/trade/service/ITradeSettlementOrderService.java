package com.ali.domain.trade.service;

import com.ali.domain.trade.model.entity.TradePaySettlementEntity;
import com.ali.domain.trade.model.entity.TradePaySuccessEntity;

import java.util.Map;

public interface ITradeSettlementOrderService {

    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception;

    Map<String, Integer> execSettlementNotifyJob() throws Exception;

    Map<String, Integer> execSettlementNotifyJob(String teamId) throws Exception;
}

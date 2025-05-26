package com.ali.domain.trade.service;

import com.ali.domain.trade.model.entity.TradePaySettlementEntity;
import com.ali.domain.trade.model.entity.TradePaySuccessEntity;

public interface ITradeSettlementOrderService {

    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity);
}

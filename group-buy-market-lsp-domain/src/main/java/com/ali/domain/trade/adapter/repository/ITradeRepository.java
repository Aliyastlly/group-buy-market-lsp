package com.ali.domain.trade.adapter.repository;

import com.ali.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.ali.domain.trade.model.entity.GroupBuyActivityEntity;
import com.ali.domain.trade.model.entity.MarketPayOrderEntity;
import com.ali.domain.trade.model.valobj.GroupBuyProgressVO;

public interface ITradeRepository {

    MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    Integer queryOrderCountByActivityId(Long activityId, String userId);

    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);
}

package com.ali.domain.activity.service;

import com.ali.domain.activity.model.entity.MarketProductEntity;
import com.ali.domain.activity.model.entity.TrialBalanceEntity;
import com.ali.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.ali.domain.activity.model.valobj.TeamStatisticVO;

import java.util.List;

public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailList(Long activityId, String userId, Integer ownerCount, Integer randomCount);

    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);

}

package com.ali.domain.activity.adapter.repository;

import com.ali.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.ali.domain.activity.model.valobj.SCSkuActivityVO;
import com.ali.domain.activity.model.valobj.SkuVO;

public interface IActivityRepository {
    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);


    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);

    boolean isTagCrowRange(String tagId, String userId);
}

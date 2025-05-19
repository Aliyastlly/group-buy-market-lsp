package com.ali.domain.activity.adapter.repository;

import com.ali.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.ali.domain.activity.model.valobj.SkuVO;

public interface IActivityRepository {
    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);


}

package com.ali.domain.activity.service.discount;

import com.ali.domain.activity.model.valobj.DiscountTypeEnum;
import com.ali.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService{
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        // 1. 人群标签过滤
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
            boolean isCrowdRange = filterTagId(userId, groupBuyDiscount.getTagId());
            if (!isCrowdRange) return originalPrice;
        }
        // 2. 折扣优惠计算
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice,
                                   GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

    private boolean filterTagId(String userId, String tagId) {
        // todo lsp 后续开发这部分
        return true;
    }
}

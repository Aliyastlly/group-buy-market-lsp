package com.ali.domain.activity.service.discount;

import com.ali.domain.activity.adapter.repository.IActivityRepository;
import com.ali.domain.activity.model.valobj.DiscountTypeEnum;
import com.ali.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.math.BigDecimal;
@Slf4j
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService{

    @Resource
    protected IActivityRepository repository;

    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        // 1. 人群标签过滤
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
            boolean isCrowdRange = filterTagId(userId, groupBuyDiscount.getTagId());
            if (!isCrowdRange) {
                log.info("折扣优惠计算拦截 userID:{}",userId);
                return originalPrice;
            }
        }
        // 2. 折扣优惠计算
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice,
                                   GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

    private boolean filterTagId(String userId, String tagId) {
        // todo lsp 后续开发这部分
        return repository.isTagCrowdRange(tagId, userId);
    }
}

package com.ali.domain.activity.service.discount;

import com.ali.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

public interface IDiscountCalculateService {

    BigDecimal calculate(String userId, BigDecimal originalPrice,
                         GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}

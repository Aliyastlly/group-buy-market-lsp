package com.ali.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 折扣表 (group_buy_discount) 对应的 Java 类
 */
public class GroupBuyDiscount {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 折扣ID
     * 注意：SQL定义是 INT(8)，与 group_buy_activity 表中的 VARCHAR(8) 类型不一致，请检查是否需要统一
     */
    private Integer discountId;

    /**
     * 折扣标题
     */
    private String discountName;

    /**
     * 折扣描述
     */
    private String discountDesc;

    /**
     * 折扣类型（0:base、1:tag）
     */
    private Integer discountType;

    /**
     * 营销优惠计划（ZJ:直减、MJ:满减、N元购）
     */
    private String marketPlan;

    /**
     * 营销优惠表达式
     */
    private String marketExpr;

    /**
     * 人群标签，特定优惠限定
     */
    private String tagId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

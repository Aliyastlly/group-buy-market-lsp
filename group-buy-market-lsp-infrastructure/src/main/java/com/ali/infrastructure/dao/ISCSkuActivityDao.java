package com.ali.infrastructure.dao;

import com.ali.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISCSkuActivityDao {
    SCSkuActivity querySCSkuActivityBySCGoodsId(SCSkuActivity scSkuActivity);
}

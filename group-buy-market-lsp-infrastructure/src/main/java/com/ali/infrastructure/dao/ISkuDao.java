package com.ali.infrastructure.dao;
import com.ali.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ISkuDao {

    Sku querySkuByGoodsId(String goodsId);

}

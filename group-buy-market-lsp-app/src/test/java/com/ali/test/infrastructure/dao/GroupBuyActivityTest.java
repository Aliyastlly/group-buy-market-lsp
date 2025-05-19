package com.ali.test.infrastructure.dao;

import com.ali.infrastructure.dao.IGroupBuyActivityDao;
import com.ali.infrastructure.dao.IGroupBuyDiscountDao;
import com.ali.infrastructure.dao.po.GroupBuyDiscount;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupBuyActivityTest {
    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Test
    public void test() {
        log.info("测试开始");
        groupBuyActivityDao.queryGroupBuyActivityList().forEach(System.out::println);
        log.info("测试结束");
    }
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;

    @Test
    public void test_queryGroupBuyDiscountList(){
        List<GroupBuyDiscount> groupBuyDiscounts = groupBuyDiscountDao.queryGroupBuyDiscountList();
        log.info("测试结果:{}", JSON.toJSONString(groupBuyDiscounts));
    }
}

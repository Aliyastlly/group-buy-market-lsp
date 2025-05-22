package com.ali.test.domain.tag;

import com.ali.domain.tag.service.TagService;
import com.ali.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITagServiceTest {

    @Resource
    private TagService tagService;
    @Resource
    private IRedisService redisService;

    @Test
    public void test_tag_job() {
        tagService.execTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
    }

    @Test
    public void test_get_tag_bitmap() {
        RBitSet bitSet = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        // 是否存在
        log.info("xiaofuge 存在，预期结果为 true，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("xiaofuge")));
        log.info("gudebai 不存在，预期结果为 false，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("gudebai")));
    }
    @Test
    public void test_tag_job_with_debug() {
        // 1. 执行前获取索引值
        long index = redisService.getIndexFromUserId("xiaofuge");
        log.info("xiaofuge的索引值: {}", index);

        // 2. 执行标签作业
        log.info("执行批处理任务前...");
        tagService.execTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
        log.info("执行批处理任务后...");

        // 3. 增加更长的延迟时间
        try {
            log.info("等待操作完成...");
            Thread.sleep(1000);  // 增加到1秒
        } catch (InterruptedException e) {
            log.error("等待被中断", e);
        }

        // 4. 详细验证
        RBitSet bitSet = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        log.info("获取到的BitSet是否为null: {}", bitSet == null);
        log.info("xiaofuge索引处的值: {}", bitSet.get(index));

        // 尝试手动设置位来验证Redis写入功能
        log.info("尝试手动设置位...");
        bitSet.set(index);
        RBitSet bitSetAfter = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        log.info("手动设置后索引处的值: {}", bitSetAfter.get(index));
    }
    @Test
    public void test_simple_bitset() {
        // 使用全新的Key测试
        String testKey = "TEST_BITSET_" + System.currentTimeMillis();
        RBitSet bitSet = redisService.getBitSet(testKey);

        // 使用小索引值测试基本操作
        int smallIndex = 100;
        log.info("设置索引{}的位值", smallIndex);
        bitSet.set(smallIndex);
        boolean result = bitSet.get(smallIndex);
        log.info("设置后读取索引{}的值: {}", smallIndex, result);
        Assert.assertTrue(result);

        // 测试实际业务索引
        int userIndex = redisService.getIndexFromUserId("xiaofuge");
        log.info("用户索引: {}", userIndex);
        bitSet.set(userIndex);
        boolean userResult = bitSet.get(userIndex);
        log.info("用户索引结果: {}", userResult);
        Assert.assertTrue(userResult);

        // 清理测试数据
        bitSet.delete();
    }

}

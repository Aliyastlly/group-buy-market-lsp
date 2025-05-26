package com.ali.test.domain.tag;

import com.ali.domain.tag.adapter.repository.ITagRepository;
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
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITagServiceTest {
    @Resource
    private ITagRepository repository;
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
        log.info("xfg01 存在，预期结果为 true，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("xfg01")));
        log.info("gudebai 不存在，预期结果为 false，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("gudebai")));
    }
    @Test
    public void test_tag_job_with_debug() {
        // 1. 执行前获取索引值
        long index = redisService.getIndexFromUserId("xfg02");
        log.info("xfg01的索引值: {}", index);

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
        log.info("xfg01索引处的值: {}", bitSet.get(index));

        // 尝试手动设置位来验证Redis写入功能
        log.info("尝试手动设置位...");
        bitSet.set(index);
        RBitSet bitSetAfter = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        log.info("手动设置后索引处的值: {}", bitSetAfter.get(index));
    }

    @Test
    public void test_fix_with_shorter_tag_id() {
        // 使用一个更短的测试ID
        String testTagId = "TEST_" + System.currentTimeMillis() % 10000;

        // 测试单个用户添加
        String userId = "录视频";

        // 添加用户并等待完成
        repository.addCrowdTagsUserId(testTagId, userId);
        log.info("添加用户: {}", userId);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        // 验证
        RBitSet bitSet = redisService.getBitSet(testTagId);
        boolean exists = bitSet.get(redisService.getIndexFromUserId(userId));
        log.info("用户 {} 存在: {}", userId, exists);
        Assert.assertTrue("用户" + userId + "应该存在", exists);

        // 清理测试数据
        bitSet.delete();
    }
}

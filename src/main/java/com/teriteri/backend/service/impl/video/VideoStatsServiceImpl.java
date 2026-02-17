package com.teriteri.backend.service.impl.video;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.teriteri.backend.mapper.VideoStatsMapper;
import com.teriteri.backend.pojo.VideoStats;
import com.teriteri.backend.service.video.VideoStatsService;
import com.teriteri.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class VideoStatsServiceImpl implements VideoStatsService {
    @Autowired
    private VideoStatsMapper videoStatsMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    @Qualifier("taskExecutor")
    private Executor taskExecutor;

    /**
     * 根据视频ID查询视频常变数据

 * 该方法首先尝试从Redis缓存中获取视频统计数据，如果缓存中没有，则从数据库查询，
 * 并异步将查询结果存入Redis缓存中，以提高后续查询效率。
     * @param vid 视频ID，用于唯一标识一个视频
     * @return 视频数据统计
     */
    @Override
    public VideoStats getVideoStatsById(Integer vid) {
    // 尝试从Redis缓存中获取视频统计数据
        VideoStats videoStats = redisUtil.getObject("videoStats:" + vid, VideoStats.class);
    // 如果缓存中没有找到数据
        if (videoStats == null) {
        // 从数据库中查询视频统计数据
            videoStats = videoStatsMapper.selectById(vid);
        // 如果数据库中存在该视频数据
            if (videoStats != null) {
            // 使用final关键字确保变量在lambda表达式中可访问
                VideoStats finalVideoStats = videoStats;
            // 使用CompletableFuture异步执行任务，将查询结果存入Redis缓存
                CompletableFuture.runAsync(() -> {
                    redisUtil.setExObjectValue("videoStats:" + vid, finalVideoStats);    // 异步更新到redis
                }, taskExecutor);
            } else {
            // 如果数据库中也不存在该视频，则返回null
                return null;
            }
        }
        // 多线程查redis反而更慢了，所以干脆直接查数据库
        return videoStats;
    }

    /**
     * 更新指定字段
     * @param vid   视频ID
     * @param column    对应数据库的列名
     * @param increase  是否增加，true则增加 false则减少
     * @param count 增减数量 一般是1，只有投币可以加2
     */
    @Override
    public void updateStats(Integer vid, String column, boolean increase, Integer count) {
    // 创建数据库更新条件包装器
        UpdateWrapper<VideoStats> updateWrapper = new UpdateWrapper<>();
    // 设置更新条件，视频ID匹配
        updateWrapper.eq("vid", vid);
    // 判断是增加还是减少字段值
        if (increase) {
        // 设置SQL语句，增加指定字段的值
            updateWrapper.setSql(column + " = " + column + " + " + count);
        } else {
            // 更新后的字段不能小于0
            updateWrapper.setSql(column + " = CASE WHEN " + column + " - " + count + " < 0 THEN 0 ELSE " + column + " - " + count + " END");
        }
        videoStatsMapper.update(null, updateWrapper);
        redisUtil.delValue("videoStats:" + vid);
    }

    /**
     * 同时更新点赞和点踩
     * @param vid   视频ID
     * @param addGood   是否点赞，true则good+1&bad-1，false则good-1&bad+1
     */
    @Override
    public void updateGoodAndBad(Integer vid, boolean addGood) {
    // 创建更新包装器，用于构建更新条件
        UpdateWrapper<VideoStats> updateWrapper = new UpdateWrapper<>();
    // 设置更新条件：视频ID等于传入的vid
        updateWrapper.eq("vid", vid);
    // 判断是点赞还是点踩操作
        if (addGood) {
        // 点赞操作：点赞数加1
            updateWrapper.setSql("good = good + 1");
        // 点赞操作：点踩数减1，但最小值为0
            updateWrapper.setSql("bad = CASE WHEN bad - 1 < 0 THEN 0 ELSE bad - 1 END");
        } else {
        // 点踩操作：点踩数加1
            updateWrapper.setSql("bad = bad + 1");
        // 点踩操作：点赞数减1，但最小值为0
            updateWrapper.setSql("good = CASE WHEN good - 1 < 0 THEN 0 ELSE good - 1 END");
        }
    // 执行更新操作，第一个参数为null表示不更新实体类属性，只执行SQL更新
        videoStatsMapper.update(null, updateWrapper);
    // 删除对应的Redis缓存，确保数据一致性
        redisUtil.delValue("videoStats:" + vid);
    }
}

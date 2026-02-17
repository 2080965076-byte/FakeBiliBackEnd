package com.teriteri.backend.controller;


import com.teriteri.backend.pojo.Result;
import com.teriteri.backend.service.video.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


/**
 * 历史记录控制器类
 * 处理用户视频观看历史的增删查操作
 * 使用缓存技术提高查询效率
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class HistoryController {
    // 自动注入历史记录服务
    @Autowired
    private HistoryService historyService;


    /**
     * 添加用户观看历史记录
     * @param userId 用户ID
     * @param videoId 视频ID
     * @return 返回操作结果
     * 使用缓存清除，确保最新数据
     */
    @CacheEvict(value = "history", key = "#userId")
    @GetMapping("/video/{userId}/{videoId}")
    public Result addHistory(@PathVariable Long userId, @PathVariable Long videoId) {
        log.info("用户{}观看了视频{}", userId, videoId);
        historyService.addHistory(userId, videoId);
        return Result.success();
    }

    /**
     * 获取用户观看历史记录
     * @param userId 用户ID
     * @return 返回用户的观看历史记录列表
     * 使用缓存提高查询性能
     */
    @Cacheable(value = "history", key = "#userId")
    @GetMapping("/{userId}")
    public Result getHistory(@PathVariable Long userId) {
        log.info("获取用户{}的观看历史", userId);
        return Result.success(historyService.getHistory(userId));
    }



    /**
     * 删除用户全部观看历史记录
     * @param userId 用户ID
     * @return 返回操作结果
     * 使用缓存清除，确保数据一致性
     */
    @CacheEvict(value = "history", key = "#userId")
    @DeleteMapping("/delAll/{userId}")
    public Result deleteAllHistory(@PathVariable Long userId) {
        log.info("删除用户{}的全部观看历史", userId);
        historyService.deleteAllHistory(userId);
        return Result.success();
    }

    /**
     * 删除指定历史记录
     * @param id 历史记录ID
     * @return 返回操作结果
     * 使用缓存清除，确保数据一致性
     */
    @CacheEvict(value = "history", allEntries = true)
    @DeleteMapping("/del/{id}")
    public Result deleteHistory(@PathVariable Long id) {
        log.info("删除观看历史{}", id);
        historyService.deleteHistory(id);
        return Result.success();
    }
}
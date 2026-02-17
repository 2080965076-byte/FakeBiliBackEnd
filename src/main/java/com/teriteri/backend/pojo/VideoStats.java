package com.teriteri.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VideoStats类用于存储视频统计数据信息
 * 使用@Data注解自动生成getter、setter等方法
 * 使用@AllArgsConstructor注解自动生成全参数构造方法
 * 使用@NoArgsConstructor注解自动生成无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoStats {
    /**
     * 视频ID，作为数据库表的主键
     */
    @TableId
    private Integer vid;
    /**
     * 视频播放次数
     */
    private Integer play;
    /**
     * 视频弹幕数量
     */
    private Integer danmu;
    /**
     * 视频点赞数
     */
    private Integer good;
    /**
     * 视频点踩数
     */
    private Integer bad;
    /**
     * 视频投币数
     */
    private Integer coin;
    /**
     * 视频收藏数
     */
    private Integer collect;
    /**
     * 视频分享数
     */
    private Integer share;
    /**
     * 视频评论数
     */
    private Integer comment;
}

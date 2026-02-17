package com.teriteri.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer vid;          // 视频ID
    private Integer uid;          // 投稿用户ID
    private String nickname;      // 用户昵称
    private String title;         // 标题
    private Byte type;            // 类型 1自制 2转载
    private Byte auth;            // 作者声明 0不声明 1未经允许禁止转载
    private Double duration;      // 播放总时长 单位秒
    private String descr;         // 简介
    private String coverUrl;      // 封面url
    private String videoUrl;      // 视频url
    private String mcId;          // 主分区ID
    private String scId;          // 子分区ID
    private String tags;          // 标签 回车分隔
    private Integer play;         // 播放量
    private Integer good;         // 点赞数
    private Integer bad;          // 点踩数
    private Integer coin;         // 投币数
    private Integer collect;      // 收藏数
    private Integer share;        // 分享数
    private Integer comment;      // 评论数量统计
    private Integer fid;          // 收藏夹ID
    private Byte status;          // 状态 0审核中 1已过审 2未通过 3已删除
    private Date uploadDate;      // 上传时间
    private Date deleteDate;      // 删除时间
}

package com.teriteri.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论实体类
 * 使用@Data注解自动生成getter、setter等方法
 * 使用@AllArgsConstructor注解生成全参数构造方法
 * 使用@NoArgsConstructor注解生成无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    /**
     * 评论ID，主键，自增
     * 使用@TableId注解标记为主键，并设置自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 视频ID，关联视频表的主键 */
    private Integer vid;
    /** 用户ID，关联用户表的主键 */
    private Integer uid;
    /** 根评论ID，用于评论的层级关系 */
    private Integer rootId;
    /** 父评论ID，用于回复评论时的关联 */
    private Integer parentId;
    /** 被回复的用户ID */
    private Integer toUserId;
    /** 评论内容 */
    private String content;
    /** 点赞数量 */
    private Integer love;
    /** 踩的数量 */
    private Integer bad;
    /** 创建时间 */
    private Date createTime;
    /** 是否置顶，0-否，1-是 */
    private Integer isTop;
    /** 是否删除，0-否，1-是 */
    private Integer isDeleted;
}

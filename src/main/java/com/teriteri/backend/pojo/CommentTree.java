package com.teriteri.backend.pojo;

import com.teriteri.backend.pojo.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 评论树实体类
 * 用于存储评论及其回复的层级结构信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentTree {
    // 评论ID
    private Integer id;
    // 视频/内容ID
    private Integer vid;
    // 根评论ID
    private Integer rootId;
    // 父评论ID
    private Integer parentId;
    // 评论内容
    private String content;
    // 评论用户信息
    private UserDTO user;
    // 被回复用户信息
    private UserDTO toUser;
    // 点赞数
    private Integer love;
    // 点踩数
    private Integer bad;
    // 回复列表，存储子评论
    private List<CommentTree> replies;
    // 创建时间
    private Date createTime;
    // 计数相关数据
    private Long count;
}

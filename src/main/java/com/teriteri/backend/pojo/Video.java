package com.teriteri.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Video类，表示视频实体类
 * 使用了Lombok库的@Data、@AllArgsConstructor和@NoArgsConstructor注解，
 * 自动生成getter/setter、构造函数和toString等方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    /**
     * 视频ID，为主键，使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer vid;
    /**
     * 用户ID，关联到发布视频的用户
     */
    private Integer uid;
    /**
     * 视频标题
     */
    private String title;
    /**
     * 视频类型
     */
    private Integer type;
    /**
     * 视频权限设置
     */
    private Integer auth;
    /**
     * 视频时长，单位为秒
     */
    private Double duration;
    /**
     * 主分类ID
     */
    private String mcId;
    /**
     * 子分类ID
     */
    private String scId;
    /**
     * 视频标签，多个标签可以用逗号分隔
     */
    private String tags;
    /**
     * 视频描述
     */
    private String descr;
    /**
     * 视频封面图片URL
     */
    private String coverUrl;
    /**
     * 视频文件URL
     */
    private String videoUrl;
    /**
     * 视频状态
     * 0-审核中
     * 1-通过审核
     * 2-打回整改（指投稿信息不符）
     * 3-视频违规删除（视频内容违规）
     */
    private Integer status;     // 0审核中 1通过审核 2打回整改（指投稿信息不符） 3视频违规删除（视频内容违规）
    /**
     * 视频上传时间
     * 使用@JsonFormat注解指定日期格式和时区
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date uploadDate;
    /**
     * 视频删除时间
     * 使用@JsonFormat注解指定日期格式和时区
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date deleteDate;
}

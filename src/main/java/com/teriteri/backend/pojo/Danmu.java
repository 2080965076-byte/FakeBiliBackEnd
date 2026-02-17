package com.teriteri.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 弹幕实体类
 * 使用Lombok注解自动生成getter、setter、toString等方法
 * 包含全参构造方法和无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Danmu {
    /**
     * 弹幕ID，使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer id;     // 弹幕ID
    /**
     * 关联的视频ID
     */
    private Integer vid;    // 视频ID
    /**
     * 发送弹幕的用户ID
     */
    private Integer uid;    // 用户ID
    /**
     * 弹幕的具体文本内容
     */
    private String content; // 弹幕内容
    /**
     * 弹幕显示的字体大小，默认为25，小于18为小字体
     */
    private Integer fontsize;   // 字体大小 默认25 小18
    /**
     * 弹幕的显示模式
     * 1: 滚动弹幕
     * 2: 顶部弹幕
     * 3: 底部弹幕
     */
    private Integer mode;   // 模式 1滚动 2顶部 3底部
    /**
     * 弹幕的字体颜色，使用6位十六进制标准格式，如#FFFFFF
     */
    private String color;   // 字体颜色 6位十六进制标准格式 #FFFFFF
    /**
     * 弹幕在视频中出现的时间点，单位为秒
     */
    private Double timePoint;   // 弹幕在视频中的时间点位置（秒）
    /**
     * 弹幕的状态标识
     * 1: 默认过审状态
     * 2: 被举报审核中状态
     * 3: 已删除状态
     */
    private Integer state;  // 弹幕状态 1默认过审 2被举报审核中 3删除
    // 注释掉的日期格式化配置
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    /**
     * 弹幕的发送时间，精确到日期
     */
    private Date createDate;    // 弹幕发送日期时间
}

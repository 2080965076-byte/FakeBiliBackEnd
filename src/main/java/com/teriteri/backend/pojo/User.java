package com.teriteri.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户实体类
 * 使用@Data注解自动生成getter、setter等方法
 * 使用@AllArgsConstructor注解生成全参数构造方法
 * 使用@NoArgsConstructor注解生成无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    /**
     * 用户ID，使用@TableId注解标记为主键，并设置为自增类型
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像URL
     */
    private String avatar;
    /**
     * 背景图URL
     */
    private String background;
    /**
     * 性别
     * 0: 女性
     * 1: 男性
     * 2: 无性别
     * 默认值为2
     */
    private Integer gender; // 性别，0女性 1男性 2无性别，默认2
    /**
     * 用户描述/个人简介
     */
    private String description;
    /**
     * 用户经验值
     * 等级区间：
     * 0级: 0~50
     * 1级: 50~200
     * 2级: 200~1500
     * 3级: 1500~4500
     * 4级: 4500~10800
     * 5级: 10800~28800
     */
    private Integer exp;    // 经验值 50/200/1500/4500/10800/28800 分别是0~6级的区间
    /**
     * 用户硬币数
     * 保留一位小数
     */
    private Double coin;    // 硬币数 保留一位小数
    /**
     * VIP会员等级
     * 0: 普通用户
     * 1: 月度大会员
     * 2: 季度大会员
     * 3: 年度大会员
     */
    private Integer vip;    // 0 普通用户，1 月度大会员，2 季度大会员，3 年度大会员
    /**
     * 用户状态
     * 0: 正常
     * 1: 封禁中
     * 2: 已注销
     */
    private Integer state;  // 0 正常，1 封禁中，2 已注销
    /**
     * 用户角色
     * 0: 普通用户
     * 1: 普通管理员
     * 2: 超级管理员
     */
    private Integer role;   // 0 普通用户，1 普通管理员，2 超级管理员
    /**
     * 认证状态
     * 0: 普通用户
     * 1: 个人认证
     * 2: 机构认证
     */
    private Integer auth;   // 0 普通用户，1 个人认证，2 机构认证
    /**
     * 认证信息
     * 例如: teriteri官方账号
     */
    private String authMsg; // 认证信息，如 teriteri官方账号
    /**
     * 关注数
     */
    private Integer followingCount;//关注数
    /**
     * 粉丝数
     */
    private Integer followersCount;//粉丝数
    /**
     * 创建时间
     * 注释掉的@JsonFormat注解用于指定日期格式和时区
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date createDate;
    /**
     * 删除时间
     * 注释掉的@JsonFormat注解用于指定日期格式和时区
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date deleteDate;
}

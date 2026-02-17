package com.teriteri.backend.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户数据传输对象(Data Transfer Object)
 * 用于在系统各层之间传输用户相关数据
 */
@Data              // Lombok注解，自动生成getter、setter、toString等方法
@AllArgsConstructor // Lombok注解，自动生成全参数构造方法
@NoArgsConstructor  // Lombok注解，自动生成无参构造方法
public class UserDTO {
    private Integer uid;            // 用户唯一标识ID
    private String nickname;         // 用户昵称
    private String avatar_url;       // 用户头像URL地址
    private String bg_url;           // 用户背景图片URL地址
    private Integer gender; // 性别，0女性 1男性 2无性别，默认2
    private String description;      // 用户个人描述/签名
    private Integer exp;    // 经验值 50/200/1500/4500/10800/28800 分别是0~6级的区间
    private Double coin;    // 硬币数  保留一位小数
    private Integer vip;    // 0 普通用户，1 月度大会员，2 季度大会员，3 年度大会员
    private Integer state;  // 0 正常，1 封禁中
    private Integer auth;   // 0 普通用户，1 个人认证，2 机构认证
    private String authMsg; // 认证信息，如 teriteri官方账号
    private Integer videoCount; // 视频投稿数
    private Integer followsCount;   // 关注数
    private Integer fansCount;  // 粉丝数
    private Integer loveCount;  // 获赞数
    private Integer playCount;  // 播放数
}

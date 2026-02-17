package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 热搜实体类
 * 用于存储热搜榜的相关信息
 * 使用@Data注解自动生成getter/setter等方法
 * 使用@AllArgsConstructor和@NoArgsConstructor注解自动生成全参和无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotSearch {
    private String content; // 内容
    private Double score;   // 热度
    private Integer type = 0;   // 类型： 0 普通 1 新 2 热
}

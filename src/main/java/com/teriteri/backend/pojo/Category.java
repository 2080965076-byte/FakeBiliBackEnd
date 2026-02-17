package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Category类，用于存储商品分类信息
 * 使用Lombok的@Data注解自动生成getter、setter、toString等方法
 * 使用@AllArgsConstructor和@NoArgsConstructor注解自动生成全参构造方法和无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    // 主分类ID
    private String mcId;
    // 子分类ID
    private String scId;
    // 主分类名称
    private String mcName;
    // 子分类名称
    private String scName;
    // 分类描述
    private String descr;
    // 推荐标签
    private String rcmTag;
}

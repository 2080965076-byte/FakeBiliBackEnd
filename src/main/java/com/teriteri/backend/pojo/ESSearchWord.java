package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ESSearchWord类，用于封装搜索引擎中的关键词信息
 * 使用了Lombok注解简化代码，自动生成getter/setter、全参构造方法和无参构造方法
 */
@Data // Lombok注解，自动为所有字段生成getter、setter、toString、equals和hashCode方法
@AllArgsConstructor // Lombok注解，自动生成包含所有字段参数的构造方法
@NoArgsConstructor // Lombok注解，自动生成无参构造方法
public class ESSearchWord {
    private String content; // 搜索关键词内容字段，用于存储用户输入的搜索词
}

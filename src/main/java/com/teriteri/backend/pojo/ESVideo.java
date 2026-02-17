package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ESVideo类用于表示视频在Elasticsearch中的索引文档
 * 使用Lombok注解自动生成getter、setter、构造器等方法
 */
@Data // Lombok注解：自动为所有字段生成getter、setter、toString、equals、hashCode方法
@AllArgsConstructor // Lombok注解：生成包含所有字段参数的构造器
@NoArgsConstructor // Lombok注解：生成无参构造器
public class ESVideo {
    private Integer vid; // 视频ID
    private Integer uid; // 用户ID
    private String title; // 视频标题
    private String mc_id; // 主分类ID
    private String sc_id; // 子分类ID
    private String tags; // 视频标签
    private Integer status; // 视频状态
}

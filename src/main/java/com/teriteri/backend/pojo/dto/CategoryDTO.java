package com.teriteri.backend.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 分类数据传输对象(CategoryDTO)，用于封装分类相关的数据信息
 * 使用了Lombok的@Data注解自动生成getter、setter、toString等方法
 * 使用了@AllArgsConstructor注解自动生成全参数构造方法
 * 使用了@NoArgsConstructor注解自动生成无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    // 主分类ID，用于标识主分类的唯一标识符
    private String mcId;
    // 主分类名称，用于表示主分类的名称信息
    private String mcName;
    // 子分类列表，使用Map结构存储子分类的相关信息，key为String类型，value为Object类型
    private List<Map<String, Object>> scList;
}

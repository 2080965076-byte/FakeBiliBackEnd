package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Command类，用于封装命令信息
 * 使用了Lombok库的@Data、@AllArgsConstructor和@NoArgsConstructor注解
 * @Data：自动生成getter、setter、toString、equals和hashCode方法
 * @AllArgsConstructor：生成包含所有字段参数的构造方法
 * @NoArgsConstructor：生成无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    // 命令代码，用于标识不同的命令类型
    private Integer code;
    // 命令内容，包含具体的命令信息
    private String content;
}

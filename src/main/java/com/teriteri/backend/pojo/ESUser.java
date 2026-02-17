package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ESUser类，用于表示Elasticsearch中的用户信息
 * 使用了Lombok注解来自动生成getter、setter、toString等方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ESUser {
    // 用户ID，整型
    private Integer uid;
    // 用户昵称，字符串类型
    private String nickname;
}

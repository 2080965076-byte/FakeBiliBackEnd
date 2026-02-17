package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举类：命令类型
 * 使用@Getter注解自动生成getter方法
 * 使用@AllArgsConstructor注解自动生成全参数构造方法
 */
@Getter
@AllArgsConstructor
public enum CommandType {
    /**
     * 建立连接
     * 对应命令码：100
     */
    CONNETION(100),

    /**
     * 聊天功能 发送
     */
    CHAT_SEND(101),

    /**
     * 聊天功能 撤回
     */
    CHAT_WITHDRAW(102),

    ERROR(-1),
    ;

    private final Integer code;

/**
 * 根据给定的代码值匹配对应的命令类型
 * @param code 要匹配的代码值
 * @return 匹配到的CommandType，如果未匹配到则返回ERROR
 */
    public static CommandType match(Integer code) {
    // 遍历所有的CommandType枚举值
        for (CommandType value: CommandType.values()) {
        // 检查当前枚举值的代码是否与给定的代码匹配
            if (value.getCode().equals(code)) {
            // 如果匹配，返回当前枚举值
                return value;
            }
        }
    // 如果遍历完所有枚举值都没有匹配项，返回ERROR类型的命令
        return ERROR;
    }
}

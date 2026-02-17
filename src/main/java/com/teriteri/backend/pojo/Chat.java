package com.teriteri.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 聊天信息实体类
 * 使用@Data注解自动生成getter/setter等方法
 * 使用@AllArgsConstructor生成全参数构造方法
 * 使用@NoArgsConstructor生成无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    /**
     * 聊天记录的唯一标识ID
     * 使用@TableId注解标记为主键，并设置自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;     // 发送者UID
    private Integer anotherId;  // 接收者UID
    private Integer isDeleted;  // 是否移除聊天 0否 1是
    private Integer unread;     // 消息未读数
    private Date latestTime;    // 最近接收消息的时间或最近打开聊天窗口的时间

}

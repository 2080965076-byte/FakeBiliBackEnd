package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 历史记录实体类
 * 用于记录用户对视频或动态的浏览历史
 * 实现了Serializable接口，支持序列化操作
 * 使用了Lombok注解简化代码
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History  implements Serializable {
    private Long id; // 历史记录的唯一标识符
    private Integer entityType; // 1-视频 2-动态
    private Long entityId;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
}

package com.teriteri.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应包装类
 * 用于统一封装接口返回的数据结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// 自定义响应对象，用于标准化API返回格式
public class CustomResponse {
    // 状态码，默认值为200表示成功
    private int code = 200;
    // 响应消息，默认值为"OK"
    private String message = "OK";
    // 响应数据，可以是任意类型的数据
    private Object data;
}

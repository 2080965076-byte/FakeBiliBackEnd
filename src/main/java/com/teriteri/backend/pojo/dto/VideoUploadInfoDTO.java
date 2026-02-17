package com.teriteri.backend.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频上传信息数据传输对象(Data Transfer Object)
 * 用于封装和传输视频上传相关的数据信息
 */
@Data                 // 使用Lombok的@Data注解，自动生成getter、setter、toString等方法
@AllArgsConstructor  // 使用Lombok的全参构造方法注解
@NoArgsConstructor   // 使用Lombok的无参构造方法注解
public class VideoUploadInfoDTO {
    private Integer uid;            // 用户ID，标识上传视频的用户
    private String hash;            // 视频文件的哈希值，用于唯一标识视频文件
    private String title;           // 视频标题
    private Integer type;           // 视频类型，可能用于分类
    private Integer auth;           // 视频权限设置，如公开、私密等
    private Double duration;        // 视频时长，单位可能是秒
    private String mcId;            // 主分类ID，用于视频分类
    private String scId;            // 子分类ID，用于更细粒度的视频分类
    private String tags;            // 视频标签，用于描述视频内容的关键词
    private String descr;           // 视频描述，提供视频的详细说明
    private String coverUrl;        // 视频封面图片的URL地址
}

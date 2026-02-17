package com.teriteri.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 收藏视频实体类
 * 使用@Data注解自动生成getter、setter等方法
 * 使用@AllArgsConstructor注解自动生成全参构造方法
 * 使用@NoArgsConstructor注解自动生成无参构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteVideo {
    /**
     * 主键ID，使用@TableId注解标记为主键，type=IdType.AUTO表示自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 视频ID，关联视频表的主键
    private Integer vid;    // 视频ID
    // 收藏夹ID，关联收藏夹表的主键
    private Integer fid;    // 收藏夹ID
    // 收藏时间，记录视频被添加到收藏夹的具体时间
    private Date time;  // 收藏时间
    // 移除标记，标识该视频是否已从收藏夹中移除
    // 1表示已移出收藏夹，0或其他值表示仍在收藏夹中
    private Integer isRemove;   // 是否移除 1已移出收藏夹
}

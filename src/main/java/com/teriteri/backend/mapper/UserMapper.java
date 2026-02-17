package com.teriteri.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teriteri.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层接口
 * 继承MyBatis-Plus的BaseMapper，提供基础的CRUD操作
 */
@Mapper  // MyBatis的注解，标记该接口为MyBatis的Mapper接口
public interface UserMapper extends BaseMapper<User> {  // 继承BaseMapper<User>，获得User实体的基础CRUD操作方法
}

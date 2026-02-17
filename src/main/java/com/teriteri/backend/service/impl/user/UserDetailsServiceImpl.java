package com.teriteri.backend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teriteri.backend.mapper.UserMapper;
import com.teriteri.backend.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

/**
 * 根据用户名加载用户信息
 * 这是UserDetailsService接口的实现方法，用于Spring Security认证过程
 *
 * @param username 要查询的用户名
 * @return 返回UserDetails对象，包含用户的所有信息，如果用户不存在则返回null
 * @throws UsernameNotFoundException 当用户名不存在时抛出此异常
 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 创建查询条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    // 设置查询条件：用户名等于传入的用户名
        queryWrapper.eq("username", username);
    // 设置查询条件：用户状态不等于2（2是封禁）
        queryWrapper.ne("state", 2);
    // 执行查询，获取用户信息
        User user = userMapper.selectOne(queryWrapper);
    // 如果用户不存在，返回null
        if (user == null) {
            return null;
        }

    // 返回UserDetails的实现类对象，包含用户信息
        return new UserDetailsImpl(user);
    }
}

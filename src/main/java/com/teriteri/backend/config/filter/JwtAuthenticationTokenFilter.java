package com.teriteri.backend.config.filter;

import com.teriteri.backend.pojo.User;
import com.teriteri.backend.service.impl.user.UserDetailsImpl;
import com.teriteri.backend.utils.JwtUtil;
import com.teriteri.backend.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // 使用Spring的@Component注解将此类标记为组件，使其成为Spring容器中的一个Bean
@Slf4j // 使用Lombok的@Slf4j注解，自动生成日志记录器
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter { // 继承OncePerRequestFilter确保每个请求只通过一次过滤器

    @Autowired // 使用Spring的@Autowired注解自动注入JwtUtil工具类
    private JwtUtil jwtUtil;

    @Autowired // 使用Spring的@Autowired注解自动注入RedisUtil工具类
    private RedisUtil redisUtil;

    /**
     * token 认证过滤器，任何请求访问服务器都会先被这里拦截验证token合法性
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param response HttpServletResponse对象，用于向客户端返回响应
     * @param filterChain FilterChain对象，用于调用过滤器链中的下一个过滤器
     * @throws ServletException 可能抛出的Servlet异常
     * @throws IOException 可能抛出的IO异常
     */
    @Override  // 重写父类的doFilterInternal方法
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
    // 从请求头中获取Authorization字段，用于获取token
        String token = request.getHeader("Authorization");

    // 检查token是否存在或是否以"Bearer "开头
        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            // 通过开放接口过滤器后，如果没有可解析的token就放行
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7); // 去掉"Bearer "前缀，获取实际的token值

        // 解析token，验证其有效性
        boolean verifyToken = jwtUtil.verifyToken(token);
        if (!verifyToken) {
//            log.error("当前token已过期");
            response.addHeader("message", "not login"); // 设置响应头信息，给前端判断用
            response.setStatus(403);
//            throw new AuthenticationException("当前token已过期");
            return;
        }
        String userId = JwtUtil.getSubjectFromToken(token);
        String role = JwtUtil.getClaimFromToken(token, "role");

        // 从redis中获取用户信息
        User user = redisUtil.getObject("security:" + role + ":" + userId, User.class);

        if (user == null) {
//            log.error("用户未登录");
            response.addHeader("message", "not login"); // 设置响应头信息，给前端判断用
            response.setStatus(403);
//            throw new AuthenticationException("用户未登录");
            return;
        }

        // 存入SecurityContextHolder，这里建议只供读取uid用，其中的状态等非静态数据可能不准，所以建议redis另外存值
        UserDetailsImpl loginUser = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}

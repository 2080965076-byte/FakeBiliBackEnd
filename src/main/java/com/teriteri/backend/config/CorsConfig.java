package com.teriteri.backend.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CorsConfig implements Filter {
/**
 * 这是一个过滤器方法，用于处理跨域请求(CORS)相关配置
 * 该方法会设置HTTP响应头，以允许跨域请求并指定相应的跨域策略
 *
 * @param req ServletRequest对象，包含客户端请求信息
 * @param res ServletResponse对象，用于向客户端发送响应
 * @param chain FilterChain对象，用于将请求传递给下一个过滤器或目标资源
 * @throws IOException 可能发生I/O异常
 * @throws ServletException 可能发生Servlet异常
 */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    // 将ServletResponse强制转换为HttpServletResponse，以便设置HTTP响应头
        HttpServletResponse response = (HttpServletResponse) res;
    // 将ServletRequest强制转换为HttpServletRequest，以便获取HTTP请求头
        HttpServletRequest request = (HttpServletRequest) req;

    // 获取请求中的Origin头信息，用于确定跨域请求的来源
        String origin = request.getHeader("Origin");
    // 如果Origin不为空，则设置Access-Control-Allow-Origin响应头，允许指定域的跨域请求
        if(origin!=null) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }

    // 获取请求中的Access-Control-Request-Headers头信息，用于预检请求
        String headers = request.getHeader("Access-Control-Request-Headers");
    // 如果headers不为空，则设置允许的请求头和暴露的响应头
        if(headers!=null) {
            response.setHeader("Access-Control-Allow-Headers", headers);
            response.setHeader("Access-Control-Expose-Headers", headers);
        }

    // 设置允许的HTTP方法，"*"表示允许所有方法
        response.setHeader("Access-Control-Allow-Methods", "*");
    // 设置预检请求的结果可以被缓存的时间（秒）
        response.setHeader("Access-Control-Max-Age", "3600");
    // 设置是否允许跨域请求携带认证信息（如cookies）
        response.setHeader("Access-Control-Allow-Credentials", "true");

    // 将请求传递给过滤器链中的下一个资源
        chain.doFilter(request, response);
    }

/**
 * 初始化过滤器方法
 * 当Web容器启动时，会调用此方法来初始化过滤器实例
 * @param filterConfig 包含过滤器的配置信息，如初始化参数等
 */
    @Override
    public void init(FilterConfig filterConfig) {

        // 此方法为空实现，表示当前过滤器不需要特殊的初始化操作
        // 如果需要从filterConfig中获取初始化参数，可以在此方法中实现
    }

/**
 * 销毁方法，用于在Servlet生命周期结束时执行资源清理工作。
 * 当Servlet容器决定卸载Servlet实例时会调用此方法。
 * 此方法由Servlet容器调用，只会在Servlet的生命周期结束时调用一次。
 *
 * @Override 注解表明这个方法是重写父类中的方法，确保方法签名正确。
 *
 * 注意：在此方法中应释放所有在init方法中获取的资源，
 * 如关闭数据库连接、停止线程、释放文件句柄等。
 */
    @Override
    public void destroy() {
    // 方法体为空，表示当前Servlet不需要进行任何特殊的资源清理工作。
    // 如果有需要释放的资源，应在此处添加相应的清理代码。
    }
}

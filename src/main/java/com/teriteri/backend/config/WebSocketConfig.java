package com.teriteri.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    /**
     * 仅在非测试环境下注册一个ServerEndpointExporter，该Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
 * 这是一个Spring配置类中的Bean方法定义
 *
 * @Bean 声明该方法返回一个对象，该对象应该被注册为Spring应用程序上下文中的Bean
 * @Profile("!test") 指定该Bean仅在非测试(profile不为test)的环境下创建
 *
 * @return ServerEndpointExporter Spring WebSocket的端点导出器，用于注册WebSocket端点
     */
    @Bean
    @Profile("!test")
    public ServerEndpointExporter serverEndpointExporter() {
    // 创建并返回一个新的ServerEndpointExporter实例
    // 该实例会自动扫描并注册带有@ServerEndpoint注解的类作为WebSocket端点
        return new ServerEndpointExporter();
    }
}

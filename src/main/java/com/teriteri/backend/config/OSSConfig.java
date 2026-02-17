package com.teriteri.backend.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OSS配置类
 * 用于配置阿里云对象存储服务(OSS)的客户端连接
 */
@Configuration
public class OSSConfig {
    // 从配置文件中读取OSS的终端节点地址
    @Value("${oss.endpoint}")
    private String OSS_ENDPOINT;

    // 从配置文件中读取访问密钥ID
    @Value("${oss.keyId}")
    private String ACCESS_KEY_ID;

    // 从配置文件中读取访问密钥密码
    @Value("${oss.keySecret}")
    private String ACCESS_KEY_SECRET;

    // 从配置文件中读取连接空闲超时时间
    @Value("${oss.idleTimeout}")
    private long IDLE_TIMEOUT;

    /**
     * 创建并配置OSS客户端Bean
     * @return OSSClient实例，用于与OSS服务进行交互
     * destroyMethod = "shutdown"确保在容器销毁时关闭OSS客户端连接
     */
    @Bean(destroyMethod = "shutdown")
    public OSS ossClient() {
        // 创建客户端配置对象
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        //连接空闲超时时间，超时则关闭
        conf.setIdleConnectionTime(IDLE_TIMEOUT);
        // 创建OSSClient实例
        return new OSSClientBuilder().build(OSS_ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET, conf);
    }
}

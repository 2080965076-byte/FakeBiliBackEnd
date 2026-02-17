package com.teriteri.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;

/**
 * 文件上传配置类
 * 用于配置Spring MVC中的文件上传功能
 */
@Configuration // 标识该类为配置类，相当于XML配置中的<beans>
@ConfigurationProperties(prefix = "file") // 将配置文件中以"file"为前缀的属性映射到该类的属性上
public class FileUploadConfig {



    /**
     * 配置MultipartResolver Bean
     * 用于处理文件上传请求
     * @return 返回一个配置好的CommonsMultipartResolver实例
     */
    @Bean(name = "multipartResolver") // 将该方法返回的对象注册为Bean，名称为"multipartResolver"
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");//设置编码
        multipartResolver.setMaxUploadSizePerFile(30 * 1024 * 1024); // 设置文件上传大小限制 30MB
        multipartResolver.setResolveLazily(true);// 设置为true表示延迟解析文件上传请求
        return multipartResolver;
    }

}
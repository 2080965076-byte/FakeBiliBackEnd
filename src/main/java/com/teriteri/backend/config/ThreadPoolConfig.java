package com.teriteri.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置类
 * 使用@Configuration注解标记该类为配置类
 * 使用@EnableAsync注解开启Spring的异步功能支持
 */
@Configuration
@EnableAsync    // 开启异步
public class ThreadPoolConfig {

    /**
     * 定义名为"taskExecutor"的线程池Bean
     * @return 返回一个配置好的线程池执行器
     */
    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        // 创建线程池任务执行器实例
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(20);
        //设置最大线程数
        executor.setMaxPoolSize(100);
        //配置队列大小，设置为最大整数值，表示队列容量几乎无限
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //设置线程活跃时间（秒），超过核心线程数的线程在空闲60秒后会被回收
        executor.setKeepAliveSeconds(60);
        //设置默认线程名称前缀，方便在日志和监控中识别线程来源
        executor.setThreadNamePrefix("teriteri");
        //等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //执行初始化
        executor.initialize();
        return executor;
    }

}

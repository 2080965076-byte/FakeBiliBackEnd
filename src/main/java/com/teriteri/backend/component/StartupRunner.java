package com.teriteri.backend.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 程序启动前创建分片目录
 */
@Component
public class StartupRunner implements CommandLineRunner {

    @Value("${directory.chunk}")
    private String chunkDirectory;

/**
 * 重写run方法，用于程序启动时执行初始化操作
 * @param args 命令行参数
 * @throws Exception 可能抛出的异常
 */
    @Override
    public void run(String... args) throws Exception {
    // 创建分片目录的File对象
        File chunkDir = new File(chunkDirectory);
    // 检查目录是否存在
        if (!chunkDir.exists()) {
        // 如果不存在，尝试创建目录
            boolean created = chunkDir.mkdirs();
        // 如果创建失败，抛出运行时异常
            if (!created) {
                throw new RuntimeException("Failed to create directory: " + chunkDirectory);
            }
        }
    }
}
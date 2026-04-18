package com.hzk.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.config
 * @Project: IT_Project
 * @name: ThreadPoolConfig
 * @Date: 2026/4/17 15:25
 * @Filename: ThreadPoolConfig
 */

@Configuration
@EnableAsync // 开启异步执行支持
public class ThreadPoolConfig {

    // 定义一个名为 logAsyncThreadPool 的线程池 Bean
    @Bean("logAsyncThreadPool")
    public Executor logAsyncThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(5);
        // 最大线程数
        executor.setMaxPoolSize(10);
        // 队列容量
        executor.setQueueCapacity(100);
        // 线程活跃时间(秒)
        executor.setKeepAliveSeconds(60);
        // 线程名称前缀
        executor.setThreadNamePrefix("logAsync-");
        // 拒绝策略：当队列满了且线程达到最大线程数时，由调用线程（即主线程）自己执行任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 初始化
        executor.initialize();
        return executor;
    }
}

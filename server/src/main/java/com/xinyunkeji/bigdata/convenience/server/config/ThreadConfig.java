package com.xinyunkeji.bigdata.convenience.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 *
 * @author Yuezejian
 * @date 2020年 08月22日 22:09:26
 */
@Configuration
public class ThreadConfig {

    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        /*executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setKeepAliveSeconds(10);
        executor.setQueueCapacity(8);*/

        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setKeepAliveSeconds(10);
        executor.setQueueCapacity(4);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
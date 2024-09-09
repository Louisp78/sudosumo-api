package com.sudosumo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class AppConfiguration {
    
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1); // Number of threads in the pool
        scheduler.setThreadNamePrefix("taskScheduler-"); // Prefix for thread names
        scheduler.initialize(); // Initialize the scheduler
        return scheduler;
    }
}

package com.abunko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

@Configuration
public class ExecutorServiceConfig {
    @Bean
    public ScheduledExecutorService scheduler() {
        return Executors.newScheduledThreadPool(10);
    }

    @Bean
    public Map<String, ScheduledFuture<?>> runnableTasks(){
        return new HashMap<>();
    }
}

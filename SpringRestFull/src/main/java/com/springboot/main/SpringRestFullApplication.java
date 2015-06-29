package com.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
@ComponentScan({"com.springboot.main.model","com.springboot.main","com.springboot.main.web.api"})
public class SpringRestFullApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestFullApplication.class, args);
    }
    
    @Bean
    public CacheManager cachemanager(){
    	GuavaCacheManager cacheManager = new GuavaCacheManager("greetings");
    	return cacheManager;
    }
}

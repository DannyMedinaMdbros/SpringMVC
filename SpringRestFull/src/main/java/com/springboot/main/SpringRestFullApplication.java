package com.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.springboot.main.model","com.springboot.main","com.springboot.main.web.api"})
public class SpringRestFullApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestFullApplication.class, args);
    }
}

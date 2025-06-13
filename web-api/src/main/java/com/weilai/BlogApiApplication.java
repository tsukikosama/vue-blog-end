package com.weilai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


///开启异步任务的注解
@EnableAsync
////用于开启定时任务
@EnableScheduling
@SpringBootApplication
@MapperScan({ "com.weilai.system.mapper","com.weilai.blog.mapper"})
//@EnableChatGPT
public class BlogApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }
}

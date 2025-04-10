package com.weilai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


///开启异步任务的注解
@EnableAsync
////用于开启定时任务
//@EnableScheduling
@SpringBootApplication
//@EnableChatGPT
public class BlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);

    }

}

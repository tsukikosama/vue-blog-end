package com.miku;


import com.miku.config.ForbiddenWordsLoader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


///开启异步任务的注解
@EnableAsync
////用于开启定时任务
//@EnableScheduling
@SpringBootApplication
@MapperScan("com.miku.mapper")
//@EnableChatGPT
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);

    }

}

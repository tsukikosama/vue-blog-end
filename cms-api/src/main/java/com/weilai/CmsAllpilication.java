package com.weilai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
////用于开启定时任务
//@EnableScheduling
@SpringBootApplication
//@EnableChatGPT
public class CmsAllpilication {
    public static void main(String[] args) {
        SpringApplication.run(CmsAllpilication.class,args);
    }
}

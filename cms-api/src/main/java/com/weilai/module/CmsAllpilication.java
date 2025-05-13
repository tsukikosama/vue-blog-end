package com.weilai.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
//用于开启定时任务
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.weilai")
//@EnableChatGPT
public class CmsAllpilication {
    public static void main(String[] args) {
        SpringApplication.run(CmsAllpilication.class,args);
    }
}

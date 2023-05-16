package com.miku.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.miku.entity.Code;
import com.miku.service.CoderService;
import com.miku.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService service;

    @Autowired
    private CoderService coderService;

    @PostMapping("/send")
    public void sendTextMail(@RequestParam("address")String address){
        //传教wrapper
        LambdaQueryWrapper<Code> wrapper = new LambdaQueryWrapper<>();
        //通过wrapper 来查询是否发送过验证码
        LambdaQueryWrapper<Code> code = wrapper.eq(Code::getEmail, address);
//        System.out.println(code);
        //判断是否需要发送
        if (BeanUtil.isNotEmpty(code)){
//            System.out.println("1111111");
            //如果为空就是没发送过 调用发送信息的借口
            service.sendTextMailMessage(address);
            //用redis设置定过期时间来实现
            //

        }

    }

}

package com.weilai.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weilai.common.Result;
import com.weilai.entity.User;
import com.weilai.service.CoderService;
import com.weilai.service.UserService;
import com.weilai.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService service;

    @Autowired
    private CoderService coderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;
    /**
     * 验证码功能
     * @param address
     */
    @PostMapping("/send")
    public Result sendTextMail(@RequestParam("address")String address){
       /**
        * 这个方法是通过邮箱
        *
        * //传教wrapper
        LambdaQueryWrapper<Code> wrapper = new LambdaQueryWrapper<>();
        //通过wrapper 来查询是否发送过验证码
        LambdaQueryWrapper<Code> code = wrapper.eq(Code::getEmail, address);
//        System.out.println(code);
        //判断是否需要发送
        if (BeanUtil.isNotEmpty(code)){
//            System.out.println("1111111");
//            如果为空就是没发送过 调用发送信息的借口
//            service.sendTextMailMessage(address);
            //用redis设置定过期时间来实现
            //判断用户是否发送过验证码**/
        //判断是否有这个邮箱
       //判断redis中是否存在这个邮箱
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
         wrapper.eq(User::getEmail, address);
        User one = userService.getOne(wrapper);
        System.out.println(one);
        if(one != null){
            return Result.fail("邮箱存在");
        }
        if (!redisTemplate.hasKey(address)){
            System.out.println("测试");
            //如果没有这个验证就就把验证码存入redis
            String code = RandomUtil.randomNumbers(6);
            redisTemplate.opsForValue().set(address,code,300,TimeUnit.SECONDS);
            //并且发送邮件
            service.sendTextMailMessage(address,code);
            return Result.ok("成功");
        }
            return Result.fail("禁止重复发送消息");

    }

    @PostMapping("/ban")
    public Result banUser(@RequestBody User address){

        System.out.println("mail"+address);
        service.sendBan(address.getEmail());
        return Result.ok();
    }


}

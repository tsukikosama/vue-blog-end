package com.miku.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.common.Result;
import com.miku.entity.Code;
import com.miku.entity.User;
import com.miku.pojo.Userpo;
import com.miku.service.CoderService;
import com.miku.service.UserService;
import com.miku.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {



    @Autowired
    private UserService userService;
    @PostMapping
    public Result login(@RequestBody User user){
        System.out.println(user);
        Userpo u = userService.login(user);
        if (u.getUsername() == null){
            return Result.fail("用户命或者密码错误");
        }
        return Result.ok(u);
    }

    @PostMapping("/register")
    public Result registerUser(@RequestBody User user,@RequestParam("code")String code){

        System.out.println(code);
        String msg = userService.register(user,code);

        return Result.ok(msg);
    }

    @GetMapping("")
    public Result getUser(@RequestParam(value = "search",required = false ,defaultValue = "") String username
                          ,@RequestParam(value = "current",required = false,defaultValue = "1") Integer current
    ){
        Page<User> u = userService.getUser(username,current);

        return Result.ok(u);
    }
    @PostMapping("/update")
    public Result updateUser(@RequestBody User user){
        if(user == null){
           return Result.fail("更新失败");
        }
        boolean b = userService.saveOrUpdate(user);
        if (!b){
            return Result.fail("更新失败");
        }
        return Result.ok("更新成功");
    }

}

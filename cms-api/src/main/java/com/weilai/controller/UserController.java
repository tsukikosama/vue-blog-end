package com.weilai.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.Result;
import com.weilai.entity.User;
import com.weilai.exception.CustomException;
import com.weilai.pojo.LoginReq;
import com.weilai.request.QueryUserParamsRequest;
import com.weilai.request.RegisterUserRequest;
import com.weilai.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    @Value("${cc.miku.avatar}")
    private String avatar;

    private final UserService userService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody LoginReq req){
        //判断用户名是否存在
        User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, req.getUsername()));
        if (one == null){
            throw new CustomException(500,"用户名不存在");
        }
        //判断密码是否一致
        if (!req.getPassword().equals(one.getPassword())){
            throw new CustomException(500,"用户名或密码错误");
        }
        StpUtil.login(one.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return Result.ok(tokenInfo.getTokenValue());
    }

    @ApiOperation("用户登录用户信息")
    @PostMapping("/info")
    public Result getUserInfo(){
        long uid = StpUtil.getLoginIdAsLong();
        User user = userService.getOneById(uid);
        return Result.ok(user);
    }

    @ApiOperation("退出登录接口")
    @PostMapping("/logout")
    public Result logout(){
        StpUtil.logout();
        return Result.ok("退出成功");
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result registerUser(@RequestBody RegisterUserRequest request){
        String msg = userService.register(request);
        return Result.ok(msg);
    }

    @ApiOperation("获取全部的用户信息")
    @GetMapping("/list")
    public Result getUserList(QueryUserParamsRequest request){
        System.out.println(request);
//        Page<User> user = userService.getUser(null, current);
        return Result.ok("user");
    }
}

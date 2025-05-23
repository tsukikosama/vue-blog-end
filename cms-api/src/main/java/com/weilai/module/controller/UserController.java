package com.weilai.module.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.module.entity.User;
import com.weilai.module.pojo.LoginReq;
import com.weilai.module.request.QueryUserParamsRequest;
import com.weilai.module.request.SaveUserRequest;
import com.weilai.module.response.BlogRecordResponse;
import com.weilai.module.response.UserRecordResponse;
import com.weilai.module.service.UserService;
import com.weilai.common.Result;
import com.weilai.exception.CustomException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    @Value("${cc.miku.avatar}")
    private String avatar;

    private final UserService userService;

    private final StringRedisTemplate redisTemplate;

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
        //如果uid等于null看看redis中是否存在

        User user = userService.getOneById(uid);
        return Result.ok(user);
    }

    @ApiOperation("退出登录接口")
    @PostMapping("/logout")
    public Result logout(){
        StpUtil.logout();
        return Result.ok("退出成功");
    }

//    @ApiOperation("注册")
//    @PostMapping("/register")
//    public Result registerUser(@RequestBody RegisterUserRequest request){
//        String msg = userService.register(request);
//        return Result.ok(msg);
//    }

//    @ApiOperation("获取全部的用户信息")
//    @GetMapping("/list")
//    public Result getUserList(QueryUserParamsRequest request){
//        System.out.println(request);
//        Page<User> user = userService.getUser(request);
//        return Result.ok(user);
//    }

    @ApiOperation("获取全部的用户信息")
    @GetMapping("/page")
    public Result page(QueryUserParamsRequest request){
        IPage<UserRecordResponse> page =  userService.selectUserPage(request);
        return Result.ok(page);
    }
    @ApiOperation("添加用户")
    @PostMapping("/resetPwd")
    public Result resetPwd(@RequestBody List<Long> ids){
        userService.resetPwd(ids);
//        Boolean addUser = userService.addUser(user);
        return Result.ok("重置密码成功");
    }

    @ApiOperation("获取用户详情")
    @GetMapping("/detail")
    public Result getUserDetail(@RequestParam("id") Long id){
        User oneById = userService.getOneById(id);
        return Result.ok(oneById);
    }

    @ApiOperation("删除用户信息")
    @PostMapping("/deleteBatch")
    public Result getUserDetail(@RequestBody List<Long> id){
        userService.remove(Wrappers.<User>lambdaQuery().in(User::getId,id));
        return Result.ok("删除用户成功");
    }

    @ApiOperation("注册用户")
    @PostMapping("/save")
    public Result saveUser(@RequestBody SaveUserRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setAvatar(avatar);
        user.setUserType(1);
        user.setIsValid(1);
        user.setVersion(1);
        user.setBan("0");
        user.setNickname(request.getUsername());
        userService.save(user);
        return Result.ok("注册成功");
    }
}

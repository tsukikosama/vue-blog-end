package com.weilai.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.entity.User;
import com.weilai.exception.CustomException;
import com.weilai.pojo.LoginReq;
import com.weilai.request.RegisterUserRequest;
import com.weilai.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Value("${cc.miku.avatar}")
    private String avatar;

    @Autowired
    private UserService userService;

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

    @GetMapping("/getall")
    public Result getUser(@RequestParam(value = "search",required = false ,defaultValue = "") String username,@RequestParam(value = "current",required = false,defaultValue = "1") Integer current){
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

    @GetMapping("/getone")
    public Result getOne(@RequestParam("id") Long id){
        User u = userService.getOneById(id);
        if(u == null){
            return Result.fail("用户名获取失败");
        }
        return Result.ok(u);
    }

//    @GetMapping("/ban")
//    public Result banUser(@RequestParam("email") String email){
//        //通过邮箱来查询对应的用户
//        Userpo userpo = userService.banUser(email);
//        return Result.ok("禁用成功");
//    }
//    @PostMapping("/unban")
//    public Result unBan(@RequestParam("email")String email,@RequestParam("uid") Integer uid){
//        Userpo userpo = userService.unBan(email,uid);
//        if (userpo == null){
//            return Result.fail("解封失败");
//        }
//        return Result.ok(userpo);
//    }
    @GetMapping("/all")
    public Result list(){
        return Result.ok(userService.list());
    }

    @GetMapping("/list")
    public Result getBlogByPage(PageQuery query){
        System.out.println(query);
        return Result.ok(userService.listByPage(query));
    }

    @PostMapping("/del")
    public Result delUser(@RequestParam("uid") Integer uid){
        userService.removeById(uid);
        return Result.ok("删除失败");
    }
    @PostMapping("/add")
    public Result saveUser(@RequestBody User user){
        user.setAbout("这个人很懒什么都没有留下");
        user.setAvatar(avatar);
        user.setIsValid(1);

        userService.saveOrUpdate(user);
        return Result.ok("保存成功");
    }
}

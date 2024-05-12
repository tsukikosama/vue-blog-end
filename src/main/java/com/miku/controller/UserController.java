package com.miku.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.common.CommonQuery;
import com.miku.common.Result;
import com.miku.entity.User;
import com.miku.pojo.Userpo;
import com.miku.service.UserService;
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

    @GetMapping()
    public Result getAllUser(){
        return Result.ok(userService.list());
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        System.out.println(user);
        Userpo token = userService.login(user);
        if (token == null){
            return Result.fail("用户名或者密码错误");
        }
        return Result.ok(token);
    }

    @PostMapping("/register")
    public Result registerUser(@RequestBody User user,@RequestParam("code")String code){

        //System.out.println(code);
        String msg = userService.register(user,code);

        return Result.ok(msg);
    }

    @GetMapping("/getall")
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

    @GetMapping("/getone")
    public Result getOne(@RequestParam("id") Integer id){
        User u = userService.getOneById(id);
        if(u == null){
            return Result.fail("用户名获取失败");
        }
        return Result.ok(u);
    }

    @GetMapping("/ban")
    public Result banUser(@RequestParam("email") String email){
        //通过邮箱来查询对应的用户
        Userpo userpo = userService.banUser(email);
        return Result.ok("禁用成功");
    }
    @PostMapping("/unban")
    public Result unBan(@RequestParam("email")String email,@RequestParam("uid") Integer uid){
        Userpo userpo = userService.unBan(email,uid);
        if (userpo == null){
            return Result.fail("解封失败");
        }
        return Result.ok(userpo);
    }
    @GetMapping("/all")
    public Result list(){
        return Result.ok(userService.list());
    }

    @GetMapping("/list")
    public Result getBlogByPage(CommonQuery query){
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
        user.setCreateTime(DateUtil.now());
        user.setAbout("这个人很懒什么都没有留下");
        user.setUserIcon(avatar);
        user.setIsValid("1");

        userService.saveOrUpdate(user);
        return Result.ok("保存成功");
    }
}

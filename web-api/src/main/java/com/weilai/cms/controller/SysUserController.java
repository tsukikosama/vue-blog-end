package com.weilai.cms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.weilai.system.model.entity.SysUserEntity;
import com.weilai.system.model.req.LoginReq;
import com.weilai.system.model.resp.UserInfoResp;
import com.weilai.system.service.ISysUserService;
import com.weilai.system.common.Result;
import com.weilai.system.exception.CustomException;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-06-13
 */
@RestController
@RequestMapping("/sys/user")
@Tag(name = "系统用户接口", description = "用户相关接口")
@RequiredArgsConstructor
public class SysUserController {


    private final ISysUserService sysUserService;



    @PostMapping("/login")
    @Operation(summary = "登录", description = "登录")
    public Result<String> login(@RequestBody LoginReq req){
        SysUserEntity user = sysUserService.getOne(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername, req.getUsername()));
        if (ObjectUtils.isEmpty(user)){
            throw new CustomException("用户名不存在");
        }
        if (!user.getPassword().equals(SecureUtil.md5(req.getPassword()))){
            throw new CustomException("密码不正确");
        }
        StpUtil.login(user.getId());
        return Result.ok(StpUtil.getTokenValue());
    }

    @SaCheckLogin
    @GetMapping("/info")
    @Operation(summary = "用户信息", description = "用户信息")
    public Result<UserInfoResp> info(){
        SysUserEntity user = sysUserService.getById(StpUtil.getLoginIdAsLong());
        return Result.ok(BeanUtil.copyProperties(user, UserInfoResp.class));
    }
}

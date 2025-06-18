package com.weilai.cms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.system.model.entity.SysUserEntity;
import com.weilai.system.model.req.LoginReq;
import com.weilai.system.model.query.UserQuery;
import com.weilai.system.model.req.UserReq;
import com.weilai.system.model.resp.UserInfoResp;
import com.weilai.system.service.ISysUserService;
import com.weilai.system.common.Result;
import com.weilai.system.exception.CustomException;
import com.weilai.system.utils.PageUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Tag(name = "系统用户接口", description = "系统用户接口")
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
    @PostMapping("/info")
    @Operation(summary = "用户信息", description = "用户信息")
    public Result<UserInfoResp> info(){
        SysUserEntity user = sysUserService.getById(StpUtil.getLoginIdAsLong());
        return Result.ok(BeanUtil.copyProperties(user, UserInfoResp.class));
    }


    @PostMapping("/logout")
    @Operation(summary = "用户退出", description = "用户退出")
    public Result<Void> logout(){
        StpUtil.logout();
        return Result.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "用户列表", description = "用户列表")
    public Result<Page<UserInfoResp>> page(UserQuery req){
//        Page<SysUserEntity> page = new Page<>(req.getCurrent(), req.getSize());
        Page<SysUserEntity>  page = sysUserService.page(new Page<>(req.getCurrent(), req.getPageSize()));
        return Result.ok(PageUtils.build(page,UserInfoResp.class));
    }

    @PostMapping("/ban/{status}")
    @Operation(summary = "禁用/启用用户", description = "禁用/启用用户")
    public Result<Void> ban(@RequestBody List<Long> ids,@PathVariable("status")Integer status){
        this.sysUserService.update(Wrappers.<SysUserEntity>lambdaUpdate().set(SysUserEntity::getStatus,status).in(SysUserEntity::getId,ids));
        return Result.ok();
    }
    @SaCheckLogin
    @PostMapping("/reset")
    @Operation(summary = "重置用户密码", description = "重置用户密码")
    public Result<Void> reset(@RequestBody List<Long> ids){
        this.sysUserService.update(Wrappers.<SysUserEntity>lambdaUpdate().set(SysUserEntity::getPassword,SecureUtil.md5("123456")).in(SysUserEntity::getId,ids));
        return Result.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "更新用户信息", description = "更新用户信息")
    public Result<Void> update(@RequestBody UserReq req){
        SysUserEntity sysUserEntity = BeanUtil.copyProperties(req, SysUserEntity.class);
        this.sysUserService.updateById(sysUserEntity);
        return Result.ok();
    }


}

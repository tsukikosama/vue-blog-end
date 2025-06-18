package com.weilai.cms.controller;

import com.weilai.system.service.impl.SysRoleServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@RestController
@RequestMapping("/sys/sysRoleEntity")
@Tag(name = "角色接口", description = "角色接口")
@RequiredArgsConstructor
public class SysRoleController {
    private final SysRoleServiceImpl sysRoleService;


}

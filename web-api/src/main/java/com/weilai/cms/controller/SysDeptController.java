package com.weilai.cms.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.weilai.system.common.Result;
import com.weilai.system.exception.CustomException;
import com.weilai.system.model.entity.SysDeptEntity;
import com.weilai.system.model.req.DeptReq;
import com.weilai.system.service.impl.SysDeptServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@RestController
@RequestMapping("/sys/sysDept")
@Tag(name = "部门接口", description = "部门接口")
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptServiceImpl sysDeptService;
    @PostMapping("/add")
    @Operation(summary = "添加部门接口", description = "添加部门接口")
    public Result<Void> add(@RequestBody DeptReq req){
        //先获取父类部门
        SysDeptEntity parent = sysDeptService.getById(req.getParentId());

        if (ObjectUtils.isEmpty(parent)){
            throw new CustomException("上级部门不存在");
        }
        //然后保存子类部门
        SysDeptEntity dept = new SysDeptEntity();
        dept.setName(req.getName());
        dept.setAncestors(StringUtils.join(req.getDescription(),",",parent.getId()));
        dept.setDescription(req.getDescription());
        return Result.ok();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除部门", description = "删除部门")
    public Result<Void> delete(@RequestBody List<Long> ids){
        //查询是否有子分类为删除
        ids.forEach(item -> {
            List<SysDeptEntity> sysDept = sysDeptService.getChildDept(item);
            if (ObjectUtils.isNotEmpty(sysDept)){
                throw new CustomException(String.format("部门:%s存在子部门 无法进行删除",sysDeptService.getById(item).getName()));
            }
        });
        sysDeptService.removeBatchByIds(ids);
        return Result.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "更新部门", description = "更新部门")
    public Result<Void> update(@RequestBody DeptReq req){
        sysDeptService.getById(BeanUtil.copyProperties(req,SysDeptEntity.class));
        return Result.ok();
    }
}

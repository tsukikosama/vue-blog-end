package com.weilai.cms.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.system.common.Result;
import com.weilai.system.model.entity.SysDictEntity;
import com.weilai.system.model.query.DictQuery;
import com.weilai.system.model.query.UserQuery;
import com.weilai.system.model.resp.DictResp;
import com.weilai.system.service.ISysDictService;
import com.weilai.system.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@RestController
@RequestMapping("/sys/sysDictEntity")
@RequiredArgsConstructor
@Tag(name = "字典接口", description = "字典接口")
public class SysDictController {
    private final ISysDictService sysDictService;

    @GetMapping("/page")
    @Operation(summary = "获取字典分页",description = "获取字典分页")
    public Result<Page<DictResp>> page(DictQuery req){
        Page<SysDictEntity> page = sysDictService.page(new Page<>(req.getCurrent(), req.getPageSize()));
        return Result.ok(PageUtils.build(page, DictResp.class));
    }

    @PostMapping("/save")
    @Operation(summary = "保存字典",description = "保存字典")
    public Result<Void> save(DictResp req){
        sysDictService.save(BeanUtil.copyProperties(req,SysDictEntity.class));
        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable("id")Long id){
        sysDictService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody DictResp res){
        sysDictService.updateById(BeanUtil.copyProperties(res,SysDictEntity.class));
        return Result.ok();
    }
}

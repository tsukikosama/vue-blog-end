package com.weilai.module.controller;

import cn.hutool.core.bean.BeanUtil;
import com.weilai.common.Result;
import com.weilai.module.entity.AngryPigEntity;
import com.weilai.module.entity.Blog;
import com.weilai.module.entity.BlogTypeEntity;
import com.weilai.module.request.QueryAngryPigParamsRequest;
import com.weilai.module.request.QueryBlogParamsRequest;
import com.weilai.module.request.SaveBlogRequest;
import com.weilai.module.response.SaveNoteRequest;
import com.weilai.module.service.IAngryPigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-05-13
 */
@RestController
@RequestMapping("/angryPig")
@RequiredArgsConstructor
public class AngryPigController {

    private final IAngryPigService angryPigService;
    @GetMapping("/page")
    public Result page(QueryAngryPigParamsRequest query){
        return Result.ok(angryPigService.listByPage(query));
    }

    @PostMapping("/save")
    public Result save(@RequestBody SaveNoteRequest res){
        AngryPigEntity angryPigEntity = BeanUtil.copyProperties(res, AngryPigEntity.class);
        angryPigService.save(angryPigEntity);
        return Result.ok("保存成功");
    }
    @PostMapping("/update")
    public Result update(@RequestBody SaveNoteRequest res){
        AngryPigEntity angryPigEntity = BeanUtil.copyProperties(res, AngryPigEntity.class);
        angryPigService.updateById(angryPigEntity);
        return Result.ok("修改成功");
    }
}

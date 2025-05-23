package com.weilai.module.controller;

import com.weilai.common.Result;
import com.weilai.module.entity.Type;
import com.weilai.module.request.QueryTypeParamsRequest;
import com.weilai.module.response.TypeResponse;
import com.weilai.module.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取全部标签")
    @GetMapping("/list")
    public Result list(){
        List<TypeResponse> list = tagService.selectList();
        return Result.ok(list);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result page(QueryTypeParamsRequest request){
        return Result.ok(tagService.page(request));
    }


    @ApiOperation("删除标签")
    @PostMapping("/delete")
    public Result delTag(@RequestBody List<Long> ids){
        tagService.removeBatchByIds(ids);
        return Result.ok("删除成功");
    }

    @ApiOperation("添加标签")
    @PostMapping("add")
    public Result addTag(@RequestBody Type tagname){
        tagService.saveOrUpdate(tagname);
        return Result.ok("添加成功");
    }
}

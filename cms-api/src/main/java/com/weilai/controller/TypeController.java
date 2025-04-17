package com.weilai.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.Result;
import com.weilai.entity.Type;
import com.weilai.request.QueryTypeParamsRequest;
import com.weilai.response.TypeResponse;
import com.weilai.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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


    @GetMapping("/tagid")
    public Result getBlogByTagid(@RequestParam("tagid")Integer tagid){
//        //通过tagid来查询对应的页数
//        Page<Type> list = tagService.getBlogByTagid(tagid);
//        return Result.ok(list);
        return Result.ok();
    }
    @PostMapping("/delete")
    public Result delTag(@RequestParam("id") Integer id){
        boolean success = tagService.removeById(id);
        if(!success){
            return Result.fail("输出失败");
        }
        return Result.ok("删除成功");
    }
    @PostMapping("add")
    public Result addTag(@RequestBody Type tagname){
        System.out.println(tagname);
        boolean success = tagService.saveOrUpdate(tagname);
        if (!success){
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }
}

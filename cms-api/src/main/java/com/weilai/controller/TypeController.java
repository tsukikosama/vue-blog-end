package com.weilai.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.Result;
import com.weilai.entity.Type;
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
//    @ApiOperation("标签分页")
//    @GetMapping("page")
//    public Result page(){
////        tagService.page();
//    }
    @GetMapping()
    public Result getTagsByCurrent(@RequestParam(value = "current",defaultValue = "1",required = false)Integer current){

        Page<Type> page = tagService.getTagsPage(current);
        return Result.ok(page);
    }

    @GetMapping("/tagid")
    public Result getBlogByTagid(@RequestParam("tagid")Integer tagid){
//        //通过tagid来查询对应的页数
//        Page<Type> list = tagService.getBlogByTagid(tagid);
//        return Result.ok(list);
        return Result.ok();
    }
    @PostMapping("/del")
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

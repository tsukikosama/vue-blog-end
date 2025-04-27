package com.weilai.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.Result;

import com.weilai.entity.Type;
import com.weilai.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public Result getTags(){
        return Result.ok(tagService.list());
    }
    @GetMapping()
    public Result getTagsByCurrent(@RequestParam(value = "current",defaultValue = "1",required = false)Integer current){

        Page<Type> page = tagService.getTagsPage(current);
        return Result.ok(page);
    }
//   @TODO 忘记了这个是什么方法
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

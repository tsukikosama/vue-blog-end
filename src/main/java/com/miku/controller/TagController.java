package com.miku.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.common.Result;

import com.miku.entity.Blog;
import com.miku.entity.Type;
import com.miku.service.BlogService;
import com.miku.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/tags")
    public Result getTags(){
        return Result.ok(tagService.list());
    }
    @GetMapping()
    public Result getTagsByCurrent(@RequestParam(value = "current",defaultValue = "1",required = false)Integer current){
        Page<Type> page = tagService.getTagsPage(current);
        return Result.ok(page);
    }
    @GetMapping("/tagid")
    public Result getBlogByTagid(@RequestParam("tagid")Integer tagid){
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper();
        wrapper.like(Blog::getTid,tagid);
        List<Blog> list = blogService.list(wrapper);
        return Result.ok(list);
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
        boolean success = tagService.save(tagname);
        if (!success){
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }
}

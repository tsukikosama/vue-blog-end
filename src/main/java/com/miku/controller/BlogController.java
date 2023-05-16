package com.miku.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.miku.common.Result;
import com.miku.entity.Blog;

import com.miku.pojo.BlogPo;
import com.miku.service.BlogService;
import com.miku.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService service;

    @Autowired
    private TagService tagService;
    @GetMapping("/{id}")
    public Result getBlog(@PathVariable("id")Integer id){
        Blog blog = service.getById(id);
        blog.setVisit(blog.getVisit()+1);
        service.updateById(blog);
        return Result.ok(blog);
    }
    @GetMapping
    public Result getBlogs(@RequestParam(value = "current",defaultValue = "1",required = false)Integer current,
        @RequestParam(value = "tagid",required = false)Integer id
    ){
        return  service.getBlogs(current);
    }


    @PostMapping("/update")
    public Result update(@RequestBody Blog blog){
        System.out.println(blog);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getBid,blog.getBid());
        boolean success = service.update(blog,wrapper);
        if (!success){
            return Result.fail("更新失败");
        }
        return Result.ok();
    }

    @PostMapping("/del")
    public Result del(@RequestBody Blog bid){

        boolean success = service.removeById(bid);
        if (!success){
            return Result.fail("输出失败");
        }
        return Result.ok("删除成功");
    }

    /**
     * 获取热门博客
     * @return
     */
    @GetMapping("hotblog")
    public Result hot(){
        List<Blog> list = service.hot();
        return Result.ok(list);
    }
}

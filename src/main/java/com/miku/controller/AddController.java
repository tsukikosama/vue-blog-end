package com.miku.controller;

import cn.hutool.core.date.DateTime;
import com.miku.common.Result;
import com.miku.entity.Blog;
import com.miku.service.BlogService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class AddController {
    @Autowired
    private BlogService blogService;
    @PostMapping("/add")
    public Result addBlog(@RequestBody Blog blog){
        System.out.println(blog);
        DateTime now = DateTime.now();
        blog.setCreateDate(now.toDateStr());

        boolean isSuccess = blogService.save(blog);
        if (!isSuccess){
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }
}

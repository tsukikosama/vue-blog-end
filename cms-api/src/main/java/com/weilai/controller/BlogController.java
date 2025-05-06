package com.weilai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.weilai.common.PageQuery;
import com.weilai.common.Result;
//import com.weilai.config.ForbiddenWordsLoader;

import com.weilai.entity.Blog;
import com.weilai.entity.Type;
import com.weilai.pojo.BlogPo;
import com.weilai.request.QueryBlogParamsRequest;
import com.weilai.request.SaveBlogRequest;
import com.weilai.service.BlogService;
import com.weilai.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    private final TagService tagService;




    @PostMapping("/save")
    public Result saveBlog(@RequestBody SaveBlogRequest res){
        System.out.println(res);
        Blog blog = BeanUtil.copyProperties(res, Blog.class);
        blog.setVisit(0);
        String result = StringUtils.join(res.getTagId(), ",");
        blog.setTagId(result);
        blog.setUserId(StpUtil.getLoginIdAsLong());
        blogService.save(blog);
        return Result.ok("保存成功");
    }

    @PostMapping("/delete")
    public Result deleteBlog(@RequestBody List<Long> ids){
        blogService.removeBatchByIds(ids);
        return Result.ok("删除成功");
    }

    @GetMapping("/page")
    public Result page(QueryBlogParamsRequest query){

        return Result.ok(blogService.listByPage(query));
    }

    @GetMapping()
    public Result getBlogs(){
        List<Blog> list = blogService.listBlogs();

        return Result.ok(list);
    }
}

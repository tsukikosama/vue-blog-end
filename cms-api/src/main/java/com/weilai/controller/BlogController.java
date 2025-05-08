package com.weilai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.weilai.common.PageQuery;
import com.weilai.common.Result;
//import com.weilai.config.ForbiddenWordsLoader;

import com.weilai.entity.Blog;
import com.weilai.entity.BlogLikeEntity;
import com.weilai.entity.BlogTypeEntity;
import com.weilai.entity.Type;
import com.weilai.pojo.BlogPo;
import com.weilai.request.QueryBlogParamsRequest;
import com.weilai.request.SaveBlogRequest;
import com.weilai.service.BlogService;
import com.weilai.service.IBlogTypeService;
import com.weilai.service.TagService;
import com.weilai.service.impl.BlogTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    private final TagService tagService;

    private final IBlogTypeService blogTypeService;

    @GetMapping("/{id}")
    public Result detail(@PathVariable("id") Long id){
        Blog blog = blogService.getById(id);
        //查询当前blog的tag
        List<BlogTypeEntity> list = blogTypeService.list(Wrappers.<BlogTypeEntity>lambdaQuery().eq(BlogTypeEntity::getBlogId, id));
        List<Long> collect = list.stream().map(item -> item.getTagId()).collect(Collectors.toList());
        String tagIds = collect.stream().map(String::valueOf).collect(Collectors.joining(","));
        blog.setTagId(tagIds);
        return Result.ok(blog);
    }


    @PostMapping("/save")
    public Result saveBlog(@RequestBody SaveBlogRequest res){
        Blog blog = BeanUtil.copyProperties(res, Blog.class);
        blog.setVisit(0);
        blogService.save(blog);
        //存储博客和标签关系
        List<BlogTypeEntity> list = new ArrayList<>();
        for (Long tagId : res.getTagId()){
            BlogTypeEntity item = new BlogTypeEntity();
            item.setBlogId(blog.getId());
            item.setTagId(tagId);
            list.add(item);
        }
        blogTypeService.saveBatch(list);
        return Result.ok("保存成功");
    }

    @PostMapping("/update")
    public Result update(@RequestBody SaveBlogRequest res){
        Blog blog = BeanUtil.copyProperties(res, Blog.class);
        blogService.updateById(blog);
        //将之前保存的博客标签清楚 并且重新保存一份
        blogTypeService.removeBatchByBlogId(Arrays.asList(res.getId()));
        List<BlogTypeEntity> list = new ArrayList<>();
        for (Long tagId : res.getTagId()){
            BlogTypeEntity item = new BlogTypeEntity();
            item.setBlogId(blog.getId());
            item.setTagId(tagId);
            list.add(item);
        }
        blogTypeService.saveBatch(list);
        return Result.ok("更新成功");
    }
    @PostMapping("/delete")
    public Result deleteBlog(@RequestBody List<Long> ids){
        blogService.removeBatchByIds(ids);
        //移除相关的标签
        blogTypeService.removeBatchByBlogId(ids);
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

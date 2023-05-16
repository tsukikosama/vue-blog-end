package com.miku.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.common.Result;
import com.miku.entity.Blog;
import com.miku.pojo.BlogPo;

import java.util.List;

public interface BlogService extends IService<Blog> {
    Result getBlogs(Integer current);

    List<Blog> hot();
    //Page<Blog> getBlogs(Integer current, Integer type);
}

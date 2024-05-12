package com.miku.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.common.CommonQuery;
import com.miku.common.Result;
import com.miku.entity.Blog;

import java.util.List;

public interface BlogService extends IService<Blog> {
    Result getBlogs(Integer current);
    Result getBlogs(Integer current,Integer id);
    List<Blog> hot();

    List<Blog> search(String key);

    List<Blog> searchIdAndTitle();

    List<Blog> getBlogByUid(Integer uid);

    Page<Blog> listByPage(CommonQuery query);

    List<Blog> listBlogs();


//    List<Blog> randomBlog();
    //Page<Blog> getBlogs(Integer current, Integer type);
}

package com.weilai.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.api.entity.Blog;
import com.weilai.api.request.QueryBlogParamsRequest;
import com.weilai.api.response.BlogRecordResponse;
import com.weilai.api.response.RecentBlogResponse;

import java.util.List;

public interface BlogService extends IService<Blog> {
//    Result getBlogs(Integer current);
//    Result getBlogs(Integer current,Integer id);
    List<Blog> hot();

    List<Blog> search(String key);

    List<Blog> searchIdAndTitle();

    List<Blog> getBlogByUid(Integer uid);

    IPage<BlogRecordResponse> listByPage(QueryBlogParamsRequest query);

    List<Blog> listBlogs();

    BlogRecordResponse getDetail(Integer id);

    List<RecentBlogResponse> getRecentBlog();


//    List<Blog> randomBlog();
    //Page<Blog> getBlogs(Integer current, Integer type);
}

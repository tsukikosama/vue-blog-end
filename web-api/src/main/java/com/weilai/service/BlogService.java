package com.weilai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.entity.Blog;
import com.weilai.request.QueryBlogParamsRequest;
import com.weilai.response.BlogRecordResponse;
import com.weilai.response.RecentBlogResponse;

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

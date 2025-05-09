package com.weilai.module.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.weilai.module.entity.Blog;
import com.weilai.module.request.QueryBlogParamsRequest;
import com.weilai.module.response.BlogRecordResponse;

public interface BlogService extends IService<Blog> {

    IPage<BlogRecordResponse> listByPage(QueryBlogParamsRequest query);

}

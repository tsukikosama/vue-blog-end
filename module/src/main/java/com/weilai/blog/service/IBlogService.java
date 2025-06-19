package com.weilai.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.blog.model.entity.BlogEntity;
import com.weilai.blog.model.query.BlogQuery;
import com.weilai.blog.model.req.BlogReq;
import com.weilai.blog.model.resp.BlogResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
public interface IBlogService extends IService<BlogEntity> {

    void saveBlog(BlogReq req);

    void updateBlog(BlogReq req);

    void removeBlog(List<Long> id);

    Page<BlogResp> blogPage(BlogQuery req);
}

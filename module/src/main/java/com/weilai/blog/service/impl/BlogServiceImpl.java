package com.weilai.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.blog.mapper.BlogMapper;
import com.weilai.blog.model.entity.BlogEntity;
import com.weilai.blog.model.entity.BlogTagEntity;
import com.weilai.blog.model.req.BlogReq;
import com.weilai.blog.service.IBlogService;
import com.weilai.blog.service.IBlogTagService;
import com.weilai.blog.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@Service
@RequiredArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, BlogEntity> implements IBlogService {

    private final ITagService tagService;

    private final IBlogTagService blogTagService;

    @Transactional
    public void saveBlog(BlogReq req) {
        BlogEntity blog = new BlogEntity();
        BeanUtil.copyProperties(req,blog);
        blog.setCreateUser(StpUtil.getLoginIdAsLong());
        blog.setVisit(0);
        this.baseMapper.insert(blog);
        blogTagService.saveBlogTag(blog.getId(),req.getTagId());
    }

    @Transactional
    @Override
    public void updateBlog(BlogReq req) {
        BlogEntity blog = BeanUtil.copyProperties(req, BlogEntity.class);
        this.baseMapper.updateById(blog);
        blogTagService.saveBlogTag(req.getId(),req.getTagId());
    }

    @Transactional
    @Override
    public void removeBlog(List<Long> ids) {
        this.baseMapper.deleteByIds(ids);
        blogTagService.removeBlogTagRelations(ids);
    }
}

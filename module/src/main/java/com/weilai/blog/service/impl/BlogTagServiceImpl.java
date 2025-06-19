package com.weilai.blog.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.weilai.blog.mapper.BlogTagMapper;
import com.weilai.blog.model.entity.BlogTagEntity;
import com.weilai.blog.service.IBlogTagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTagEntity> implements IBlogTagService {

    /**
     * 保存博客与标签的关系
     * @param id
     * @param tagId
     */
    @Override
    public void saveBlogTag(Long id, List<Long> tagId) {
        removeBlogTagRelations(Arrays.asList(id));
        List<BlogTagEntity> list = new ArrayList<>();
        tagId.forEach(item -> {
            BlogTagEntity blogTagEntity = new BlogTagEntity();
            blogTagEntity.setBlogId(id);
            blogTagEntity.setTagId(item);
            list.add(blogTagEntity);
        });
        this.baseMapper.insert(list);
    }

    @Override
    public void removeBlogTagRelations(List<Long> id){
        this.baseMapper.delete(Wrappers.<BlogTagEntity>lambdaQuery().in(BlogTagEntity::getBlogId,id));
    }

    @Override
    public List<Long> getTagIdByBlogId(Long id) {
        List<BlogTagEntity> blogTagEntities = this.baseMapper.selectList(Wrappers.<BlogTagEntity>lambdaQuery()
                .eq(BlogTagEntity::getBlogId, id));
        return blogTagEntities.stream().map(BlogTagEntity::getBlogId).toList();
    }
}

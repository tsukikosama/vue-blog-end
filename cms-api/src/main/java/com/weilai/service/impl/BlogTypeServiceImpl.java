package com.weilai.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.entity.BlogLikeEntity;
import com.weilai.entity.BlogTypeEntity;
import com.weilai.mapper.BlogTypeMapper;
import com.weilai.service.IBlogTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-05-08
 */
@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogTypeEntity> implements IBlogTypeService {

    @Override
    public void removeBatchByBlogId(List<Long> ids) {
        this.baseMapper.delete(Wrappers.<BlogTypeEntity>lambdaQuery().in(BlogTypeEntity::getBlogId,ids));
    }
}

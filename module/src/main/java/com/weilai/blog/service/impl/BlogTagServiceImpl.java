package com.weilai.blog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.weilai.blog.mapper.BlogTypeMapper;
import com.weilai.blog.model.entity.BlogTypeEntity;
import com.weilai.blog.service.IBlogTagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTypeMapper, BlogTypeEntity> implements IBlogTagService {

}

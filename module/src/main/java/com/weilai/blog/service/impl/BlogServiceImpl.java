package com.weilai.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.blog.mapper.BlogMapper;
import com.weilai.blog.model.entity.BlogEntity;
import com.weilai.blog.service.IBlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, BlogEntity> implements IBlogService {

}

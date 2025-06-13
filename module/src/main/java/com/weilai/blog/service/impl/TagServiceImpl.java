package com.weilai.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.blog.mapper.TagMapper;
import com.weilai.blog.model.entity.TagEntity;
import com.weilai.blog.service.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements ITagService {

}

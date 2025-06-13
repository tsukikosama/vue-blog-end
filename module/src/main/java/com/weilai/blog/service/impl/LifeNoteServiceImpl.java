package com.weilai.blog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.blog.mapper.LifeNoteMapper;
import com.weilai.blog.model.entity.LifeNoteEntity;
import com.weilai.blog.service.ILifeNoteService;
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
public class LifeNoteServiceImpl extends ServiceImpl<LifeNoteMapper, LifeNoteEntity> implements ILifeNoteService {

}

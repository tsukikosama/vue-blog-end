package com.weilai.blog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.blog.mapper.ReviewMapper;
import com.weilai.blog.model.entity.ReviewEntity;
import com.weilai.blog.service.IReviewService;
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
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, ReviewEntity> implements IReviewService {

}

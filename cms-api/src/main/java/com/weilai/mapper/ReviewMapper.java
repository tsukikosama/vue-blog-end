package com.weilai.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.entity.Review;
import com.weilai.response.ReviewResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miku
 * @since 2025-04-14
 */
@Mapper
public interface ReviewMapper extends BaseMapper<Review> {

    Page<ReviewResponse> selectMyPage(@Param("page")Page<ReviewResponse> p, @Param("ew")LambdaQueryWrapper<Review> wrapper);

    List<ReviewResponse> selectChildrenList(@Param("ew")LambdaQueryWrapper<Review> reviewLambdaQueryWrapper);
}

package com.weilai.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.entity.Blog;
import com.weilai.entity.Review;
import com.weilai.response.BlogRecordResponse;
import com.weilai.response.MainReplyResponse;
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

    IPage<ReviewResponse> selectMyPage(@Param("page") Page<ReviewResponse> page, @Param("ew") QueryWrapper<Blog> wrapper);

    List<ReviewResponse> selectChildList(@Param("id")Long id);

    List<MainReplyResponse> getMainReply();
}

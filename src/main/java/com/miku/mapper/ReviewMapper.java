package com.miku.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.common.CommonQuery;
import com.miku.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {

    List<Review> listByPage(CommonQuery query, LambdaQueryWrapper<Review> wrapper);
}

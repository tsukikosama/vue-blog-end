package com.miku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.common.CommonQuery;
import com.miku.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
    List<Feedback> listByPage(@Param("query") CommonQuery query);
}

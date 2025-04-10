package com.weilai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.common.CommonQuery;
import com.weilai.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
    List<Feedback> listByPage(@Param("query") CommonQuery query);
}

package com.miku.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.common.CommonQuery;
import com.miku.entity.Friendlink;
import com.miku.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendLinkMapper extends BaseMapper<Friendlink> {
    List<Friendlink> listByPage(@Param("query") CommonQuery query);
}

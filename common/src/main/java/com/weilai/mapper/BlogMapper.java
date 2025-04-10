package com.weilai.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.common.CommonQuery;
import com.weilai.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> listByPage(CommonQuery query, @Param("wrapper") LambdaQueryWrapper wrapper);
}

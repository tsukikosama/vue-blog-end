package com.miku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}

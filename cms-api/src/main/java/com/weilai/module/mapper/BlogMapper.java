package com.weilai.module.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.module.entity.Blog;
import com.weilai.module.response.BlogRecordResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miku
 * @since 2025-04-14
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    IPage<BlogRecordResponse> selectMyPage(@Param("page")Page<BlogRecordResponse> page , @Param("ew") QueryWrapper wrapper);
}

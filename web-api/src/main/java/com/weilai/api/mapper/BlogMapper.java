package com.weilai.api.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.api.entity.Blog;
import com.weilai.api.response.BlogRecordResponse;
import com.weilai.api.response.RecentBlogResponse;
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
public interface BlogMapper extends BaseMapper<Blog> {

    IPage<BlogRecordResponse> selectMyPage(@Param("page") Page<BlogRecordResponse> page,@Param("ew") QueryWrapper<Blog> wrapper);

    BlogRecordResponse getDetailById(Integer id);

    List<RecentBlogResponse> getRecentBlog();

}

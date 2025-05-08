package com.weilai.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.entity.Blog;
import com.weilai.response.BlogRecordResponse;
import com.weilai.response.RecentBlogResponse;
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

    IPage<BlogRecordResponse> selectMyPage(@Param("page") Page<BlogRecordResponse> page,@Param("ew") LambdaQueryWrapper<Blog> wrapper);

    BlogRecordResponse getDetailById(Integer id);

    List<RecentBlogResponse> getRecentBlog();

}

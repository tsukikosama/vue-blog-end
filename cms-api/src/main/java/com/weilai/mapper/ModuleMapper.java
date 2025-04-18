package com.weilai.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.response.TimeLineResponse;
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
public interface ModuleMapper extends BaseMapper<Module> {

    Page<TimeLineResponse> selectMyPage(@Param("page") Page<TimeLineResponse> p, @Param("ew")LambdaQueryWrapper<Module> wrapper);
}

package com.weilai.module.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.module.entity.AngryPigEntity;
import com.weilai.module.response.AngryPigRecordResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miku
 * @since 2025-05-13
 */
@Mapper
public interface AngryPigMapper extends BaseMapper<AngryPigEntity> {

    IPage<AngryPigRecordResponse> selectMyPage(@Param("page") Page<AngryPigRecordResponse> page,@Param("ew") QueryWrapper<AngryPigEntity> wrapper);

}

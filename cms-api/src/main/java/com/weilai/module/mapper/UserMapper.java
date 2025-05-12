package com.weilai.module.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.module.entity.User;
import com.weilai.module.request.QueryUserParamsRequest;
import com.weilai.module.response.UserRecordResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miku
 * @since 2025-04-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<UserRecordResponse> selectUserPage(@Param("page")Page<UserRecordResponse> page ,@Param("ew") QueryWrapper<User> wrapper);
}

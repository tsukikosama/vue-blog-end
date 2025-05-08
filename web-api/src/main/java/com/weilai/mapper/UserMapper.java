package com.weilai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.entity.User;
import com.weilai.response.UserMainInfoResponse;
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
public interface UserMapper extends BaseMapper<User> {

    UserMainInfoResponse getMainInfo(@Param("id") Long id);
}

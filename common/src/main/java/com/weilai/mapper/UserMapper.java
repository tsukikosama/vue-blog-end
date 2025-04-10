package com.weilai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.common.CommonQuery;
import com.weilai.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User>  {

    Integer selectUserByBid(@Param("bid") Integer bid);

    List<User> listByPage(@Param("query") CommonQuery query);
}

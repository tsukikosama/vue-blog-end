package com.weilai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.entity.Type;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<Type> {
}

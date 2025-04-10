package com.weilai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.common.CommonQuery;
import com.weilai.entity.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModuleMapper extends BaseMapper<Module> {
    List<Module> listByPage(@Param("query")CommonQuery query);
}

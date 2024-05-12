package com.miku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.common.CommonQuery;
import com.miku.entity.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModuleMapper extends BaseMapper<Module> {
    List<Module> listByPage(@Param("query")CommonQuery query);
}

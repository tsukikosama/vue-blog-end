package com.weilai.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.system.model.entity.SysDictEntity;
import com.weilai.system.model.resp.DictResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDictEntity> {

    List<DictResp> getDictValueByCode(@Param("code") String code);
}

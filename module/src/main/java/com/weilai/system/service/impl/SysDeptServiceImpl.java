package com.weilai.system.service.impl;



import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.system.mapper.SysDeptMapper;

import com.weilai.system.model.entity.SysDeptEntity;
import com.weilai.system.service.ISysDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {

    @Override
    public List<SysDeptEntity> getChildDept(Long id) {
        return this.baseMapper.selectList(Wrappers.<SysDeptEntity>lambdaQuery().eq(SysDeptEntity::getParentId, id));
    }
}

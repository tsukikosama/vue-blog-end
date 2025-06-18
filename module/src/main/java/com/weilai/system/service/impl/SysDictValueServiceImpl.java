package com.weilai.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.system.exception.CustomException;
import com.weilai.system.mapper.SysDictValueMapper;
import com.weilai.system.model.entity.SysDictEntity;
import com.weilai.system.model.entity.SysDictValueEntity;
import com.weilai.system.model.resp.DictValueResp;
import com.weilai.system.service.ISysDictValueService;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SysDictValueServiceImpl extends ServiceImpl<SysDictValueMapper, SysDictValueEntity> implements ISysDictValueService {

    private final SysDictServiceImpl sysDictService;
    @Override
    public List<DictValueResp> getDictList(String labelName) {
        SysDictEntity one = sysDictService.getOne(Wrappers.<SysDictEntity>lambdaQuery().eq(SysDictEntity::getCode, labelName));
        if (one == null){
            throw new CustomException("字典值有误");
        }
        List<SysDictValueEntity> sysDictValueEntities = this.baseMapper.selectList(Wrappers.<SysDictValueEntity>lambdaQuery().eq(SysDictValueEntity::getDictId, one.getId()));
        List<DictValueResp> dictValueResps = BeanUtil.copyToList(sysDictValueEntities, DictValueResp.class);
        return dictValueResps;
    }


}

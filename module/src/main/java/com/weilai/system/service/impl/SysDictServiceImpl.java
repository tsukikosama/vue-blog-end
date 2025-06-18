package com.weilai.system.service.impl;


import com.weilai.system.annotation.DictField;
import com.weilai.system.mapper.SysDictMapper;
import com.weilai.system.model.entity.SysDictEntity;
import com.weilai.system.model.resp.DictValueResp;
import com.weilai.system.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.v3.core.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements ISysDictService {



    @Override
    public List<DictValueResp> getDictList(String labelName) {
        return null;
    }
}

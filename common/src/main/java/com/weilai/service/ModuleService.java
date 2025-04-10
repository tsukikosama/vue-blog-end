package com.weilai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.CommonQuery;
import com.weilai.entity.Module;

public interface ModuleService extends IService<Module> {
    String savaModule(Module module);

    Page<Module> listByPage(CommonQuery query);
}

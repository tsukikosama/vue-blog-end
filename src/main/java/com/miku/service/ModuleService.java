package com.miku.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.common.CommonQuery;
import com.miku.entity.Module;

public interface ModuleService extends IService<Module> {
    String savaModule(Module module);

    Page<Module> listByPage(CommonQuery query);
}

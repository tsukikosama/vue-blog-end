package com.weilai.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;

public interface ModuleService extends IService<Module> {
    String savaModule(Module module);

    Page<Module> listByPage(PageQuery query);
}

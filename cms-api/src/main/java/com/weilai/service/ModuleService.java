package com.weilai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;
import com.weilai.request.QueryModuleParamsRequest;
import com.weilai.response.TimeLineResponse;

public interface ModuleService extends IService<Module> {
    String savaModule(Module module);

    Page<Module> listByPage(PageQuery query);
    Page<TimeLineResponse> page(QueryModuleParamsRequest request);
}

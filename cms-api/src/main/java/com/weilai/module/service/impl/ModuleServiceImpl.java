package com.weilai.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.PageQuery;

import com.weilai.module.mapper.ModuleMapper;
import com.weilai.module.request.QueryModuleParamsRequest;
import com.weilai.module.response.TimeLineResponse;
import com.weilai.module.service.ModuleService;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {
    /**
     * 添加模块功能
     * @param module
     * @return
     */
    @Override
    public String savaModule(Module module) {

        boolean bool = this.save(module);
        if (!bool){
            return "添加失败";
        }
        return "添加成功";
    }

    @Override
    public Page<Module> listByPage(PageQuery query) {
//        Page<Module> page = new Page<>(query.getPageNum(), query.getPageSize());
        System.out.println(query);
//        List<Module> modules = this.baseMapper.listByPage(query);
//        //通过uid查询用户信息
//        page.setRecords(modules);
//        page.setTotal(modules.size());
        return null;
    }

    @Override
    public Page<TimeLineResponse> page(QueryModuleParamsRequest request) {
        Page<TimeLineResponse> p = new Page(request.getCurrent(),request.getPageSize());
        LambdaQueryWrapper<Module> wrapper = new LambdaQueryWrapper<>();
        Page<TimeLineResponse> page = this.baseMapper.selectMyPage(p, wrapper);
        return page;
    }
}

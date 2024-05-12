package com.miku.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.common.CommonQuery;
import com.miku.entity.Module;
import com.miku.entity.User;
import com.miku.mapper.ModuleMapper;
import com.miku.service.ModuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {
    /**
     * 添加模块功能
     * @param module
     * @return
     */
    @Override
    public String savaModule(Module module) {
        module.setDate(DateUtil.date().toString());
        boolean bool = this.save(module);
        if (!bool){
            return "添加失败";
        }
        return "添加成功";
    }

    @Override
    public Page<Module> listByPage(CommonQuery query) {
        Page<Module> page = new Page<>(query.getPageNum(), query.getPageSize());
        System.out.println(query);
        List<Module> modules = this.baseMapper.listByPage(query);
        //通过uid查询用户信息
        page.setRecords(modules);
        page.setTotal(modules.size());
        return page;
    }
}

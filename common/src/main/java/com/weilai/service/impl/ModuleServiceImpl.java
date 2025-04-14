package com.weilai.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.CommonQuery;
import com.weilai.mapper.ModuleMapper;
import com.weilai.service.ModuleService;
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
//        List<Module> modules = this.baseMapper.listByPage(query);
//        //通过uid查询用户信息
//        page.setRecords(modules);
//        page.setTotal(modules.size());
        return page;
    }
}

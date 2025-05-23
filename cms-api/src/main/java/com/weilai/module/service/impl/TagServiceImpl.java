package com.weilai.module.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.weilai.module.entity.Type;
import com.weilai.module.mapper.TypeMapper;
import com.weilai.module.request.QueryTypeParamsRequest;
import com.weilai.module.response.TypeResponse;
import com.weilai.module.service.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TypeMapper, Type> implements TagService {


    //通过页数来查询tag的数量
    @Override
    public Page<Type> getTagsPage(Integer current) {
        Page<Type> page = new Page<>(current,8);
        long total = count();
        //处理数据 如果超过最大的条数就换成最大条数
        List<Type> types = list().subList((current - 1) * 8, (current - 1) * 8 + 8 > total ? (int) total : (current - 1) * 8 + 8);
        page.setRecords(types);
        page.setTotal(total);
        page.setCurrent(current);

        System.out.println(page);
        return page;
    }

    @Override
    public List<Type> getTageById(Integer tagid) {
        LambdaQueryWrapper<Type> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Type::getId,tagid);
        List<Type> tags = list(wrapper);
        return tags;
    }

    /**
     * 这个方法是通过字符串的tagid来获取标签的名字
     * @param tagid
     * @return
     */
    @Override
    public List<Type> getTagNameByTagid(String tagid){
        //传过来的字符串是通过,来划分的 每个都是tagid
        String[] split = tagid.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        //来存储获取的type
        ArrayList<Type> types = new ArrayList<>();
        for(String i : split){
            list.add(Integer.parseInt(i));
        }
        list.stream().forEach((item) -> {
            Type tag = this.getById(item);
            types.add(tag);
        });
        return types;
    }

    @Override
    public List<TypeResponse> selectList() {
        List<Type> types = this.baseMapper.selectList(Wrappers.<Type>lambdaQuery().select(Type::getId, Type::getTagName));
        List<TypeResponse> list = BeanUtil.copyToList(types, TypeResponse.class);
        return list;
    }

    @Override
    public Page<TypeResponse> page(QueryTypeParamsRequest request) {
        Page p = new Page(request.getCurrent(),request.getPageSize());
        LambdaQueryWrapper<Type> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(request.getTagName())){
            wrapper.eq(Type::getTagName,request.getTagName());
        }
        Page page  = this.baseMapper.selectPage(p, wrapper);
        return page;
    }

}

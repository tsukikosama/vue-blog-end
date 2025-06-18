package com.weilai.system.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PageUtils {
    public static <T, R> Page<R> build(Page<T> sourcePage, Class<R> targetClass) {
        List<R> targetList = sourcePage.getRecords().stream()
                .map(item -> BeanUtil.copyProperties(item, targetClass))
                .collect(Collectors.toList());

        Page<R> resultPage = new Page<>();
        resultPage.setRecords(targetList);
        resultPage.setTotal(sourcePage.getTotal());
        resultPage.setSize(sourcePage.getSize());
        resultPage.setCurrent(sourcePage.getCurrent());
        resultPage.setPages(sourcePage.getPages());

        return resultPage;
    }
}

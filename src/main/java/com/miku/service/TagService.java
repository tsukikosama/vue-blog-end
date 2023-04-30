package com.miku.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.entity.Type;

import java.util.List;

public interface TagService extends IService<Type> {
    Page<Type> getTagsPage(Integer current);

    List<Type> getTageById(Integer tagid);
}

package com.weilai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.entity.Type;

import java.util.List;

public interface TagService extends IService<Type> {
    Page<Type> getTagsPage(Integer current);

    List<Type> getTageById(Integer tagid);

    List<Type> getTagNameByTagid(String tagid);


}

package com.weilai.module.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.module.entity.Type;
import com.weilai.module.request.QueryTypeParamsRequest;
import com.weilai.module.response.TypeResponse;


import java.util.List;

public interface TagService extends IService<Type> {
    Page<Type> getTagsPage(Integer current);

    List<Type> getTageById(Integer tagid);

    List<Type> getTagNameByTagid(String tagid);


    List<TypeResponse> selectList();

    Page<TypeResponse> page(QueryTypeParamsRequest request);

}

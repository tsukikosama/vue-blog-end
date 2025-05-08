package com.weilai.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.entity.BlogTypeEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miku
 * @since 2025-05-08
 */
public interface IBlogTypeService extends IService<BlogTypeEntity> {

    void removeBatchByBlogId(List<Long> ids);
}

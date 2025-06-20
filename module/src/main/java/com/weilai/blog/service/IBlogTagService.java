package com.weilai.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.blog.model.entity.BlogTagEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
public interface IBlogTagService extends IService<BlogTagEntity> {

    void saveBlogTag(Long id, List<Long> tagId);

    void removeBlogTagRelations(List<Long> id);

    List<Long> getTagIdByBlogId(Long id);

    boolean isUseTag(Long item);
}

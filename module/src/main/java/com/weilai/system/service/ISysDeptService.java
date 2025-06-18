package com.weilai.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.system.model.entity.SysDeptEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
public interface ISysDeptService extends IService<SysDeptEntity> {

    List<SysDeptEntity> getChildDept(Long item);
}

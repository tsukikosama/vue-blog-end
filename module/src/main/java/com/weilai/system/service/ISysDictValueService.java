package com.weilai.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.system.model.entity.SysDictValueEntity;
import com.weilai.system.model.resp.DictValueResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
public interface ISysDictValueService extends IService<SysDictValueEntity> {

    List<DictValueResp> getDictList(String labelName);
}

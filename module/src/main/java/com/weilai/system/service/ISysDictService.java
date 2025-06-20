package com.weilai.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.system.model.entity.SysDictEntity;
import com.weilai.system.model.resp.DictResp;
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
public interface ISysDictService extends IService<SysDictEntity> {


    List<DictValueResp> getDictList(String labelName);

    List<DictResp> getDictValueByCode(String code);
}

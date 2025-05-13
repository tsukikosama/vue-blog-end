package com.weilai.module.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.module.entity.AngryPigEntity;
import com.weilai.module.request.QueryAngryPigParamsRequest;
import com.weilai.module.response.AngryPigRecordResponse;
import com.weilai.module.response.BlogRecordResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miku
 * @since 2025-05-13
 */
public interface IAngryPigService extends IService<AngryPigEntity> {

    IPage<AngryPigRecordResponse> listByPage(QueryAngryPigParamsRequest query);
}

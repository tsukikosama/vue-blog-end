package com.weilai.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.module.entity.AngryPigEntity;
import com.weilai.module.entity.Blog;
import com.weilai.module.mapper.AngryPigMapper;
import com.weilai.module.request.QueryAngryPigParamsRequest;
import com.weilai.module.response.AngryPigRecordResponse;
import com.weilai.module.response.BlogRecordResponse;
import com.weilai.module.service.IAngryPigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-05-13
 */
@Service
public class AngryPigServiceImpl extends ServiceImpl<AngryPigMapper, AngryPigEntity> implements IAngryPigService {

    @Override
    public IPage<AngryPigRecordResponse> listByPage(QueryAngryPigParamsRequest query) {
        QueryWrapper<AngryPigEntity> wrapper = new QueryWrapper<>();
        Page<AngryPigRecordResponse> page = new Page<>(query.getCurrent(), query.getPageSize());
        IPage<AngryPigRecordResponse> blogPage = baseMapper.selectMyPage(page, wrapper);
        return blogPage;
    }
}

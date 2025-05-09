package com.weilai.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.module.entity.Blog;
import com.weilai.module.entity.BlogLikeEntity;
import com.weilai.module.entity.BlogTypeEntity;
import com.weilai.module.mapper.BlogMapper;
import com.weilai.module.request.QueryBlogParamsRequest;
import com.weilai.module.service.BlogService;
import com.weilai.module.service.TagService;
import com.weilai.module.service.UserService;
import com.weilai.module.response.BlogRecordResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final TagService tagService;


    private final UserService userService;

    private final BlogTypeServiceImpl blogTypeService;
    @Override
    public IPage<BlogRecordResponse> listByPage(QueryBlogParamsRequest query ) {

        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("cb.is_valid",1);
        Page<BlogRecordResponse> page = new Page<>(query.getCurrent(), query.getPageSize());
        IPage<BlogRecordResponse> blogPage = baseMapper.selectMyPage(page, wrapper);
        //
        blogPage.getRecords().stream().forEach(item -> {
            List<BlogTypeEntity> list = blogTypeService.list(Wrappers.<BlogTypeEntity>lambdaQuery().eq(BlogTypeEntity::getBlogId, item.getId()));
            String result = list.stream()
                    .map(i -> String.valueOf(i.getTagId()))
                    .collect(Collectors.joining(","));
            item.setTagId(result);
        });
        return blogPage;
    }









}

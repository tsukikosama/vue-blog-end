package com.weilai.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.module.entity.Blog;
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

@Slf4j
@Service
@AllArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final TagService tagService;

    @Autowired
    private final UserService userService;


    @Override
    public IPage<BlogRecordResponse> listByPage(QueryBlogParamsRequest query ) {

        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();

        Page<BlogRecordResponse> page = new Page<>(query.getCurrent(), query.getPageSize());
        IPage<BlogRecordResponse> blogPage = baseMapper.selectMyPage(page, wrapper);

        return blogPage;
    }









}

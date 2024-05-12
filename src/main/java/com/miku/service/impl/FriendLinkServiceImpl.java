package com.miku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.common.CommonQuery;
import com.miku.entity.Friendlink;
import com.miku.entity.Review;
import com.miku.mapper.FriendLinkMapper;
import com.miku.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, Friendlink> implements FriendService {
    @Override
    public List<Friendlink> getFriendLink(Integer offset) {
        LambdaQueryWrapper<Friendlink> wrapper = new LambdaQueryWrapper<>();
        wrapper.last("limit "+offset+",3");
        List<Friendlink> list = list(wrapper);
        return list;
    }

    @Override
    public Page<Friendlink> listByPage(CommonQuery query) {


        Page<Friendlink> page = new Page<>(query.getPageNum(), query.getPageSize());

        List<Friendlink> blogs = this.baseMapper.listByPage(query);

        page.setRecords(blogs);
        page.setTotal(blogs.size());
        return page;
    }
}

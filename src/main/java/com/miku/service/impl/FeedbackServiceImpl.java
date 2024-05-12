package com.miku.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.common.CommonQuery;
import com.miku.entity.Feedback;
import com.miku.entity.User;
import com.miku.mapper.FeedbackMapper;
import com.miku.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Override
    public Page<Feedback> listByPage(CommonQuery query) {
        Page<Feedback> page = new Page<>(query.getPageNum(), query.getPageSize());
        System.out.println(query);
        List<Feedback> user = this.baseMapper.listByPage(query);
        //通过uid查询用户信息
        page.setRecords(user);
        page.setTotal(user.size());
        return page;
    }
}

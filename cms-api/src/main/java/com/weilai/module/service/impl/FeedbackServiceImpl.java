package com.weilai.module.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.PageQuery;

import com.weilai.module.entity.Feedback;
import com.weilai.module.mapper.FeedbackMapper;
import com.weilai.module.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Override
    public Page<Feedback> listByPage(PageQuery query) {
//        Page<Feedback> page = new Page<>(query.getPageNum(), query.getPageSize());
        System.out.println(query);
//        List<Feedback> user = this.baseMapper.listByPage(query);
        //通过uid查询用户信息
//        page.setRecords(user);
//        page.setTotal(user.size());
        return null;
    }
}

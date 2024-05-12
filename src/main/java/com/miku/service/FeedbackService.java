package com.miku.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.common.CommonQuery;
import com.miku.entity.Feedback;

public interface FeedbackService extends IService<Feedback> {
    Page<Feedback> listByPage(CommonQuery query);

}

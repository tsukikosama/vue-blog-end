package com.weilai.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;
import com.weilai.entity.Feedback;

public interface FeedbackService extends IService<Feedback> {
    Page<Feedback> listByPage(PageQuery query);

}

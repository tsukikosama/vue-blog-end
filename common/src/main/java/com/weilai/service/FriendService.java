package com.weilai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.CommonQuery;
import com.weilai.entity.Friendlink;

import java.util.List;


public interface FriendService extends IService<Friendlink> {
    List<Friendlink> getFriendLink(Integer offset);

    Page<Friendlink> listByPage(CommonQuery query);

}

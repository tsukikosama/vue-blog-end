package com.miku.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.common.CommonQuery;
import com.miku.entity.Friendlink;

import java.util.List;


public interface FriendService extends IService<Friendlink> {
    List<Friendlink> getFriendLink(Integer offset);

    Page<Friendlink> listByPage(CommonQuery query);

}

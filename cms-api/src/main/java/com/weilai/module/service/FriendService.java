package com.weilai.module.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;
import com.weilai.module.entity.Friendlink;


import java.util.List;


public interface FriendService extends IService<Friendlink> {
    List<Friendlink> getFriendLink(Integer offset);

    Page<Friendlink> listByPage(PageQuery query);

}

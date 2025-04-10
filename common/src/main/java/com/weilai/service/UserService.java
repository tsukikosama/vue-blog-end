package com.weilai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.CommonQuery;
import com.weilai.entity.User;
import com.weilai.pojo.Userpo;

public interface UserService extends IService<User>  {
    Userpo login(User user);

    Page<User> getUser(String username, Integer current);

    Boolean addUser(User user);

    String register(User user, String code);

    String registerForEmail(User user , String code);

    User getOneById(Integer uid);

    Userpo banUser(String email);

    Userpo unBan(String email,Integer uid);


    Page<User> listByPage(CommonQuery query);
}

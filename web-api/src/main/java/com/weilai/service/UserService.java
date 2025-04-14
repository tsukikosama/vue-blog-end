package com.weilai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;
import com.weilai.entity.User;
import com.weilai.request.RegisterUserRequest;


public interface UserService extends IService<User>  {


    Page<User> getUser(String username, Integer current);

    Boolean addUser(User user);

    String register(RegisterUserRequest request);

    String registerForEmail(User user , String code);

    User getOneById(Long uid);




    Page<User> listByPage(PageQuery query);
}

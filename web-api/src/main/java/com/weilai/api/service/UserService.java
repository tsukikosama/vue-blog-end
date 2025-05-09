package com.weilai.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.api.entity.User;
import com.weilai.api.request.RegisterUserRequest;
import com.weilai.api.response.UserMainInfoResponse;
import com.weilai.common.PageQuery;



public interface UserService extends IService<User>  {


    Page<User> getUser(String username, Integer current);

    Boolean addUser(User user);

    String register(RegisterUserRequest request);

    String registerForEmail(User user , String code);

    User getOneById(Long uid);




    Page<User> listByPage(PageQuery query);

    UserMainInfoResponse getMainInfo();

}

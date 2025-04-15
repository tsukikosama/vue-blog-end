package com.weilai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;
import com.weilai.entity.User;
import com.weilai.request.QueryUserParamsRequest;
import com.weilai.request.RegisterUserRequest;

import java.util.List;


public interface UserService extends IService<User>  {


    Page<User> getUser(QueryUserParamsRequest request);

    Boolean addUser(User user);

    String register(RegisterUserRequest request);

    String registerForEmail(User user , String code);

    User getOneById(Long uid);




    Page<User> listByPage(PageQuery query);

    void resetPwd(List<Long> ids);
}

package com.miku.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.entity.User;
import com.miku.pojo.Userpo;

import java.util.List;

public interface UserService extends IService<User> {
    Userpo login(User user);

    Page<User> getUser(String username, Integer current);

    Boolean addUser(User user);

    String register(User user, String code);
}

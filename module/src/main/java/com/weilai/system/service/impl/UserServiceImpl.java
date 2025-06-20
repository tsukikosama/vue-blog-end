package com.weilai.system.service.impl;


import com.weilai.blog.mapper.CustomerMapper;
import com.weilai.blog.model.entity.CustomerEntity;
import com.weilai.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<CustomerMapper, CustomerEntity> implements IUserService {

}

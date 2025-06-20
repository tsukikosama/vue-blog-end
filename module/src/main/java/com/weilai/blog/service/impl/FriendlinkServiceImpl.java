package com.weilai.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.blog.mapper.FriendlinkMapper;
import com.weilai.blog.model.entity.FriendlinkEntity;
import com.weilai.blog.service.IFriendlinkService;
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
public class FriendlinkServiceImpl extends ServiceImpl<FriendlinkMapper, FriendlinkEntity> implements IFriendlinkService {

    @Override
    public void checkUrlIsAccessible(Long[] ids) {

    }

}

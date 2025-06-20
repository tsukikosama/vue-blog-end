package com.weilai.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.blog.model.entity.FriendlinkEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
public interface IFriendlinkService extends IService<FriendlinkEntity> {

    void checkUrlIsAccessible(Long[] ids);
}

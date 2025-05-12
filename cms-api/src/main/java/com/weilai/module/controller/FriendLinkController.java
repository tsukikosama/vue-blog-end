package com.weilai.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.Result;
import com.weilai.module.entity.Blog;
import com.weilai.module.entity.FriendlinkEntity;
import com.weilai.module.request.QueryFriendLinkParamsRequest;
import com.weilai.module.response.BlogRecordResponse;
import com.weilai.module.service.impl.FriendLinkServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-05-12
 */
@RestController
@RequestMapping("/friendLink")
@AllArgsConstructor
public class FriendLinkController {

    private final FriendLinkServiceImpl friendLinkService;
    @GetMapping("/page")
    public Result page(QueryFriendLinkParamsRequest query){
        QueryWrapper<FriendlinkEntity> wrapper = new QueryWrapper<>();
        Page<FriendlinkEntity> page = new Page<>(query.getCurrent(), query.getPageSize());
        return Result.ok(friendLinkService.page(page, wrapper));
    }
}

package com.weilai.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.Result;
import com.weilai.module.entity.FriendlinkEntity;
import com.weilai.module.entity.LifeNoteEntity;
import com.weilai.module.request.QueryFriendLinkParamsRequest;
import com.weilai.module.request.QueryLifeNoteParamsRequest;
import com.weilai.module.service.ILifeNoteService;
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
 * @since 2025-05-15
 */
@RestController
@RequestMapping("/lifeNote")
@AllArgsConstructor
public class LifeNoteController {

    private final ILifeNoteService lifeNoteService;
    @GetMapping("/page")
    public Result page(QueryLifeNoteParamsRequest query){
        QueryWrapper<LifeNoteEntity> wrapper = new QueryWrapper<>();
        Page<LifeNoteEntity> page = new Page<>(query.getCurrent(), query.getPageSize());
        return Result.ok(lifeNoteService.page(page, wrapper));
    }

}

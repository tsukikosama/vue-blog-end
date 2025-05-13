package com.weilai.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.Result;
import com.weilai.module.entity.Blog;
import com.weilai.module.entity.FriendlinkEntity;
import com.weilai.module.entity.User;
import com.weilai.module.request.QueryFriendLinkParamsRequest;
import com.weilai.module.response.BlogRecordResponse;
import com.weilai.module.service.impl.FriendLinkServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.weilai.enums.WebValidEnum.VALID_URL;

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

    @GetMapping("/detail/{id}")
    public Result check(@PathVariable("id") Long id){
        //查看详情
        FriendlinkEntity entity = friendLinkService.getById(id);
        return Result.ok(entity);
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id")Long id){
        friendLinkService.removeById(id);
        return Result.ok("删除成功");
    }
    @PostMapping("/save")
    public Result save(@RequestBody FriendlinkEntity friendlinkEntity){
        friendlinkEntity.setWebAccess(VALID_URL.getCode());
        friendLinkService.save(friendlinkEntity);
        return Result.ok();
    }
    @PostMapping("/update")
    public Result update(@RequestBody FriendlinkEntity friendlinkEntity){
        friendLinkService.updateById(friendlinkEntity);
        return Result.ok();
    }
    @ApiOperation("获取友链详情")
    @GetMapping("/detail")
    public Result getUserDetail(@RequestParam("id") Long id){
        FriendlinkEntity record = friendLinkService.getById(id);
        return Result.ok(record);
    }
}

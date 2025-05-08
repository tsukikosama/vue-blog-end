package com.weilai.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.weilai.common.Result;
import com.weilai.entity.BlogLikeEntity;
import com.weilai.service.BlogLikeService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.weilai.constante.RedisConstante.BLOG_LIKE;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/blogLike")
@AllArgsConstructor
public class BlogLikeController {
    private final BlogLikeService blogLikeService;

    private final StringRedisTemplate stringRedisTemplate;

    @PostMapping("/like/{id}")
    public Result like(@PathVariable("id") Long id){
        //获取用户id
        long userId = StpUtil.getLoginIdAsLong();
        //判断用户是否点赞过该博客
        Set<String> members = stringRedisTemplate.opsForSet().members(BLOG_LIKE + id);
        if (members.contains(userId)){
            //如果用户点赞过就从redis中移除该记录
            stringRedisTemplate.opsForSet().remove(BLOG_LIKE + id,userId);
        }else{
            //没有存在该记录 判断数据库中是否有该条记录
            BlogLikeEntity one = blogLikeService.getOne(Wrappers.<BlogLikeEntity>lambdaQuery().eq(BlogLikeEntity::getBlogId, id).eq(BlogLikeEntity::getUserId, userId));
            if (one != null){
                //数据库中存在点赞记录 就把该记录移除
                blogLikeService.removeById(one);
            }else{
                //不存在 就把当前的记录存入redis中
                stringRedisTemplate.opsForSet().add(BLOG_LIKE + id,userId+"");
            }
        }
        return Result.ok("点赞成功");
    }
}

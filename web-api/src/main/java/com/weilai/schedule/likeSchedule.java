package com.weilai.schedule;

import com.weilai.entity.BlogLikeEntity;
import com.weilai.service.BlogLikeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weilai.constante.RedisConstante.BLOG_LIKE;

@Component
@Slf4j
@AllArgsConstructor
public class likeSchedule {

    private final StringRedisTemplate stringRedisTemplate;

    private final BlogLikeService blogLikeService;

    // 每天凌晨 1 点执行（cron 表达式）
    @Scheduled(cron = "0 * * * * ?")
    public void runAtOneAM() {
        log.info("=======存储点赞数据任务开始=======");
        //开启定时任务
        Set<String> result = stringRedisTemplate.execute((RedisConnection connection) -> {
            Set<String> keys = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(
                    ScanOptions.scanOptions().match(BLOG_LIKE + "*").count(1000).build()
            );
            while (cursor.hasNext()) {
                keys.add(new String(cursor.next()));
            }
            return keys;
        });
        //便利匹配到的key
        List<BlogLikeEntity> blogLikeEntities = new ArrayList<>();
        for (String key : result) {
            Set<String> members = stringRedisTemplate.opsForSet().members(key);
            //匹配到后面的博客id
            Pattern pattern = Pattern.compile("BLOG:LIKE:(\\d+)");
            System.out.println(key);
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                String blogId = matcher.group(1);
                //将对应的key和value存入数据库中
                for (String userId : members) {
                    BlogLikeEntity item = new BlogLikeEntity();
                    item.setBlogId(Long.parseLong(blogId));
                    item.setUserId(Long.parseLong(userId));
                    blogLikeEntities.add(item);
                }
                //将该key删除
                stringRedisTemplate.delete(key);
            }
        }

        //将全部的点赞数量存入数据库
        blogLikeService.saveBatch(blogLikeEntities);
        log.info("=======存储点赞数据任务结束=======");
        log.info("本次一共存储点赞数量{},影响的博客个数{}", blogLikeEntities.size(), result.size());
    }
}

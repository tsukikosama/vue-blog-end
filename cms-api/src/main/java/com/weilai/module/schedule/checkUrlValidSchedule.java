package com.weilai.module.schedule;

import com.weilai.module.entity.FriendlinkEntity;
import com.weilai.module.service.IFriendLinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weilai.constante.RedisConstante.BLOG_LIKE;
import static com.weilai.enums.WebValidEnum.FAILURE_URL;

@Component
@Slf4j
@AllArgsConstructor
public class checkUrlValidSchedule {

    private final IFriendLinkService friendLinkService;

    private final RestTemplate restTemplate = new RestTemplate();
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkValid() {
        log.info("======开始检查网址是否有效======");
        List<FriendlinkEntity> list = friendLinkService.list();
        int count = 0;
        for (FriendlinkEntity item : list){
            try {
                ResponseEntity<String> resp = restTemplate.getForEntity(item.getWebUrl(), String.class);
                if (!resp.getStatusCode().is2xxSuccessful()){
                    //不是200
                    item.setWebAccess(FAILURE_URL.getCode());
                    count++;
                }
            }catch (Exception e){
                item.setWebAccess(FAILURE_URL.getCode());
                count++;
            }
        }
        friendLinkService.updateBatchById(list);
        log.info("本次一共检测{}个网址 失效的网址数量为{}",list.size(),count);
    }
}

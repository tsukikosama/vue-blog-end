package com.weilai.controller;

import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.entity.Friendlink;
import com.weilai.service.FriendService;
import com.weilai.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendlink")
public class FriendLinkController {
    @Autowired
    private FriendService service;

    @Autowired
    private MailService mailService;
    @Value("${spring.mail.username}")
    private String myEmail;
    @GetMapping("/get")
    public Result getLink(@RequestParam("offset")Integer offset){
        List<Friendlink> list = service.getFriendLink(offset);
        return Result.ok(list);
    }

    @GetMapping("/all")
    public Result getall(){
        return Result.ok(service.list());
    }

    @PostMapping("/save")
    public Result saveLink(@RequestBody Friendlink friendlink){

        service.saveOrUpdate(friendlink);
        mailService.sendTips(myEmail,"有人申请友链拉,快去查看一下吧~");
        return Result.ok("保存成功");
    }
    @PostMapping("/del")
    public Result delLink(@RequestParam("id") Integer id){
        boolean success = service.removeById(id);
        return Result.ok("删除成功");
    }
    @GetMapping("/list")
    public Result list(PageQuery query){
        return Result.ok(service.listByPage(query));
    }
}

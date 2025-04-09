package com.miku.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.dfa.WordTree;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.miku.common.CommonQuery;
import com.miku.common.Result;
import com.miku.config.ForbiddenWordsLoader;
import com.miku.entity.Blog;

import com.miku.entity.Type;
import com.miku.pojo.BlogPo;
import com.miku.service.BlogService;
import com.miku.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService service;

    @Autowired
    private TagService tagService;

    /**
     * 注入违禁词过滤器
     */
    @Autowired
    private  ForbiddenWordsLoader forbiddenWordsLoader;
    @GetMapping("/{id}")
    public Result getBlog(@PathVariable("id")Integer id){
        Blog blog = service.getById(id);
        blog.setVisit(blog.getVisit()+1);
        service.updateById(blog);
        return Result.ok(blog);
    }
    //通过tagid来查询页面  可以不穿就是查询所有
    @GetMapping("/pages")
    public Result getBlogs(@RequestParam(value = "current",defaultValue = "1",required = false)Integer current,
        @RequestParam(value = "tagid",required = false)Integer id
    ){
        if (id != null){
            return  service.getBlogs(current,id);
        }else{
        }
        return  service.getBlogs(current);
    }

    @PostMapping("/add")
    public Result addBlog(@RequestBody Blog blog){
        System.out.println("666");
        System.out.println(blog.getIsValid());
        DateTime now = DateTime.now();
        blog.setCreateDate(now.toDateStr());
        boolean match = forbiddenWordsLoader.checkWord(blog.getContent());
        if (match){
            return Result.fail("内容不合法请修改后再提交");
        }
        boolean isSuccess = service.saveOrUpdate(blog);
        if (!isSuccess){
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }
    @PostMapping("/update")
    public Result update(@RequestBody Blog blog){
        System.out.println(blog);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getBid,blog.getBid());
        boolean success = service.update(blog,wrapper);
        if (!success){
            return Result.fail("更新失败");
        }
        return Result.ok();
    }

    @PostMapping("/del")
    public Result del(@RequestParam("bid") Integer bid){

        boolean success = service.removeById(bid);
        if (!success){
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }

    /**
     * 获取热门博客
     * @return
     */
    @GetMapping("hotblog")
    public Result hot(){
        List<Blog> list = service.hot();
        return Result.ok(list);
    }

    /**
     * 通过搜索查询博客
     * @return
     */
    @GetMapping("search")
    public Result SearchBlog(@RequestParam("key") String key){
            List<Blog> list = service.search(key);
            return Result.ok(list);
    }

    @GetMapping("searchtitle")
    public Result SearchBolgIdAndTitle(){
        List<Blog> list = service.searchIdAndTitle();
        return Result.ok(list);
    }

    /**
     * 通过用户id来查询对应用户发布的博客
     * @param uid 用户id
     * @return
     */
    @GetMapping("myblog")
    public Result GetBlogByUid(@RequestParam("uid") Integer uid){
        List<Blog> list = service.getBlogByUid(uid);
        List<BlogPo> po = new ArrayList<>();
        list.stream().forEach((item) -> {
            BlogPo blogPo = BeanUtil.copyProperties(item, BlogPo.class);
            String tid = item.getTid();
            List<Type> tags = tagService.getTagNameByTagid(tid);
            blogPo.setTagname(tags);
            po.add(blogPo);
        });
        return Result.ok(po);
    }


    @GetMapping("randomblog")
    public Result RandomBlog(){

        List<Blog> list = service.list();

        Set<Blog> set = new HashSet<Blog>();
        int index ;
        while(set.size() < 5){
            index = RandomUtil.randomInt(0,list.size()-1);
            set.add(list.get(index));
        }
        return Result.ok(set);
    }

    @GetMapping("/list")
    public Result getBlogByPage(CommonQuery query){

        return Result.ok(service.listByPage(query));
    }

    @GetMapping()
    public Result getBlogs(){
        List<Blog> list = service.listBlogs();

        return Result.ok(list);
    }
}

package com.weilai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.config.ForbiddenWordsLoader;

import com.weilai.entity.Blog;
import com.weilai.entity.Type;
import com.weilai.pojo.BlogPo;
import com.weilai.request.QueryBlogParamsRequest;
import com.weilai.request.SaveBlogRequest;
import com.weilai.service.BlogService;
import com.weilai.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;


    private final TagService tagService;


    private final ForbiddenWordsLoader forbiddenWordsLoader;
    @GetMapping("/{id}")
    public Result getBlog(@PathVariable("id")Integer id){
        Blog blog = blogService.getById(id);
        blog.setVisit(blog.getVisit()+1);
        blogService.updateById(blog);
        return Result.ok(blog);
    }
    //通过tagid来查询页面  可以不穿就是查询所有
    @GetMapping("/pages")
    public Result getBlogs(@RequestParam(value = "current",defaultValue = "1",required = false)Integer current,
                           @RequestParam(value = "tagid",required = false)Integer id
    ){
        if (id != null){
            return  blogService.getBlogs(current,id);
        }else{
        }
        return  blogService.getBlogs(current);
    }

    @PostMapping("/save")
    public Result saveBlog(@RequestBody SaveBlogRequest res){
        System.out.println(res);
        Blog blog = BeanUtil.copyProperties(res, Blog.class);
        blog.setVisit(0);
        String result = StringUtils.join(res.getTagId(), ",");
        blog.setTagId(result);
        blog.setUserId(StpUtil.getLoginIdAsLong());
        blogService.save(blog);
        return Result.ok("保存成功");
    }
    @PostMapping("/add")
    public Result addBlog(@RequestBody Blog blog){
        DateTime now = DateTime.now();
        boolean match = forbiddenWordsLoader.checkWord(blog.getContent());
        if (match){
            return Result.fail("内容不合法请修改后再提交");
        }
        boolean isSuccess = blogService.saveOrUpdate(blog);
        if (!isSuccess){
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }
    @PostMapping("/update")
    public Result update(@RequestBody Blog blog){
        System.out.println(blog);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getId,blog.getId());
        boolean success = blogService.update(blog,wrapper);
        if (!success){
            return Result.fail("更新失败");
        }
        return Result.ok();
    }

    @PostMapping("/del")
    public Result del(@RequestParam("bid") Integer bid){

        boolean success = blogService.removeById(bid);
        if (!success){
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }

    /**
     * 获取热门博客
     * @return
     */
    @GetMapping("/hotblog")
    public Result hot(){
        List<Blog> list = blogService.hot();
        return Result.ok(list);
    }

    /**
     * 通过搜索查询博客
     * @return
     */
    @GetMapping("search")
    public Result SearchBlog(@RequestParam("key") String key){
        List<Blog> list = blogService.search(key);
        return Result.ok(list);
    }

    @GetMapping("searchtitle")
    public Result SearchBolgIdAndTitle(){
        List<Blog> list = blogService.searchIdAndTitle();
        return Result.ok(list);
    }

    /**
     * 通过用户id来查询对应用户发布的博客
     * @param uid 用户id
     * @return
     */
    @GetMapping("myblog")
    public Result GetBlogByUid(@RequestParam("uid") Integer uid){
        List<Blog> list = blogService.getBlogByUid(uid);
        List<BlogPo> po = new ArrayList<>();
        list.stream().forEach((item) -> {
            BlogPo blogPo = BeanUtil.copyProperties(item, BlogPo.class);
            String tid = item.getTagId();
            List<Type> tags = tagService.getTagNameByTagid(tid);
            blogPo.setTagname(tags);
            po.add(blogPo);
        });
        return Result.ok(po);
    }


    @GetMapping("randomblog")
    public Result RandomBlog(){

        List<Blog> list = blogService.list();

        Set<Blog> set = new HashSet<Blog>();
        int index ;
        while(set.size() < 5){
            index = RandomUtil.randomInt(0,list.size()-1);
            set.add(list.get(index));
        }
        return Result.ok(set);
    }

    @GetMapping("/list")
    public Result getBlogByPage(QueryBlogParamsRequest query){

        return Result.ok(blogService.listByPage(query));
    }

    @GetMapping()
    public Result getBlogs(){
        List<Blog> list = blogService.listBlogs();

        return Result.ok(list);
    }
}

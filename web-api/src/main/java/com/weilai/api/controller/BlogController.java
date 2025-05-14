package com.weilai.api.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.weilai.api.entity.Blog;
import com.weilai.common.Result;


import com.weilai.api.request.QueryBlogParamsRequest;
import com.weilai.api.response.RecentBlogResponse;
import com.weilai.api.service.BlogService;
import com.weilai.api.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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


    @GetMapping("/detail/{id}")
    public Result getBlog(@PathVariable("id")Integer id){
        return Result.ok(blogService.getDetail(id));
    }
    //通过tagid来查询页面  可以不穿就是查询所有
    @GetMapping("/page")
    public Result page(QueryBlogParamsRequest query){
        return Result.ok(blogService.listByPage(query));
    }

    @PostMapping("/add")
    public Result addBlog(@RequestBody Blog blog){
        blogService.save(blog);
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



    @GetMapping()
    public Result getBlogs(){
        List<Blog> list = blogService.listBlogs();

        return Result.ok(list);
    }

    /**
     * 查看最近的博客
     * @return
     */
    @GetMapping("/recentBlog")
    public Result getRecentBlog(){
        List<RecentBlogResponse> res = blogService.getRecentBlog();
        return Result.ok(res);
    }

}

package com.miku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.common.Result;
import com.miku.entity.Blog;
import com.miku.entity.Type;
import com.miku.mapper.BlogMapper;
import com.miku.pojo.BlogPo;
import com.miku.service.BlogService;
import com.miku.service.TagService;
import com.miku.utils.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    private TagService tagService;
    /**
     * 分页查询blog
     * @param current
     * @return
     */
    @Override
    public Result getBlogs(Integer current) {
        //查询所有blog
        Page<Blog> page = query().page(new Page<>(current, SystemConstants.BLOG_PAGE_SIZE));
        //获取当前页数
//        List<Blog> records = page.getRecords();
        System.out.println(page);
        System.out.println(count());
        page.setTotal(count());
        List<Blog> records = page.getRecords();

        List<Blog> blogs = records.stream().map((item) -> {
            //获取tid
            String tid = item.getTid();
            //获取截取获取typeid然后去查询
            String[] split = tid.split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for (String k : split) {
                list.add(Integer.parseInt(k));
            }
            //通过typeid查询type
            List<Type> types = tagService.listByIds(list).stream().collect(Collectors.toList());
            item.setTag(types);
            return item;
        }).collect(Collectors.toList());
        //把封装好的对象存入页面
        page.setRecords(blogs);
        return Result.ok(page);
    }

    /**
     * 获取热门博客
     * @return
     */
    @Override
    public List<Blog> hot() {
        //构建条件wrapper
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Blog::getVisit);
        QueryWrapper<Blog> f = new QueryWrapper<>();
//        f.select()
        wrapper.select(Blog::getBid,Blog::getTitle, Blog::getCreateDate);
        wrapper.last("limit 5");
        List<Blog> list = list(wrapper);
        System.out.println(list);
        return list;
    }

    /**
     * //判断是否有更具类型查询blog
     * @param current
     * @param type
     * @return
     */
   /* @Override
    public Page<Blog> getBlogs(Integer current, Integer type) {
        //查询所有blog
        Page<Blog> page = query().like("tid",type).page(new Page<>(current, SystemConstants.BLOG_PAGE_SIZE));

        //获取当前页数
//        List<Blog> records = page.getRecords();
//        System.out.println(page);
        page.setTotal(page.getTotal());
        List<Blog> records = page.getRecords();

        List<Blog> blogs = records.stream().map((item) -> {
            //获取tid
            String tid = item.getTid();
            //获取截取获取typeid然后去查询
            String[] split = tid.split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for (String k : split) {
                list.add(Integer.parseInt(k));
            }
            //通过typeid查询type
            List<Type> types = tagService.listByIds(list).stream().collect(Collectors.toList());
            item.setTag(types);
            return item;
        }).collect(Collectors.toList());
        //把封装好的对象存入页面
        page.setRecords(blogs);
        return page;
    }*/
}

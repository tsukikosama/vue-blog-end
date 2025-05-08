package com.weilai.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.entity.Blog;
import com.weilai.entity.BlogLikeEntity;
import com.weilai.entity.Type;
import com.weilai.entity.User;
import com.weilai.mapper.BlogMapper;
import com.weilai.request.QueryBlogParamsRequest;
import com.weilai.response.BlogRecordResponse;
import com.weilai.response.RecentBlogResponse;
import com.weilai.service.BlogLikeService;
import com.weilai.service.BlogService;
import com.weilai.service.TagService;
import com.weilai.service.UserService;
import com.weilai.utils.SystemConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.weilai.constante.RedisConstante.BLOG_LIKE;

@Slf4j
@Service
@AllArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final TagService tagService;

    @Autowired
    private final UserService userService;

    private final BlogLikeService blogLikeService;

    private final StringRedisTemplate stringRedisTemplate;
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
//        System.out.println(page);
//        System.out.println(count());
        page.setTotal(count());
        List<Blog> records = page.getRecords();

        List<Blog> blogs = records.stream().map((item) -> {
            //获取tid
            String tid = item.getTagId();
            //获取截取获取typeid然后去查询
            String[] split = tid.split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for (String k : split) {
                list.add(Integer.parseInt(k));
            }
            //通过typeid查询type
            List<Type> types = tagService.listByIds(list).stream().collect(Collectors.toList());


            //通过uid去查询用户信息
            User u = userService.getById(item.getUserId());

            return item;
        }).collect(Collectors.toList());
        //把封装好的对象存入页面
        page.setRecords(blogs);
        return Result.ok(page);
    }
    //通过id来查询不同的类型
    @Override
    public Result getBlogs(Integer current, Integer id) {
        //查询所有blog
        Page<Blog> page = query().like("tid",id.toString()).page(new Page<>(current, SystemConstants.BLOG_PAGE_SIZE));

        //获取当前页数
//        List<Blog> records = page.getRecords();
//        System.out.println(page);
//        System.out.println(count());
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Blog::getId,id);
        page.setTotal(count(wrapper));
        List<Blog> records = page.getRecords();

        List<Blog> blogs = records.stream().map((item) -> {
            //获取tid
            String tid = item.getTagId();
            //获取截取获取typeid然后去查询
            String[] split = tid.split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for (String k : split) {
                list.add(Integer.parseInt(k));
            }
            //通过typeid查询type
            List<Type> types = tagService.listByIds(list).stream().collect(Collectors.toList());
//            item.setTag(types);
//
//            //通过uid去查询用户信息
//            User u = userService.getById(item.getUid());
//            Userpo userpo = BeanUtil.copyProperties(u, Userpo.class);
//            item.setUser(userpo);
            return item;
        }).collect(Collectors.toList());

        //通过uid把用户的信息存进去

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
//        QueryWrapper<Blog> f = new QueryWrapper<>();
//        f.select();
        wrapper.select(Blog::getId,Blog::getTitle, Blog::getCreateDate);
        wrapper.last("limit 5");
        List<Blog> list = list(wrapper);
        System.out.println(list);
        return list;
    }

    @Override
    public List<Blog> search(String key) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        //通过关键字查询
        wrapper.like(Blog::getTitle,key);
        List<Blog> list = list(wrapper);
        System.out.println(list);
        return list;
    }

    /**
     * 查询博客的id和title
     * @return
     */
    @Override
    public List<Blog> searchIdAndTitle() {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Blog::getTitle,Blog::getId);
        List<Blog> list = this.list(wrapper);
        return list;
    }

    /**
     * 通过用户id来查询发布的博客
     * @param uid
     * @return
     */
    @Override
    public List<Blog> getBlogByUid(Integer uid) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getId, uid).eq(Blog::getIsValid,1);

        List<Blog> list = list(wrapper);

        return list;
    }

    @Override
    public IPage<BlogRecordResponse> listByPage(QueryBlogParamsRequest query) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();

        Page<BlogRecordResponse> page = new Page<>(query.getCurrent(), query.getPageSize());
        IPage<BlogRecordResponse> blogPage = this.baseMapper.selectMyPage(page, wrapper);
        //变量全部的博客记录判断用户是否点赞过
        for (BlogRecordResponse blogRecordResponse : blogPage.getRecords().stream().collect(Collectors.toList())) {
            BlogLikeEntity one = blogLikeService.getOne(Wrappers.<BlogLikeEntity>lambdaQuery().eq(BlogLikeEntity::getBlogId, blogRecordResponse.getId()).eq(BlogLikeEntity::getUserId, StpUtil.getLoginIdAsLong()));
            if (one != null){
                blogRecordResponse.setIsLike(1);
            }else{
                //如果为空 就代表数据库中没有存储改记录 查看redis中是否有存储
                Boolean member = stringRedisTemplate.opsForSet().isMember(BLOG_LIKE + blogRecordResponse.getId(), StpUtil.getLoginId());
                if (member){
                    //如果改记录存在在redis中
                    blogRecordResponse.setIsLike(1);
                }else{
                    blogRecordResponse.setIsLike(0);
                }
            }
            //判断redis中没有存入数据库的点赞书数量
            Set<String> members = stringRedisTemplate.opsForSet().members(BLOG_LIKE + blogRecordResponse.getId());
            blogRecordResponse.setLikes(blogRecordResponse.getLikes() + members.size());
        }

        return blogPage;
    }


    @Override
    public List<Blog> listBlogs() {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getIsValid,1);
        List<Blog> list = list(wrapper);
        totalBlog(list);
        return list;
    }

    @Override
    public BlogRecordResponse getDetail(Integer id) {
        BlogRecordResponse res = this.baseMapper.getDetailById(id);
        return res;
    }

    @Override
    public  List<RecentBlogResponse> getRecentBlog() {

        return this.baseMapper.getRecentBlog();
    }

    public void totalBlog(List<Blog> blogs) {
        blogs.stream().forEach(item ->{
            User u = userService.getOneById(item.getId());
//            Userpo userpo = BeanUtil.copyProperties(u, Userpo.class);
//            item.setUser(userpo);
//            String[] split = item.getTid().split(",");
//            List<Type> list = new ArrayList<Type>();
//            System.out.println(split.length);
//            //查询标签
//            for (int i = 0 ; i < split.length ;i++){
//                Type type = tagService.getById(Integer.parseInt(split[i]));
//                list.add(type);
//            }
//            item.setTag(list);
        });
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

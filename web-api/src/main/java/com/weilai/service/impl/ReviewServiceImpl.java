package com.weilai.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.PageQuery;
import com.weilai.entity.Blog;
import com.weilai.entity.Review;
import com.weilai.mapper.ReviewMapper;
import com.weilai.mapper.UserMapper;
import com.weilai.request.QueryReviewParamsRequest;
import com.weilai.request.ReplyRequest;
import com.weilai.response.BlogRecordResponse;
import com.weilai.response.ReviewResponse;
import com.weilai.service.ReviewService;
import com.weilai.service.UserService;
import com.weilai.utils.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.weilai.enums.ReviewEnum.CHILD_REVIEW;
import static com.weilai.enums.ReviewEnum.MAIN_REVIEW;
import static com.weilai.utils.RedisConstants.IS_LIKE;

@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;


    /**
     * 通过博客的id来查询所有的评论
     * @param bid
     * @return
     */
    @Override
    public List<Review> findAllForBid(Integer bid) {
        LambdaQueryWrapper<Review> wrapper = new LambdaQueryWrapper<Review>();

        wrapper.eq(Review::getBlogId, bid).eq(Review::getReply, 0);
        //全部的评论字段
        List<Review> list = list(wrapper);
        //获取全部的评论 查看他们是否有回复
//        List<Integer> ids = new ArrayList<>();
        wrapper.clear();

        List<Review> replys = new ArrayList<>();
        for(Review r : list){


            wrapper.eq(Review::getReplyId,r.getId()).orderBy(true,true,Review::getLikes);

            replys = list(wrapper);

//            r.setReplyId(replys);
            wrapper.clear();
        }
        System.out.println(list);
        return list;
    }

    /**
     * 判断用户是否点赞
     * @param bid
     * @param uid
     * @return
     */
    @Override
    public String islike(String bid ,String uid,Integer rid) {
        //拼接redis的key
        String key = IS_LIKE + bid + ":" + uid ;
        //判断redis是否存在改数据
        Double score = redisTemplate.opsForZSet().score(key,rid.toString());

        //先从redis中看看这个用户是否点赞过 如果不存在就是没有点赞过
        if (score == null){
            //不存在这个用户 就把这个用户添加并且+1赞

            redisTemplate.opsForZSet().add(key,rid.toString(),System.currentTimeMillis());
            //更新点赞数
            update().setSql("likes = likes +1").eq("id",rid).update();
            return "更新成功";
        }
        //存在的情况 吧用户从集合中移除
        redisTemplate.opsForZSet().remove(key,rid.toString());
        update().setSql("likes = likes - 1").eq("id",rid).update();
        return "更新成功";
    }

    @Override
    public Set<String> likes(String bid, String uid) {
        String key = IS_LIKE + bid + ":" + uid ;
        //获取全部的数据
        Set<String> range = redisTemplate.opsForZSet().range(key, 0, -1);
        System.out.println(range);
        return range;
    }

    @Override
    public String  addRevice(ReplyRequest request) {
        Review review = new Review();
        review.setLikes(0);
        review.setContent(request.getContent());
        review.setBlogId(request.getBlogId());
        review.setUserId(StpUtil.getLoginIdAsLong());
        //判断是否有评论id
        if (request.getReplyId() != null){
            //有评论id代表是回复
            review.setReviewType(CHILD_REVIEW.getCode());
            review.setReplyId(request.getReplyId());
        }else{
            review.setReviewType(MAIN_REVIEW.getCode());
        }
        this.baseMapper.insert(review);
        return "回复成功";
    }

    @Override
    public List<Review> getReplyByBid(Integer bid) {
        LambdaQueryWrapper<Review> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Review::getBlogId,bid);
        List<Review> list = list(wrapper);
        return list;
    }

    @Override
    public Page<Review> listByPage(PageQuery query) {
        LambdaQueryWrapper<Review> wrapper = new LambdaQueryWrapper<>();

//        Page<Review> page = new Page<>(query.getPageNum(), query.getPageSize());

//        List<Review> blogs = this.baseMapper.listByPage(query, wrapper);
//        //通过uid查询用户信息
//        totalReview(blogs);
//        page.setRecords(blogs);
//        page.setTotal(blogs.size());
        return null;
    }

    @Override
    public List<Review> listPage() {
        List<Review> list = list();
        totalReview(list);
        return list;
    }

    @Override
    public IPage<ReviewResponse> pageByBlogId(QueryReviewParamsRequest query) {
        Page<ReviewResponse> page = new Page<>(query.getCurrent(), query.getPageSize());
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq(query.getId() != null,"cr.blog_id",query.getId());
        wrapper.eq("cr.review_type",MAIN_REVIEW.getCode());
        IPage<ReviewResponse> blogPage = this.baseMapper.selectMyPage(page, wrapper);
        for (ReviewResponse item : blogPage.getRecords()){
            //获取全部的子评论
           List<ReviewResponse> list =  this.baseMapper.selectChildList(item.getId());
           item.setChildList(list);
        }
        return blogPage;
    }

    public void totalReview(List<Review> list){
//        list.stream().forEach(item -> {
//            item.set(userService.getById(item.getUid()));
//        });
    }
}

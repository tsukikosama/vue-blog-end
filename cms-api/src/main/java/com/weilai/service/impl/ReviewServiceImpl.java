package com.weilai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.config.ForbiddenWordsLoader;
import com.weilai.entity.Review;
import com.weilai.mapper.ReviewMapper;
import com.weilai.mapper.UserMapper;
import com.weilai.service.ReviewService;
import com.weilai.service.UserService;
import com.weilai.utils.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private ForbiddenWordsLoader forbiddenWordsLoader;

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
    public String  addRevice(Review review) {
        review.setLikes(0);
        //
        Boolean check = forbiddenWordsLoader.checkWord(review.getContent());
        if (check){
            return "有非法词";
        }
        boolean flag = this.save(review);


        //通过bid去查询用户
//        Integer uid = userMapper.selectUserByBid(review.getBlogId());

//        System.out.println("uid"+uid);
//        //通过这个uid去查询博客的邮箱;
//        User user = userService.getOneById(uid);
//        mailService.sendMsg(user.getEmail());
        //发送邮件提示有人评论了
        if(!flag){
            return "回复失败，请稍后再试";
        }

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

    public void totalReview(List<Review> list){
//        list.stream().forEach(item -> {
//            item.set(userService.getById(item.getUid()));
//        });
    }


}

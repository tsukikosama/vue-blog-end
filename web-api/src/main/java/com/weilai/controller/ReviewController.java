package com.weilai.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.entity.Review;
import com.weilai.request.QueryReviewParamsRequest;
import com.weilai.request.ReplyRequest;
import com.weilai.response.BlogRecordResponse;
import com.weilai.response.ReviewResponse;
import com.weilai.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.weilai.enums.ReviewEnum.CHILD_REVIEW;
import static com.weilai.enums.ReviewEnum.MAIN_REVIEW;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 评论接口
     * @param request
     * @return
     */
    @PostMapping("/reply")
    public Result addReview(@RequestBody ReplyRequest request){
        reviewService.addRevice(request);
        return Result.ok("评论成功");
    }
    @PostMapping("delreview")
    public Result delReview(@RequestParam("rid") Integer rid){
        boolean success = reviewService.removeById(rid);
        if(!success){
            return Result.fail("删除失败请稍后再试");
        }
        return Result.ok("删除成功");
    }
    @GetMapping("/findall")
    public Result findAllReview(@RequestParam("bid")Integer bid){
        List<Review> list = reviewService.findAllForBid(bid);
        return Result.ok(list);
    }

    /**
     * 判断用户是否点赞
     * @return
     */
    @PostMapping("/islike")
    public Result islike(@RequestParam("bid") String bid, @RequestParam("uid")String uid,@RequestParam("rid")Integer rid){
        System.out.println(bid + uid);
        String msg = reviewService.islike(bid,uid,rid);
        return Result.ok(msg);
    }

    /**
     * 查询博客点赞的用户
     * @param bid 博客id
     * @param uid 用户id
     * @return
     */
    @GetMapping("/likes")
    public Result likes(@RequestParam("bid")String bid,@RequestParam("uid")String uid) {
        Set<String> list =  reviewService.likes(bid,uid);
       return Result.ok(list);
    }

    /**
     * 通过博客id获取评论
     * @param bid
     * @return
     */
    @GetMapping("/getreplybybid")
    public Result GetReplyByBid(@RequestParam("bid") Integer bid){
        List<Review> list = reviewService.getReplyByBid(bid);
        return Result.ok(list);
    }

    @GetMapping("/all")
    public Result getall(){
        return Result.ok(reviewService.listPage());
    }

    @GetMapping("/list")
    public Result list(PageQuery query){
        return Result.ok(reviewService.listByPage(query));
    }


    @GetMapping("/page")
    public Result page(QueryReviewParamsRequest request){
        IPage<ReviewResponse> page  = reviewService.pageByBlogId(request);
        return Result.ok(page);
    }

}

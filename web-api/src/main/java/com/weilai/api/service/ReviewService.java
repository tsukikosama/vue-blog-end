package com.weilai.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.api.entity.Review;
import com.weilai.api.request.QueryReviewParamsRequest;
import com.weilai.api.request.ReplyRequest;
import com.weilai.api.response.MainReplyResponse;
import com.weilai.api.response.ReviewResponse;
import com.weilai.common.PageQuery;



import java.util.List;
import java.util.Set;

public interface ReviewService extends IService<Review> {
    List<Review> findAllForBid(Integer bid);

    String islike(String bid, String uid,Integer rid);

    Set<String> likes(String bid, String uid);

    String addRevice(ReplyRequest review);

    List<Review> getReplyByBid(Integer bid);

    Page<Review> listByPage(PageQuery query);

    List<Review> listPage();

    IPage<ReviewResponse> pageByBlogId(QueryReviewParamsRequest request);

    List<MainReplyResponse> getMainReply();
}

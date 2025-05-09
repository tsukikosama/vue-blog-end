package com.weilai.module.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.PageQuery;
import com.weilai.module.entity.Review;
import com.weilai.module.request.QueryReviewParamsRequest;
import com.weilai.module.response.ReviewResponse;


import java.util.List;
import java.util.Set;

public interface ReviewService extends IService<Review> {
    List<Review> findAllForBid(Integer bid);

    String islike(String bid, String uid,Integer rid);

    Set<String> likes(String bid, String uid);

    String addRevice(Review review);

    List<Review> getReplyByBid(Integer bid);

    Page<Review> listByPage(PageQuery query);

    List<Review> listPage();

    Page<ReviewResponse> page(QueryReviewParamsRequest request);

    void deleteById(Long id);
}

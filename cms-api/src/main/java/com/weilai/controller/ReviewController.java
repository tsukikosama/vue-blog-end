package com.weilai.controller;

import com.weilai.common.Result;
import com.weilai.request.QueryReviewParamsRequest;
import com.weilai.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/page")
    public Result page(QueryReviewParamsRequest request){
        System.out.println(request);
        return Result.ok(reviewService.page(request));
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        reviewService.deleteById(id);
        return Result.ok("删除成功");
    }
}

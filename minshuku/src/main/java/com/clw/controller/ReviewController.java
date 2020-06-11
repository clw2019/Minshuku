package com.clw.controller;

import com.clw.pojo.ReviewParams;
import com.clw.service.ReviewService;
import com.clw.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/28 11:48
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    /**
     * 添加评论
     * @param params
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public CommonResult addComment(@RequestBody ReviewParams params) {
        CommonResult result = reviewService.addComment(params);
        return result;
    }

    @GetMapping(value = "/hostReview")
    public CommonResult hostReview(@RequestParam("reviewId") Integer reviewId, @RequestParam("reviewContent") String reviewContent) {
        CommonResult result = reviewService.hostReview(reviewId, reviewContent);
        return result;
    }
}

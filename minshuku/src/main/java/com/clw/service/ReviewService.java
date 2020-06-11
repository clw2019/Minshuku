package com.clw.service;

import com.clw.domain.Review;
import com.clw.pojo.AdminReview;
import com.clw.pojo.ReviewParams;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;

public interface ReviewService{


    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);

    CommonResult addComment(ReviewParams params);

    CommonResult hostReview(Integer reviewId, String reviewContent);

    CommonResult<CommonPage<AdminReview>> getReviewInfo(Integer pageNum, Integer pageSize, String input);
}

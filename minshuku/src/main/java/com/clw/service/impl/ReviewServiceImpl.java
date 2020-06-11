package com.clw.service.impl;

import com.clw.pojo.AdminReview;
import com.clw.pojo.ReviewParams;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.domain.Review;
import com.clw.mapper.ReviewMapper;
import com.clw.service.ReviewService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService{

    @Resource
    private ReviewMapper reviewMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Review record) {
        return reviewMapper.insert(record);
    }

    @Override
    public int insertSelective(Review record) {
        return reviewMapper.insertSelective(record);
    }

    @Override
    public Review selectByPrimaryKey(Integer id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Review record) {
        return reviewMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Review record) {
        return reviewMapper.updateByPrimaryKey(record);
    }

    @Override
    public CommonResult addComment(ReviewParams params) {
        Review review = new Review();
        review.setId(null);
        review.setOrderId(params.getOrderId());
        review.setUserId(params.getUserId());
        review.setHomeId(params.getHomeId());
        review.setUserReview(params.getUserReview());
        review.setHostReview(null);
        review.setRate(params.getRate());
        review.setCreateTime(new Date());
        review.setUpdateTime(new Date());
        int i = reviewMapper.insertSelective(review);
        return i==1 ? CommonResult.success(i) : CommonResult.fail(100, "评论失败");
    }

    @Override
    public CommonResult hostReview(Integer reviewId, String reviewContent) {
        Integer result = reviewMapper.hostReview(reviewId, reviewContent);
        System.out.println("回复评论结果 ---------- " + result);
        return result == 1 ? CommonResult.success(result) : CommonResult.fail(100, "回复失败，请稍后重试...");
    }

    @Override
    public CommonResult<CommonPage<AdminReview>> getReviewInfo(Integer pageNum, Integer pageSize, String input) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdminReview> reviewList = reviewMapper.getReviewInfo(input);
        return reviewList == null ? CommonResult.fail(100, "暂无评论") : CommonResult.success(CommonPage.resultPage(reviewList));
    }

}

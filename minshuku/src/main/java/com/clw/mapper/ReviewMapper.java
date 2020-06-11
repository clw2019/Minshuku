package com.clw.mapper;

import com.clw.domain.Review;
import com.clw.pojo.AdminReview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);

    Integer hostReview(@Param("reviewId") Integer reviewId, @Param("reviewContent") String reviewContent);

    List<AdminReview> getReviewInfo(@Param("input") String input);
}
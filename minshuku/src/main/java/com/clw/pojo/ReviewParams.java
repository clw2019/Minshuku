package com.clw.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/28 11:50
 */
@Getter
@Setter
@ToString
public class ReviewParams {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 房屋ID
     */
    private Integer homeId;

    /**
     * 评论内容
     */
    private String userReview;

    /**
     * 评分
     */
    private BigDecimal rate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
}

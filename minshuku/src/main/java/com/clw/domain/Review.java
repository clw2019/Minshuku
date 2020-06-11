package com.clw.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Review {
    private Integer id;

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
    * 房东回复
    */
    private String hostReview;

    /**
     * 评分
     */
    private BigDecimal rate;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
}
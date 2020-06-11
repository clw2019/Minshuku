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
 * @Date: 2020/3/19 16:30
 */
@Getter
@Setter
@ToString
public class ReviewPojo {
    private Integer id;

//    /**
//     * 用户ID
//     */
//    private Integer userId;
    private String nick;

    private String icon;

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
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date updateTime;
}

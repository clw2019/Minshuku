package com.clw.pojo;

import com.clw.domain.Img;
import com.clw.domain.OrderInfo;
import com.clw.domain.Review;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/19 18:17
 */
@Getter
@Setter
@ToString
public class OrderInfoResponse extends OrderInfo{
    private String title;
    private List<Img> picList;
    private List<Review> reviewList;
}

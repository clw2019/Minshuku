package com.clw.pojo;

import com.clw.domain.Review;
import com.clw.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/22 19:50
 */
@Getter
@Setter
@ToString
public class OrderInfoDetail extends OrderInfoResponse  {
    private User user;
}

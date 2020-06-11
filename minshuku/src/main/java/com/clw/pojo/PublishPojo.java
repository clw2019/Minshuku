package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author: clw
 * @Description: 接收发布房屋的实体类
 * @Date: 2020/5/6 11:46
 */
@Getter
@Setter
@ToString
public class PublishPojo {
    private Integer homeId;
    private Integer hostId;
    private String title;
    private String describe;
    private BigDecimal price;
    private String area;
    private Integer livepeople;
    private String address;
    private String detailAddress;
    private Integer suite;
    private Integer bedroom;
    private Integer livingroom;
    private Integer kitchen;
    private Integer toilet;
    private String img;
}

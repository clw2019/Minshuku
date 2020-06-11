package com.clw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Carousel {
    /**
    * 轮播图ID
    */
    private Integer id;

    /**
    * 房屋ID
    */
    private Integer homeId;

    /**
    * 链接地址
    */
    private String link;

    /**
    * 图片URL
    */
    private String imgurl;
}
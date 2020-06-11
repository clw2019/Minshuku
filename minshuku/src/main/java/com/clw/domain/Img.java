package com.clw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Img {
    /**
    * 图片ID
    */
    private Integer id;

    /**
    * 房屋ID
    */
    private Integer homeId;

    /**
    * 图片路径
    */
    private String imgurl;
}
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
public class Home {
    /**
     * 房屋ID
     */
    private Integer id;

    /**
     * 房东ID
     */
    private Integer hostId;

    /**
     * 标题
     */
    private String title;

    /**
     * 房屋描述
     */
    private String describe;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 地址
     */
    private String address;

    /**
     * 面积（㎡）
     */
    private String area;

    /**
     * 可以居住几人
     */
    private Integer livepeople;

    /**
     * 收藏数量
     */
    private Integer collection;

    /**
     * 0（未审核）
     * 1（审核通过）
     */
    private Integer status;

    /**
     * 是否加入轮播
     * 0：否，1：是
     */
    private Integer flag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
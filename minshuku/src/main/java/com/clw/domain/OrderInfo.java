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
public class OrderInfo {
    private Integer id;

    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 房屋ID
    */
    private Integer homeId;

    /**
    * 房屋地址
    */
    private String address;

    /**
    * 入住时间
    */
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date startTime;

    /**
    * 离开时间
    */
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date endTime;

    /**
    * 入住人数
    */
    private Integer liveNum;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 单位：天
    */
    private Integer liveDays;

    /**
    * 总共支付
    */
    private BigDecimal countPrice;

    /**
    * 联系方式
    */
    private String phoneNum;

    /**
    * 租客姓名
    */
    private String liveName;

    /**
    * 证件类型
    */
    private String type;

    /**
    * 证件号码
    */
    private String idNum;

    /**
    * 状态：0 未入住；1 进行中；2已完成
    */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
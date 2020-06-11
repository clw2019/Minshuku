package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/9 10:24
 */
@Getter
@Setter
@ToString
public class OrderInfoParams {
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

//    /**
//     * 入住时间
//     */
//    private Date startTime;
//
//    /**
//     * 离开时间
//     */
//    private Date endTime;
    private List<Date> date;

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
    private Integer countDay;

    /**
     * 总共支付
     */
    private BigDecimal countPay;

    /**
     * 联系方式
     */
    private String phoneNum;

    /**
     * 租客姓名
     */
    private String name;

    /**
     * 证件类型
     */
    private String type;

    /**
     * 证件号码
     */
    private String idCard;

    /**
     * 状态：0 未入住；1 进行中；2已完成
     */
//    private Integer status;
    private Integer enableLive;
}

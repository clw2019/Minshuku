package com.clw.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Collect {
    private Integer id;

    /**
     * 房屋ID
     */
    private Integer homeId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
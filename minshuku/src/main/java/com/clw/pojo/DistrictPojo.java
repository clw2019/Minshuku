package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/23 21:12
 */
@Getter
@Setter
@ToString
public class DistrictPojo {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 父类id
     */
    private Integer pid;

    /**
     * 城市的名字
     */
    private String districtName;

    /**
     * 城市的类型，0是国，1是省，2是市，3是区
     */
    private Integer type;

    private List<DistrictPojo> districtList;
}

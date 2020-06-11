package com.clw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeDetail {
    private Integer id;

    private Integer homeId;

    /**
    * 是否是整套房：0否，1是
    */
    private Integer suite;

    /**
    * 卧室数量
    */
    private Integer bedroom;

    /**
    * 厅
    */
    private Integer livingroom;

    /**
    * 是否有厨房：0没有，1有
    */
    private Integer kitchen;

    /**
    * 是否有厕所：0没有，1有
    */
    private Integer toilet;
}
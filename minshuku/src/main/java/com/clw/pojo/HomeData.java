package com.clw.pojo;

import com.clw.domain.Collect;
import com.clw.domain.Img;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class HomeData {
    /**
     * 房屋ID
     */
    private Integer id;

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


    /**
     * 图片List
     */
    private List<Img> urlList;

    /**
     * 该房屋被哪些用户收藏了，放在一个list
     */
    private List<Integer> collectList;
}

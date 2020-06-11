package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Author: clw
 * @Description: 根据地址和时间进行查询，暂时未用
 * @Date: 2020/5/7 17:58
 */
@Getter
@Setter
@ToString
public class SelectHomePojo {
    private List<String> address;
    private List<Date> date;
}

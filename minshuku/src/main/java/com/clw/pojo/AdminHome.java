package com.clw.pojo;

import com.clw.domain.Home;
import com.clw.domain.HomeDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/3 20:11
 */
@Getter
@Setter
@ToString
public class AdminHome extends Home {
    private String hostName;
    private HomeDetail homeDetail;
    private List<String> imageList;
}

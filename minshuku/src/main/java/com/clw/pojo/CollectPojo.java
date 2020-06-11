package com.clw.pojo;

import com.clw.domain.Home;
import com.clw.domain.Img;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/27 12:22
 */
@Getter
@Setter
@ToString
public class CollectPojo extends Home{
    private List<Img> urlList;
    private List<Integer> homeIdList;
}

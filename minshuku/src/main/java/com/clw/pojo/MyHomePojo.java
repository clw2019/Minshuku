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
 * @Date: 2020/5/6 17:45
 */
@Getter
@Setter
@ToString
public class MyHomePojo extends Home {
    private List<Img> imgList;
}

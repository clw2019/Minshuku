package com.clw.pojo;

import com.clw.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class HomeDetailData extends HomeData {

    private List<ReviewPojo> reviewList;

    private BigDecimal avgRate;

    private List<User> hostList;

    private List<Map<String, Date>> timeList;
}

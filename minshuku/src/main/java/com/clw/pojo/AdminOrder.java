package com.clw.pojo;

import com.clw.domain.OrderInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/4 14:40
 */
@Getter
@Setter
@ToString
public class AdminOrder extends OrderInfo {
    private String guestName;
    private String hostName;
}

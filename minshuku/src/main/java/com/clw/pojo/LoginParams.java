package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/14 10:36
 */
@Getter
@Setter
@ToString
public class LoginParams {
    private String username;
    private String password;
}

package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/2 15:37
 */
@Getter
@Setter
@ToString
public class AdminLogin {
    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}

package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/28 17:50
 */
@Getter
@Setter
@ToString
public class UpdateUserParams {
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String phonenumber;

    private String icon;
}

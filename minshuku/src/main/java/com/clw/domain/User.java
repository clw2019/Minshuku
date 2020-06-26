package com.clw.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Integer id;

    /**
    * 用户名称
    */
    private String username;

    /**
    * 用户密码
    */
    @JsonIgnore
    private String password;

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

    /**
    * 头像
    */
    private String icon;

    /**
    * 账户类型：0用户，1房东和用户
    */
    private Integer type;

    /**
    * 账号状态：0启用，1禁用
    */
    private Integer status;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
    * 更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
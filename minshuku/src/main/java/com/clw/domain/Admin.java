package com.clw.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Admin {
    private Integer id;

    /**
    * 用户名称
    */
    private String username;

    /**
    * 用户密码
    */
    private String password;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;
}
package com.clw.service;

import com.clw.domain.Admin;
import com.clw.pojo.AdminLogin;
import com.clw.utils.CommonResult;

public interface AdminService{


    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    CommonResult login(AdminLogin params);
}

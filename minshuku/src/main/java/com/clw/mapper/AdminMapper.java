package com.clw.mapper;

import com.clw.domain.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByPrimaryUserName(@Param("username") String username);
}
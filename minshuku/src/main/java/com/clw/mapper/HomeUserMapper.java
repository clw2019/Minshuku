package com.clw.mapper;

import com.clw.domain.HomeUser;

public interface HomeUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomeUser record);

    int insertSelective(HomeUser record);

    HomeUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeUser record);

    int updateByPrimaryKey(HomeUser record);
}
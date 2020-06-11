package com.clw.service;

import com.clw.domain.HomeDetail;
public interface HomeDetailService{


    int deleteByPrimaryKey(Integer id);

    int insert(HomeDetail record);

    int insertSelective(HomeDetail record);

    HomeDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeDetail record);

    int updateByPrimaryKey(HomeDetail record);

}

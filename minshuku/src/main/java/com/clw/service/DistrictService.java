package com.clw.service;

import com.clw.domain.District;
import com.clw.pojo.DistrictParams;
import com.clw.pojo.DistrictPojo;

import java.util.List;

public interface DistrictService{


    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    List<DistrictPojo> selectDistrict();

    List<DistrictPojo> selectAllDistrict();
}

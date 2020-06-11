package com.clw.service;

import com.clw.domain.Collect;
import com.clw.pojo.CollectPojo;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;

import java.util.List;

public interface CollectService {


    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    CommonResult collect(Integer homeId, Integer userId);

    CommonResult updateCollection(Integer homeId, Integer num);

    CommonResult<CommonPage<CollectPojo>> selectCollectByUserId(Integer pageNum, Integer pageSize, Integer userId);
}



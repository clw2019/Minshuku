package com.clw.service;

import com.clw.domain.Home;
import com.clw.pojo.*;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;

import java.util.List;

public interface HomeService {


    int deleteByPrimaryKey(Integer id);

    int insert(Home record);

    int insertSelective(Home record);

    Home selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);

    List<HomeData> getHomeData(Integer pageNum, Integer pageSize, String address);

    List<HomeDetailData> getDetail(Integer id);

    CommonResult<CommonPage<AdminHome>> getHome(Integer pageNum, Integer pageSize);

    CommonResult<CommonPage<AdminHome>> getHomeByLike(Integer pageNum, Integer pageSize, String homeName);

    CommonResult updateHomeStatus(Integer homeId);

    CommonResult publishHome(PublishPojo params);

    CommonResult<CommonPage<MyHomePojo>> selectMyHomeByHostId(Integer pageNum, Integer pageSize, Integer hostId);

    CommonResult updateHomeFlag(Integer homeId, Integer flag, String imgUrl);
}


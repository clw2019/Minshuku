package com.clw.mapper;

import com.clw.domain.Home;
import com.clw.pojo.AdminHome;
import com.clw.pojo.HomeData;
import com.clw.pojo.HomeDetailData;
import com.clw.pojo.MyHomePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Home record);

    int insertSelective(Home record);

    Home selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);

    List<HomeData> getHomeData(@Param("address") String address);

    List<HomeDetailData> getDetail(@Param("id") Integer id);

    List<AdminHome> getHome();

    List<AdminHome> getHomeByLike(@Param("homeName") String homeName);

    Integer updateHomeStatus(@Param("homeId") Integer homeId);

    List<MyHomePojo> selectMyHomeByHostId(@Param("hostId") Integer hostId);

    Integer updateHomeFlag(@Param("homeId") Integer homeId, @Param("flag") Integer flag);
}
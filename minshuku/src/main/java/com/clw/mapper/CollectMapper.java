package com.clw.mapper;

import com.clw.domain.Collect;
import com.clw.pojo.CollectPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    Collect selectByHomeIdAndUserId(@Param("homeId") Integer homeId, @Param("userId") Integer userId);

    Integer updateCollection(@Param("homeId") Integer homeId, @Param("num") Integer num);

    List<CollectPojo> selectCollectByUserId(@Param("userId") Integer userId);
}
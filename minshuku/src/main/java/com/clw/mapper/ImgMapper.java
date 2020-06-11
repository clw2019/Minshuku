package com.clw.mapper;

import com.clw.domain.Img;
import org.apache.ibatis.annotations.Param;

public interface ImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);

    Integer deleteByHomeId(@Param("homeId") Integer homeId);
}
package com.clw.mapper;

import com.clw.domain.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<Carousel> getCarousel();

    Integer deleteByHomeId(@Param("homeId") Integer homeId);
}
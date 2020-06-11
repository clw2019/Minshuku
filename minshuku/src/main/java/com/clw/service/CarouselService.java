package com.clw.service;

import com.clw.domain.Carousel;

import java.util.List;

public interface CarouselService{

    int deleteByPrimaryKey(Integer id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<Carousel> getCarousel();
}

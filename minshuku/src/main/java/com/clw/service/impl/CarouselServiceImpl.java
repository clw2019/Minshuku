package com.clw.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.mapper.CarouselMapper;
import com.clw.domain.Carousel;
import com.clw.service.CarouselService;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService{

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carouselMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Carousel record) {
        return carouselMapper.insert(record);
    }

    @Override
    public int insertSelective(Carousel record) {
        return carouselMapper.insertSelective(record);
    }

    @Override
    public Carousel selectByPrimaryKey(Integer id) {
        return carouselMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Carousel record) {
        return carouselMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Carousel record) {
        return carouselMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Carousel> getCarousel() {
        List<Carousel> carouselList = carouselMapper.getCarousel();
        return carouselList;
    }

}

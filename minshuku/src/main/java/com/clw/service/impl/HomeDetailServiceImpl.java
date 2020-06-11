package com.clw.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.domain.HomeDetail;
import com.clw.mapper.HomeDetailMapper;
import com.clw.service.HomeDetailService;
@Service
public class HomeDetailServiceImpl implements HomeDetailService{

    @Resource
    private HomeDetailMapper homeDetailMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return homeDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(HomeDetail record) {
        return homeDetailMapper.insert(record);
    }

    @Override
    public int insertSelective(HomeDetail record) {
        return homeDetailMapper.insertSelective(record);
    }

    @Override
    public HomeDetail selectByPrimaryKey(Integer id) {
        return homeDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(HomeDetail record) {
        return homeDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(HomeDetail record) {
        return homeDetailMapper.updateByPrimaryKey(record);
    }

}

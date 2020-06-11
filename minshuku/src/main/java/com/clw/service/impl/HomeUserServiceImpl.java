package com.clw.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.mapper.HomeUserMapper;
import com.clw.domain.HomeUser;
import com.clw.service.HomeUserService;
@Service
public class HomeUserServiceImpl implements HomeUserService{

    @Resource
    private HomeUserMapper homeUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return homeUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(HomeUser record) {
        return homeUserMapper.insert(record);
    }

    @Override
    public int insertSelective(HomeUser record) {
        return homeUserMapper.insertSelective(record);
    }

    @Override
    public HomeUser selectByPrimaryKey(Integer id) {
        return homeUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(HomeUser record) {
        return homeUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(HomeUser record) {
        return homeUserMapper.updateByPrimaryKey(record);
    }

}

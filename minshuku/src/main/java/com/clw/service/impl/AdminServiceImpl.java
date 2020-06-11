package com.clw.service.impl;

import com.clw.pojo.AdminLogin;
import com.clw.utils.CommonResult;
import com.clw.utils.JwtTokenUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.domain.Admin;
import com.clw.mapper.AdminMapper;
import com.clw.service.AdminService;

import java.util.HashMap;

@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public CommonResult login(AdminLogin params) {
        Admin admin = adminMapper.selectByPrimaryUserName(params.getUsername());
        if (admin == null || admin.equals("")) {
            return CommonResult.fail(100, "用户名不存在");
        }
        if (!params.getPassword().equals(admin.getPassword())) {
            return CommonResult.fail(100, "密码不正确");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", admin.getId());
        map.put("username", admin.getUsername());
        String token = jwtTokenUtils.generateToken(map);
        return CommonResult.success(token);
    }

}

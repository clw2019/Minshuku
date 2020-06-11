package com.clw.service;

import com.clw.domain.User;
import com.clw.pojo.LoginParams;
import com.clw.pojo.RegisterParams;
import com.clw.pojo.ResetPasswordParams;
import com.clw.pojo.UpdateUserParams;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;

import java.util.List;

public interface UserService{


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    CommonResult register(RegisterParams params);

    CommonResult login(LoginParams params);

    CommonResult selectUser(Integer userId);

    CommonResult updateUser(UpdateUserParams params);

    CommonResult<CommonPage<User>> getUserInfo(Integer pageNum, Integer pageSize, String input);

    CommonResult updateStatus(Integer userId);

    CommonResult<CommonPage<User>> getUserByLike(Integer pageNum, Integer pageSize, String username);

    CommonResult resetPassword(List<Integer> params);
}

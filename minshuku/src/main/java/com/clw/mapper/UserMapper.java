package com.clw.mapper;

import com.clw.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPrimaryUsername(@Param("username") String username);

    User selectUser(@Param("userId") Integer userId);

    List<User> selectUserInfo(@Param("input") String input);

    Integer updateStatus(@Param("userId") Integer userId);

    List<User> getUserByLike(@Param("username") String username);

    Integer resetPassword(@Param("list") List<Integer> list, @Param("newPassword") String newPassword);
}
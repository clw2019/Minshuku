package com.clw.controller;

import com.clw.pojo.LoginParams;
import com.clw.pojo.RegisterParams;
import com.clw.pojo.UpdateUserParams;
import com.clw.service.UserService;
import com.clw.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/13 12:04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
//注册时密码是明文传输的

    /**
     * 注册
     * @param params
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@RequestBody RegisterParams params) {
        log.info("[{}]", params);
        CommonResult result = userService.register(params);
        return result;
    }

    /**
     * 登录
     * @param params
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginParams params) {
        log.info("[{}]", params);
        CommonResult result = userService.login(params);
        return result;
    }

    /**
     * 根据userId查询用户基本信息
     * @param userId
     * @return
     */
    @RequestMapping("/selectUser")
    public CommonResult selectUser(@RequestParam("userId") Integer userId) {
        CommonResult result = userService.selectUser(userId);
        return result;
    }

    /**
     * 根据userId修改用户基本信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public CommonResult updateUser(@RequestBody UpdateUserParams params) {
        CommonResult result = userService.updateUser(params);
        return result;
    }
}

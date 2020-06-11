package com.clw.service.impl;

import com.clw.pojo.LoginParams;
import com.clw.pojo.RegisterParams;
import com.clw.pojo.ResetPasswordParams;
import com.clw.pojo.UpdateUserParams;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import com.clw.utils.JwtTokenUtils;
import com.clw.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.clw.mapper.UserMapper;
import com.clw.domain.User;
import com.clw.service.UserService;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtTokenUtils jwtTokenUtils;
    @Resource
    private MD5Utils md5Utils;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public CommonResult register(RegisterParams params) {
        String username = params.getUsername();
        String password = params.getPassword();
        if (username == null || username == "") {
            return CommonResult.fail(100, "用户名不合法");
        }
        User user = userMapper.selectByPrimaryUsername(username);
        if (user != null) {
            return CommonResult.fail(100, "用户名已存在");
        }
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(md5Utils.md5(password));
        user1.setNick(username);
        user1.setIcon("https://s3plus-img.meituan.net/v1/mss_65766da973d14523b3d781fe3ac2bbac/www-assets/shared/images/avatar-default-ved645e79.png@80w_80h_1e_1c.webp");
        int result = userMapper.insertSelective(user1);
        return result == 1 ? CommonResult.success("注册成功") : CommonResult.fail(100, "注册失败");
    }

    @Override
    public CommonResult login(LoginParams params) {
        User user = userMapper.selectByPrimaryUsername(params.getUsername());
        if (user == null || user.equals("")) {
            return CommonResult.fail(100, "用户不存在");
        }
        if (user.getStatus() == 1) {
            return CommonResult.fail(100, "此用户已被禁用，请联系相关人员");
        }
        if (!user.getPassword().equals(md5Utils.md5(params.getPassword()))) {
            return CommonResult.fail(100, "密码错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("username", user.getNick());
        map.put("icon", user.getIcon());
        String token = jwtTokenUtils.generateToken(map);
        log.info("[{}]", token);
        Claims claims = jwtTokenUtils.getClaimsFromToken(token);
        log.info("[{}]", claims.get("username"));
        System.out.println("过期时间........... " + claims.getExpiration());
        System.out.println(jwtTokenUtils.isTokenExpired(token));
        return CommonResult.success(jwtTokenUtils.generateToken(map));
    }

    @Override
    public CommonResult selectUser(Integer userId) {
        User user = userMapper.selectUser(userId);
        return CommonResult.success(user);
    }

    @Override
    public CommonResult updateUser(UpdateUserParams params) {
        User user = new User();
        user.setId(params.getId());
        user.setNick(params.getNick());
        user.setSex(params.getSex());
        user.setPhonenumber(params.getPhonenumber());
        user.setIcon(params.getIcon());
        int i = userMapper.updateByPrimaryKeySelective(user);
        return CommonResult.success(i);
    }

    @Override
    public CommonResult<CommonPage<User>> getUserInfo(Integer pageNum, Integer pageSize, String input) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectUserInfo(input);
        return userList == null ? CommonResult.fail(100, "用户信息为空") : CommonResult.success(CommonPage.resultPage(userList));
    }

    @Override
    public CommonResult updateStatus(Integer userId) {
        Integer result = userMapper.updateStatus(userId);
        return result == 0 ? CommonResult.fail(100, "状态更新失败") : CommonResult.success(result);
    }

    @Override
    public CommonResult<CommonPage<User>> getUserByLike(Integer pageNum, Integer pageSize, String username) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getUserByLike(username);
        return userList == null ? CommonResult.fail(100, "用户不存在") : CommonResult.success(CommonPage.resultPage(userList));
    }

    @Override
    public CommonResult resetPassword(List<Integer> list) {
        Integer result = null;
        if (list != null && list.size() > 0) {
            result = userMapper.resetPassword(list,md5Utils.md5("123456"));
            System.out.println("............... " + result);
        } else {
            return CommonResult.fail(100, "请选择要重置的账号");
        }
        return result == null ? CommonResult.fail(100, "密码重置失败") : CommonResult.success("密码重置成功");
    }
}

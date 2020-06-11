package com.clw.controller;

import com.clw.domain.OrderInfo;
import com.clw.domain.Review;
import com.clw.domain.User;
import com.clw.pojo.*;
import com.clw.service.*;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/2 15:33
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;
    @Resource
    private HomeService homeService;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private ReviewService reviewService;

    /**
     * 后台：管理员登录
     * @param params
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody AdminLogin params) {
        log.info("AdminLogin -- [{}]", params);
        return adminService.login(params);
    }

    /**
     * 后台：分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/user")
    public CommonResult<CommonPage<User>> getUserInfo(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "input", required = false) String input) {
        log.info("pageNum,pageSize----> [{}][{}]",pageNum, pageSize, input);
        return userService.getUserInfo(pageNum, pageSize, input);
    }

    /**
     * 后台：根据用户ID修改用户状态
     * @param userId
     * @return
     */
    @GetMapping("/updateUserStatus")
    public CommonResult updateStatus(@RequestParam("userId") Integer userId) {
        log.info("userId ----> [{}]", userId);
        return userService.updateStatus(userId);
    }

    /**
     * 批量密码重置
     * @param params
     * @return
     */
    @PostMapping("/resetPassword")
    public CommonResult resetPassword(@RequestBody List<Integer> params) {
        log.info("List ----> [{}]", params);

        return userService.resetPassword(params);
    }

    /**
     * 后台：分页查询房屋信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/home")
    public CommonResult<CommonPage<AdminHome>> getHome(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        log.info("pageNum,pageSize----> [{}][{}]", pageNum, pageSize);
        return homeService.getHome(pageNum, pageSize);
    }

    /**
     * 后台：根据房屋名模糊查询
     * @param pageNum
     * @param pageSize
     * @param homeName
     * @return
     */
    @RequestMapping("/likeHomeName")
    public CommonResult<CommonPage<AdminHome>> getHomeByLike(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                             @RequestParam("homeName") String homeName) {
        log.info("pageNum,pageSize,username----> [{}][{}][{}]",pageNum, pageSize, homeName);
        return homeService.getHomeByLike(pageNum, pageSize, homeName);
    }

    /**
     * 后台：更新房屋状态
     * @param homeId
     * @return
     */
    @RequestMapping("/updateHomeStatus")
    public CommonResult updateHomeStatus(@RequestParam("homeId") Integer homeId) {
        log.info("homeId ----> [{}]", homeId);
        return homeService.updateHomeStatus(homeId);
    }

    /**
     * 后台：分页查询订单
     * @param pageNum
     * @param pageSize
     * @param orderId
     * @return
     */
    @RequestMapping("/order")
    public CommonResult<CommonPage<AdminOrder>> getOrderInfo(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                             @RequestParam(value = "orderId", required = false) Integer orderId) {
        log.info("pageNum,pageSize,orderId----> [{}][{}][{}]",pageNum, pageSize, orderId);
        return orderInfoService.getOrderInfo(pageNum, pageSize, orderId);
    }

    /**
     * 后台：分页查询评价
     * @param pageNum
     * @param pageSize
     * @param input
     * @return
     */
    @GetMapping("/comment")
    public CommonResult<CommonPage<AdminReview>> getReviewInfo(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "input", required = false) String input) {
        log.info("pageNum,pageSize,orderId----> [{}][{}][{}]",pageNum, pageSize, input);
        return reviewService.getReviewInfo(pageNum, pageSize, input);
    }

    /**
     * 后台：是否加入轮播
     * @param homeId
     * @return
     */
    @GetMapping("/updateHomeFlag")
    public CommonResult updateHomeFlag(@RequestParam("homeId") Integer homeId,
                                       @RequestParam("flag") Integer flag,
                                       @RequestParam("imgUrl") String imgUrl) {
        log.info("homeId ----> [{}]", homeId);
        log.info("flag ----> [{}]", flag);
        log.info("imgUrl ----> [{}]", imgUrl);
        return homeService.updateHomeFlag(homeId, flag, imgUrl);
    }

}

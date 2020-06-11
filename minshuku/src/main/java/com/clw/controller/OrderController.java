package com.clw.controller;

import com.clw.pojo.OrderInfoDetail;
import com.clw.pojo.OrderInfoParams;
import com.clw.pojo.OrderInfoResponse;
import com.clw.service.OrderInfoService;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/9 10:12
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 添加订单
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult addOrderInfo(@RequestBody OrderInfoParams params) {
        log.info("[{}]", params);
        CommonResult result = orderInfoService.addOrderInfo(params);
        return result;
    }

    /**
     * 根据用户ID查找订单
     *
     * @param userId 用户ID
     * @param status 订单状态(根据居住日期算出来的状态)
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult<CommonPage<OrderInfoResponse>> getOrderByUserId(@RequestParam("userId") String userId,
                                                                        @RequestParam(value = "status", defaultValue = "0") Integer status,
                                                                        @RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        log.info("[{}],[{}],[{}],[{}]", userId, status, pageNum, pageSize);
        List<OrderInfoResponse> orderList = orderInfoService.selectOrderByUserId(userId, status, pageNum, pageSize);
        log.info("[{}]", orderList);
        return orderList != null ? CommonResult.success(CommonPage.resultPage(orderList)) : CommonResult.fail(100, "暂无数据");
    }

    /**
     * 根据订单id查询订单详细信息
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/orderInfo",method = RequestMethod.GET)
    public CommonResult selectOrderInfoByOrderId(@RequestParam("orderId") String orderId) {
        log.info("[{}]", orderId);
        OrderInfoDetail orderInfoDetailList = orderInfoService.selectOrderDetailByOrderId(orderId);
        return CommonResult.success(orderInfoDetailList);
    }
}

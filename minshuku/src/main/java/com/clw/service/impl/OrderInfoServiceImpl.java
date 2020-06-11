package com.clw.service.impl;

import com.clw.pojo.AdminOrder;
import com.clw.pojo.OrderInfoDetail;
import com.clw.pojo.OrderInfoParams;
import com.clw.pojo.OrderInfoResponse;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.clw.mapper.OrderInfoMapper;
import com.clw.domain.OrderInfo;
import com.clw.service.OrderInfoService;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderInfo record) {
        return orderInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderInfo record) {
        return orderInfoMapper.insertSelective(record);
    }

    @Override
    public OrderInfo selectByPrimaryKey(Integer id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public CommonResult addOrderInfo(OrderInfoParams params) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(params.getUserId());
        orderInfo.setHomeId(params.getHomeId());
        orderInfo.setAddress(params.getAddress());
        orderInfo.setLiveNum(params.getLiveNum());
        orderInfo.setPrice(params.getPrice());
        orderInfo.setStartTime(params.getDate().get(0));
        orderInfo.setEndTime(params.getDate().get(1));
        orderInfo.setLiveDays(params.getCountDay());
        orderInfo.setCountPrice(params.getCountPay());
        orderInfo.setPhoneNum(params.getPhoneNum());
        orderInfo.setLiveName(params.getName());
        orderInfo.setType(params.getType());
        orderInfo.setIdNum(params.getIdCard());
        int result = orderInfoMapper.addUserInfo(orderInfo);
        return result == 1 ? CommonResult.success("下单成功！") : CommonResult.fail(100, "下单失败！");
    }

    @Override
    public List<OrderInfoResponse> selectOrderByUserId(String userId, Integer status, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderInfoResponse> orderInfoResponses = orderInfoMapper.selectOrderByUserId(userId, status);
        System.out.println(orderInfoResponses);
        return orderInfoResponses;
    }

    @Override
    public OrderInfoDetail selectOrderDetailByOrderId(String orderId) {
        return orderInfoMapper.selectOrderDetailByOrderId(orderId);
    }

    @Override
    public CommonResult<CommonPage<AdminOrder>> getOrderInfo(Integer pageNum, Integer pageSize, Integer orderId) {
        PageHelper.startPage(pageNum, pageSize);
        //可以写一个方法，用动态SQL，不想改了
        if (orderId == null || orderId.equals("")) {
            List<AdminOrder> orderInfoList = orderInfoMapper.getOrderInfo();
            return orderInfoList == null ? CommonResult.fail(100, "订单数据为空") : CommonResult.success(CommonPage.resultPage(orderInfoList));
        }
        List<AdminOrder> orderInfoList = orderInfoMapper.getOrderInfoByLike(orderId);
        return orderInfoList == null ? CommonResult.fail(100, "订单数据为空") : CommonResult.success(CommonPage.resultPage(orderInfoList));
    }

}

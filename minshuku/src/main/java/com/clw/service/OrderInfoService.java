package com.clw.service;

import com.clw.domain.OrderInfo;
import com.clw.pojo.AdminOrder;
import com.clw.pojo.OrderInfoDetail;
import com.clw.pojo.OrderInfoParams;
import com.clw.pojo.OrderInfoResponse;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;

import java.util.List;

public interface OrderInfoService{


    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    CommonResult addOrderInfo(OrderInfoParams params);

    List<OrderInfoResponse> selectOrderByUserId(String userId, Integer status, Integer pageNum, Integer pageSize);

    OrderInfoDetail selectOrderDetailByOrderId(String orderId);

    CommonResult<CommonPage<AdminOrder>> getOrderInfo(Integer pageNum, Integer pageSize, Integer orderId);
}

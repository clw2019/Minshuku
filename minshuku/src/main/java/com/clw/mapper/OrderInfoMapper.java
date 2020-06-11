package com.clw.mapper;

import com.clw.domain.OrderInfo;
import com.clw.pojo.AdminOrder;
import com.clw.pojo.OrderInfoDetail;
import com.clw.pojo.OrderInfoResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int addUserInfo(OrderInfo orderInfo);

    List<OrderInfoResponse> selectOrderByUserId(@Param("userId") String userId, @Param("status") Integer status);

    OrderInfoDetail selectOrderDetailByOrderId(@Param("orderId") String orderId);

    List<AdminOrder> getOrderInfo();

    List<AdminOrder> getOrderInfoByLike(@Param("orderId") Integer orderId);
}
import { request } from './request'
import Qs from 'qs'

// 添加订单
export function addOrder(params) {
    return request({
        url: '/order/add',
        method: 'POST',

        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },

        // data: Qs.stringify(params)
        data: JSON.stringify(params)
    })
}

// 获取用户的订单信息
export function getOrder(userId, status, currentPage, pageSize) {
    return request({
        url: '/order/select',
        method: 'GET',
        // headers: {
        //     'Content-Type': 'application/json;charset=utf-8',
        // },
        // data: JSON.stringify(params)
        params: {
            userId,
            status,
            currentPage,
            pageSize
        }
    })
}

// 根据订单ID查找详细信息
export function getOrderDetail(orderId) {
    return request({
        url: '/order/orderInfo',
        method: 'GET',
        params: {
            orderId
        }
    })
}
import { request } from './request'

// 分页查询订单     
export function getOrder(currentPage, pageSize, orderId) {
    return request({
        url: '/admin/order',
        params: {
            currentPage, 
            pageSize,
            orderId
        }
    })
}

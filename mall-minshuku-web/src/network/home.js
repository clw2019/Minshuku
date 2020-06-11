import { request } from './request'

// 请求轮播图资源
export function getCarousel() {
    return request({
        url: '/carousel/data'
    })
}

// 请求房屋资源
export function getGoods(currentPage, pageSize, address) {
    return request({
        url: '/home/data',
        params: {
            currentPage, 
            pageSize,
            address
        }
    })
}

// 请求省市县资源
export function getDistrict() {
    return request({
        url: '/district/select',
    })
}

// 发布房源
export function publishHome(params) {
    return request({
        url: '/home/publish',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
        data: JSON.stringify(params)
    })
}

// 请求发布房源资源
export function getMyHome(hostId, currentPage, pageSize) {
    return request({
        url: '/home/myhome',
        params: {
            hostId,
            currentPage,
            pageSize
        }
    })
}

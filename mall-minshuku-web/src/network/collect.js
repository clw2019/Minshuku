import { request } from './request'

// 收藏
export function collect(homeId, userId) {
    return request({
        url: '/collect/insertOrDelete',
        params: {
            homeId,
            userId
        }
    })
}

// 更新数据库的收藏数
export function updateCollection(homeId, num) {
    return request({
        url: '/collect/collection',
        params: {
            homeId,
            num
        }
    })
}

// 根据userId查找收藏的房屋
export function selectCollectByUserId(userId, currentPage, pageSize) {
    return request({
        url: '/collect/myCollect',
        params: {
            userId,
            currentPage,
            pageSize
        }
    })
}
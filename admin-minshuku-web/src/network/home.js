import { request } from './request'

// 房屋列表
export function getHome(currentPage, pageSize) {
    return request({
        url: '/admin/home',
        params: {
            currentPage,
            pageSize
        }
    })
}

// 通过房屋名称查询
export function getHomeByLike(currentPage, pageSize, homeName) {
    return request({
        url: '/admin/likeHomeName',
        params: {
            currentPage,
            pageSize,
            homeName
        }
    })
}

// 更新房屋状态
export function changeHomeStatus(homeId) {
    return request({
        url: '/admin/updateHomeStatus',
        params: {
            homeId
        }
    })
}

// 更新轮播状态
export function changeFlag(homeId, flag, imgUrl) {
    return request({
        url: '/admin/updateHomeFlag',
        params: {
            homeId,
            flag,
            imgUrl
        }
    })
}
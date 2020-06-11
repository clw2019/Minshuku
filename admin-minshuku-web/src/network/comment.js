import { request } from './request'

// 查询评价
export function getComment(currentPage, pageSize, input) {
    return request({
        url: '/admin/comment',
        params: {
            currentPage, 
            pageSize,
            input
        }
    })
}
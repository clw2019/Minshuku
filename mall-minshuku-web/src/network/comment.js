import { request } from './request'

// 添加评论
export function editComment(params) {
    return request({
        url: '/review/edit',
        method: 'POST',

        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },

        data: JSON.stringify(params)
    })
}

// 添加回复
export function review(reviewId, reviewContent) {
    return request({
        url: '/review/hostReview',
        params: {
            reviewId,
            reviewContent
        }
    })
}



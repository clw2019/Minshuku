import { request } from './request'

// 用户列表
export function getUser(currentPage, pageSize, input) {
    return request({
        url: '/admin/user',
        params: {
            currentPage,
            pageSize,
            input
        }
    })
}

// 更新用户状态
export function changeStatus(userId) {
    return request({
        url: '/admin/updateUserStatus',
        params: {
            userId
        }
    })
}

// 批量重置密码
export function resetPassword(userIdList) {
    return request({
        url: '/admin/resetPassword',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
        data: JSON.stringify(userIdList)
    })
}


// // 根据用户名模糊查询
// export function getUsernameByLike(currentPage, pageSize, username) {
//     return request({
//         url: '/admin/likeUsername',
//         params: {
//             currentPage, 
//             pageSize,
//             username
//         }
//     })
// }
import { request } from './request'

// 注册
export function register(params) {
    return request({
        url: '/user/register',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        data: JSON.stringify(params)
    })
}

// 登录
export function login(params) {
    return request({
        url: '/user/login',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
        data: JSON.stringify(params)
    })
}

// 通过userId查询用户信息
export function selectUser(userId) {
    return request({
        url: '/user/selectUser',
        params: {
            userId
        }
    })
}

// 通过userId修改用户信息
export function updateUser(params) {
    return request({
        url: '/user/updateUser',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
        data: JSON.stringify(params)
    })
}
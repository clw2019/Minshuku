import { request } from './request'

// Admin login
export function login(params) {
    return request({
        url: '/admin/login',
        method: 'POST',
        data: params
    })
}
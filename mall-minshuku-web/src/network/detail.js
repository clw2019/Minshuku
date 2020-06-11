import { request } from './request'

export function getDetail(id) {
    return request({
        url: '/home/detail',
        params: {
            id
        }
    })
}
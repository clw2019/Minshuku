import { request } from './request'

export function selectHome(address) {
    return request({
        url: '/home/data',
        params: {
            address
        }
    })
}
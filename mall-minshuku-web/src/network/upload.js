import { request } from './request'


export function upload(file) {
    return request({
        url: '/upload1',
        method: 'POST',
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        data: file
    })
}

export function upload111(file) {
    return request({
        url: '/upload1',
        method: 'POST',
        data: file
    })
}
// 1. 导入axios
import axios from 'axios'
export function request(config) {
    // 2. 创建axios实例
    const instance = axios.create({
        baseURL: 'http://localhost:8000/minshuku',
        timeout: 5000,   //毫秒
    })

    // 2.1 axios请求拦截
    instance.interceptors.request.use(config => {
        // 在这里进行拦截
        return config
    }, err => {
        console.log("req err => ", err);
    })

    // 2.2 响应拦截
    instance.interceptors.response.use(res => {
        return res
    }, err => {
        console.log("res err => ", err);
    })
    // 发送真正的网络请求
    return instance(config)
}
import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8080/bigHomeWork', // 接口基础路径
  timeout: 10000 // 超时时间（毫秒）
})

// 添加请求拦截器
instance.interceptors.request.use(function (config) {
  // 在请求发送之前做一些处理
  return config
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 响应拦截器
instance.interceptors.response.use(function (response) {
  // 对响应数据做一些处理
  return response.data
}, function (error) {
  // 对响应错误做些什么
  return Promise.reject(error)
})

// 封装 GET 请求方法
export const get = (url, params) => {
  return instance.get(url, { params })
}

// 封装 POST 请求方法
export const post = (url, data) => {
  return instance.post(url, data)
}

import instance from '../libs/request'
import Qs from 'qs'

// 封装 GET 请求方法
export const get = (query) => {
  return instance({
    url: '',
    method: 'get',
    params: query
  })
}

// 封装验证账号密码请求方法
export const checking = (data) => {
  //利用axios自带的Qs.stringify，将对象解析成url的形式
  return instance({
    url: '/adminis/Verify',
    method: 'post',
    data: Qs.stringify(data)
  })
}
export const upPassWord = (data) => {
  return instance({
    url: '/student/upPass',
    method: 'post',
    data: Qs.stringify(data)
  })
}
export const register = (data) => {
  return instance({
    url: '/student/add',
    method: 'post',
    data: Qs.stringify(data)
  })
}

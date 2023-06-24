import instance from '../libs/request'
import Qs from 'qs'

// 封装 GET 请求方法
export const getAllClass = () => {
  return instance({
    url: '/clas/getAll',
    method: 'get',
    params: null
  })
}

// 封装顾客报名赛事方法
export const joinStudent = (data) => {
  //利用axios自带的Qs.stringify，将对象解析成url的形式
  return instance({
    url: '/singUp/add',
    method: 'post',
    data: Qs.stringify(data)
  })
}

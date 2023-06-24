import instance from '../libs/request'
import Qs from 'qs'

// 封装 GET 请求方法
export const getAllActivity = () => {
  return instance({
    url: 'activity/getAll',
    method: 'get',
    params: null
  })
}

// 封装顾客报名赛事方法
export const joinActivity = (data) => {
  //利用axios自带的Qs.stringify，将对象解析成url的形式
  return instance({
    url: '/joinactivity/add',
    method: 'post',
    data: Qs.stringify(data)
  })
}

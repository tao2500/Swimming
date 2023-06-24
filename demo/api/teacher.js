import instance from '../libs/request'
import Qs from 'qs'

// 获取所有教练员信息
export const getAllTeacher = () => {
  return instance({
    url: '/teacher/getAll',
    method: 'get',
    params: null
  })
}

// 根据id获取教练员信息
export const getTeacher = (data) => {
  //利用axios自带的Qs.stringify，将对象解析成url的形式
  return instance({
    url: '/teacher/getById',
    method: 'post',
    data: Qs.stringify(data)
  })
}

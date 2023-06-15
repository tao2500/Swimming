//引入axios和封装好的config
import axios from 'axios';
import config from '../src/config';
import Message from "shelljs";

const instance = axios.create({
  baseURL: config.baseUrl,
  //超时时间，根据项目需求，决定是否启用
  //如有文件上传需求，建议设置大一些 或 另外创建一个axios实例 负责文件上传
  timeout: 1000 * 60
});

//如有需要，可在请求发起之前，做拦截处理
instance.interceptors.request.use((request) => {
  console.log("config拦截:"+config);
  return config;
})

//如有需要，可在请求收到回复之后，做拦截处理
instance.interceptors.response.use(
  (res) => {
    //请根据项目前后端约定，更改对应字段取值
    let resData = res.data;
    let code = resData.code || '404';
    if(code != '404'){
      if(res.config.commonHandleError !== false){
        Message.error(resData && resData.message || '网络连接错误，请稍后再试');
      }
      //将数据暴露给调用函数，以便调用方使用catch捕获做个性化处理
      return Promise.reject(resData);
    }
    return resData;
  },
  (error) => {
    // console.log(error.message);
    Message.error((error && error.message) || '网络连接错误，请稍后再试')
    return Promise.reject(error);
  }
)

//导出
export default instance;

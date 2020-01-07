import axios from 'axios'
export function request(config){
  //1.创建axios实例
  const instance=axios.create({
    baseURL:'http://127.0.0.1:6001',
    timeout:5000,
    withCredentials:true
  })
  //2.发送网络请求
  return instance(config);
}

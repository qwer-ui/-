import {request} from '../request'
//登录
export const logins=(name,password,captcha)=>{
   return request({
      url:'/Login/login',
      method:'get',
      params:{
        name,
        password,
        captcha
      }
   })
}

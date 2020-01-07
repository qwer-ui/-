//获取菜单
import {request} from '../../request'
export  const getMenu=(url,userId)=>{
   return request({
      url,
      method:'get',
      params:{
        UserId:userId
      }
   })
}

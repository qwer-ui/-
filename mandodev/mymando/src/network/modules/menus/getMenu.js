/*获取用户信息*/
import {request} from '../../request'
export  const  GetMenu=(url,pageNo,pageSize,name)=>{
  return request({
    url,
    method:'get',
    params:{
      pageNo,
      pageSize,
      name
    }
  })
}

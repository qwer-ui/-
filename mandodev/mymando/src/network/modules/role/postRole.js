/*新增用户信息*/
import {request} from '../../request'
export  const  PostRole=(url,{id,name,remark})=>{
  return request({
    url,
    method:'post',
    params:{
      id,name,remark
    }
  })
}

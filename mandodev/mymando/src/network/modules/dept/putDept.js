/*新增用户信息*/
import {request} from '../../request'
export  const  PutDept=(url,{id,name,parent_id})=>{
  return request({
    url,
    method:'put',
    params:{
      id,name,parent_id
    }
  })
}
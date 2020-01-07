/*新增用户信息*/
import {request} from '../../request'
export  const  PostMenu=(url,menu)=>{
  return request({
    url,
    method:'post',
    data:menu
  })
}

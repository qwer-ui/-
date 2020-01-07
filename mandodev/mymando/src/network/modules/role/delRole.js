/*删除用户信息*/
import {request} from '../../request'
export  const  DelRole=(url)=>{
  return request({
    url,
    method:'delete'
  })
}

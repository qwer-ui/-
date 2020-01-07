/*删除用户信息*/
import {request} from '../../request'
export  const  DelMenu=(url)=>{
  return request({
    url,
    method:'delete'
  })
}

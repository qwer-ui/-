/*新增用户信息*/
import {request} from '../../request'
export  const  PostUser=(url,{id,name,nick_name,password,email,mobile,dept_id,role_id})=>{
  return request({
    url,
    method:'post',
    params:{
        id,name,nick_name,password,email,mobile,dept_id,role_id
    }
  })
}

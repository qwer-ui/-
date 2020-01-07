import {getMenu} from '../../../network/modules/menu/getMenu'

//初始化菜单
export const initMenu=(router,store)=>{
   if(store.state.routes.length>0){
     return
  }

  //根据用户角色请求菜单

  getMenu('/menu/getMenu',sessionStorage.getItem("userId")).then(res=>{
      if(res){//请求数据不为空
         let fmRoutes=formatRoutes(res.data.data)
         console.log(res.data)
         router.addRoutes(fmRoutes)
         store.commit('initMenu',fmRoutes)
     }
  })
}
//初始化路由
export  const formatRoutes=routes=>{
  let fmRoutes=[]
  routes.forEach(router=>{
     let {
        path,
        name,
        icon,
        children,
        component,
        parent_id
     }=router
     if(children&&children instanceof Array){
        children=formatRoutes(children) //递归循环给子集合复制
     }
    if(path!=null && component!=null){
      let fmRouter={
        path:path,
        component(resolve){//动态将组件赋值给对象
          console.log(component)
          if(component.startsWith('Home')){
            require(['../../../views/'+component+'.vue'],resolve)
          }else if(component.startsWith('Sys')){
            require(['../../../views/sys/'+component+'.vue'],resolve)
          }
        },
        name:name,
        iconcls:icon,
        children:children
      }
      fmRoutes.push(fmRouter)
    }
  })
  return fmRoutes
}

//引入router
import Vue from 'vue'
import VueRouter from 'vue-router'
import store  from '../store/index'
import {initMenu} from "../components/content/initmenu/InitMenu";

//引入组件
const  Login=()=>import('../views/Login')
const  Home=()=>import('../views/Home')
/*const  SysConfig=()=>import('../views/sys/SysConfig')*/
//1.安装插件
Vue.use(VueRouter)

//2.配置路由对象
const routes=[
  {
     path:'',
     redirect:'login'
  },
  {
    path:'/login',
    name:'登录',
    component:Login
  },
  {
    path:'/home',
    name:'首页',
    component:Home
  },
  /*{
    path:'/sys/config',
    name:'系统配置',
    component:SysConfig
  }*/
]

const  router=new VueRouter({
  routes,
  mode:'history'
})
//前置路由守卫
router.beforeEach((to,from,next)=>{
 if(to.name=='登录'){
    next()
    return
 }
 initMenu(router,store)
 next()
})


//3.导出对象
export default router


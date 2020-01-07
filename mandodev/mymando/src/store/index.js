import Vue from 'vue'
import vuex from 'vuex'
//注册vuex
Vue.use(vuex)

const store=new vuex.Store({
  state:{
    user:{},//用户状态
    routes:[]//路由对象
  },
  getters:{

    /* filterRoutes(state){
        return state.routes.filter(item=>{
           return item.children!=''
        })
     }*/
  },
  mutations:{
    initMenu(state,routes){//初始化路由
      console.log(routes)
      state.routes=routes
    }
  },
  actions:{

  },
  modules:{

  }
})
//导出store
export default  store

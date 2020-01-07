import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from  './store'
Vue.config.productionTip = false
Vue.use(ElementUI) //注册使用Element

new Vue({
  el: '#app',
  components: { App },
  router,
  store,
  template: '<App/>'
})

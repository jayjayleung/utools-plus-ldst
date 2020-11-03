import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import api from '@/http/index' //请求封装
import store from '@/store/index' //Vuex
import '@/utils/filter' //过滤器
import { ElementMessage } from '@/utils/elementMessage'



Vue.use(api);
Vue.prototype.$store = store
Vue.prototype.$message = ElementMessage;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


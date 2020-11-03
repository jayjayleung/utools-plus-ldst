
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// import vTable from '@/components/table/vTableComponent';
// import vTree from '@/components/tree/vTree';
// import commonView from '@/components/common-view/commonView';

import {Global} from '@/common/Global';
import './assets/css/dialog.css';
import echarts from 'echarts'

import api from '@/http/index' //请求封装
import store from '@/store/index' //Vuex
import { ElementMessage } from '@/components/elementMessage'
import '@/utils/filter' //过滤器

// Vue.use(Print);

// //使用阿里图标库
// import './assets/icons/iconfont.css';
// import './assets/icons/icon.css';


Vue.prototype.global = Global;

// 引入echarts 全局引入
Vue.prototype.$echarts = echarts
Vue.use(api);
Vue.prototype.$store = store
Vue.prototype.$message = ElementMessage;

import print from '@/utils/print'
Vue.use(print)


//刷新保存状态
if (sessionStorage.getItem("store")) {
  store.replaceState(
    Object.assign(
      {},
      store.state,
      JSON.parse(sessionStorage.getItem("store"))
    )
  );
  sessionStorage.removeItem("store")
}
//监听，在页面刷新时将vuex里的信息保存到sessionStorage里
window.addEventListener("beforeunload", () => {
  sessionStorage.setItem("store", JSON.stringify(store.state));
});

/**
 * 将所有未登录会话重定向到 /login
 *用 vue-router 的 beforeEach 实现
 *beforeEach 方法接收三个参数：
 *to: Route: 即将要进入的目标 路由对象
 *from: Route: 当前导航正要离开的路由
 *next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。
 */

// router.beforeEach((to, from, next) => {
//   var reg = RegExp('mobile');
//   console.log(reg.test(to.path))
//   console.log(to.path)
//   if(to.path === '/login' || to.path ===  '/register' || reg.exec(to.path)){
//     next();
//   }else{
//     if (!store.state.token) {
//       next('/login');
//     } else {
//       next();
//     }
//   }
// })

Vue.config.productionTip = false
Vue.use(ElementUI);
// Vue.component("v-table",vTable);
// Vue.component("v-tree",vTree);
// Vue.component("v-common",commonView);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

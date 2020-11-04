 
import Vue from 'vue'
import VueRouter from 'vue-router'

// 解决两次访问相同路由地址报错
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}




const Home = () => import('@/views/home/Home');
const Info = () => import('@/views/home/Info');


Vue.use(VueRouter)

const routes = [
  //首页
  {
    path: '/',
    name: 'home',
    component: Home
  },
  //首页
  {
    path: '/home',
    name: 'home',
    component: Home,
  },
  {
    path: '/info',
    name:'info',
    component: Info
  }
]

const router = new VueRouter({
  // mode:'history',  //使用history模式,  意味着服务器要配置
  base : '/ldst/',
  routes
})

router.onError((error) => {
  const pattern = /Loading chunk (\d)+ failed/g;
  const isChunkLoadFailed = error.message.match(pattern);
  const targetPath = router.history.pending.fullPath;
  if (isChunkLoadFailed) {
   router.replace(targetPath);
  }
 });

export default router

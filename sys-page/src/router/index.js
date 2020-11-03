 
import Vue from 'vue'
import VueRouter from 'vue-router'

// 解决两次访问相同路由地址报错
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}




const Login = () => import('@/views/login/Login');
const register  = () => import('@/views/login/register');
const Home = () => import('@/views/home/Home');
const Index = () => import('@/views/home/index');

const addDelivery = () => import('@/views/delivery/addDelivery'); 
const deliveryList = () => import('@/views/delivery/deliveryList'); 
const deliveryInfo = () => import('@/views/delivery/deliveryInfo'); 
const deliveryTag = () => import('@/views/delivery/deliveryTag'); 
const deliveryFlag = () => import('@/views/delivery/deliveryFlag'); 

const warehouseManagement = () => import('@/views/warehouse/warehouseManagement'); 
const warehouseInfo = () => import('@/views/warehouse/warehouseInfo'); 

const mobile = () => import('@/views/mobile/index');
const mwarthouseInfo = () => import('@/views/mobile/wareHouse/warthouseInfo');
const deliveryListInfo  = () => import('@/views/mobile/wareHouse/deliveryListInfo');


const userManagement = () => import('@/views/user/user'); 

const picking = () => import('@/views/warehouse/picking');

Vue.use(VueRouter)

const routes = [
  //登录
  {
    path: '/login',
    name: 'Login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: Login
  },
   //登录
   {
    path: '/register',
    name: 'register',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: register
  },
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
    //首页下的子路由
    children:[
      {
        // 当 /home/test 匹配成功，
        // UserProfile 会被渲染在 User 的 <router-view> 中
        path: 'index',
        name:'index',
        component: Index
      },
      {
        // 当 /home/test2 匹配成功，
        // UserProfile 会被渲染在 User 的 <router-view> 中
        path: 'deliveryList',
        name:'deliveryList',
        component: deliveryList
      },
      {
        // 当 /home/test2 匹配成功，
        // UserProfile 会被渲染在 User 的 <router-view> 中
        path: 'deliveryInfo',
        name:'deliveryInfo',
        component: deliveryInfo
      },
      {
        path: 'addDeliveryList',
        name:'addDelivery',
        component: addDelivery
      },
      {
        // 当 /home/test2 匹配成功，
        // UserProfile 会被渲染在 User 的 <router-view> 中
        path: 'deliveryTag',
        name:'deliveryTag',
        component: deliveryTag
      },
      {
        path: 'deliveryFlag',
        name:'deliveryFlag',
        component: deliveryFlag
      },
      {
        path: 'warehouseManagement',
        name:'warehouseManagement',
        component: warehouseManagement
      },
      {
        path: 'warehouseInfo',
        name:'warehouseInfo',
        component: warehouseInfo
      },
      {
        path: 'warehouseInfo',
        name:'warehouseInfo',
        component: warehouseInfo
      },{
        path: 'userManagement',
        name:'userManagement',
        component: userManagement
      },
  	  {
        path: 'picking',
        name:'picking',
        component: picking
      }
    ]
  },
  {
    path: '/mobile',
    name: 'mobile',
    component: mobile,
    children:[
      {
        path: 'mwarthouseInfo',
        name:'mwarthouseInfo',
        component: mwarthouseInfo
      }
      ,{
        path: 'deliveryListInfo',
        name:'deliveryListInfo',
        component: deliveryListInfo
      },
    ]
  },
]

const router = new VueRouter({
  // mode:'history',  //使用history模式,  意味着服务器要配置
  base : '/',
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

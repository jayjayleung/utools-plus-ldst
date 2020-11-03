import axios from 'axios';
import store from '@/store/index' 
import router from '@/router/index';
import { Message } from 'element-ui';

// 创建 axios 实例
// let axiosObj = axios.create({
//   baseURL: Global.baseUrl,
//   timeout: 60000

const instance = axios.create({
  baseURL: process.env.VUE_APP_BASEURL,
  timeout: 15000,
});

instance.interceptors.request.use(function (config) {
   //获取vuex里的token
   const token = store.state.token;
   //每次请求都设置token
   if(token) config.headers['token'] = token;
   return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
})

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
  if(response.data.code == '10001'){//登陆失效时的操作
      router.push({ path: '/login'})
  }
  // 获取请求头里的token
  const token = response.headers['token'];
  if (token) {
      //将token存入vuex
      store.commit("setToken",token)
  }
  return response.data;
}, function (error) {
  // 对响应错误做点什么
  return Promise.reject(error);
});


export default instance
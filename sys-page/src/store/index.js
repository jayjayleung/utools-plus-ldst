import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        //登录的token
        token: '',
        //是否登录
        isLogin:false,
        //用户信息
        userInfo:{},
    },
    getters:{
    },
    mutations: {
        setToken (state, token) {
            state.token = token;
        },
        setUserInfo (state, userInfo) {
            state.userInfo = userInfo;
        },
        setDictInfo (state, dictInfo) {
          state.dictInfo = dictInfo;
        },
        // 退出登录
        LOG_OUT(state){
            state.userInfo = {}
            state.token = '';
        }
    },
})

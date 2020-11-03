
const ldstApi = require('@/http/api/ldst.js');



const API = {
    ldstApi
}

export default {
    install(Vue){
        // 将所有接口挂到原型对象里面
        Vue.prototype.$api = API;
    }
}
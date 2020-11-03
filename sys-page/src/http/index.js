
const loginApi = require('@/http/api/login.js');
const warehouseApi = require('@/http/api/warehouse.js');
const warehouseGoodsApi = require('@/http/api/warehouseGoods.js');
const deleveryApi = require('@/http/api/delivery.js');
const tableApi = require('@/http/api/table.js');
const userApi = require('@/http/api/user.js');
const commonApi = require('@/http/api/common.js');



const API = {
    loginApi,
    warehouseApi,
    warehouseGoodsApi,
    deleveryApi,
    tableApi,
    userApi,
    commonApi
}

export default {
    install(Vue){
        // 将所有接口挂到原型对象里面
        Vue.prototype.$api = API;
    }
}
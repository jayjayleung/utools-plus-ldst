import request from '@/http/config';

const add = (params) => {
    return request({
        url:'/warehouse-goods/add',
        method: 'post',
        data:{...params}
    })
}

const list = (params) => {
    return request({
        url:'/warehouse-goods/list',
        method: 'get',
        params:{...params}
    })
}

const listOfQrCode = (params) => {
    return request({
        url:'/mobile/warehouse-goods/list',
        method: 'get',
        params:{...params}
    })
}

export{
    add,
    list,
    listOfQrCode
}
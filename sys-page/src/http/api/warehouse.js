import request from '@/http/config';

// 添加仓库
const add = (params) => {
    return request({
        url:'/warehouse/add',
        method: 'post',
        data:{...params}
    })
}

// 添加列表
const list = (params) => {
    return request({
        url:'/warehouse/list',
        method: 'post',
        data:{...params}
    })
}

const getById = (params) => {
    return request({
        url:'/warehouse/findById',
        method: 'get',
        params:{...params}
    })
}

const getByIdofQrCode = (params) => {
    return request({
        url:'/mobile/warehouse/findById',
        method: 'get',
        params:{...params}
    })
}

export{
    add,
    list,
    getById,
    getByIdofQrCode
}
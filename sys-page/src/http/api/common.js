import request from '@/http/config';

// 添加
const add = (params) => {
    return request({
        url:'/common/add',
        method: 'post',
        data:{...params}
    })
}

// 添加
const getList = (params) => {
    return request({
        url:'/common/getList',
        method: 'get',
        params:{...params}
    })
}

export{
    add,
    getList
}
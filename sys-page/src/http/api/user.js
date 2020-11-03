import request from '@/http/config';

// 添加
const add = (params) => {
    return request({
        url:'/user/add',
        method: 'post',
        data:{...params}
    })
}

// 添加
const edit = (params) => {
    return request({
        url:'/user/edit',
        method: 'post',
        data:{...params}
    })
}

// 添加
const remove = (params) => {
    return request({
        url:'/user/delete',
        method: 'get',
        params:{...params}
    })
}

export {
    add,
    edit,
    remove
}
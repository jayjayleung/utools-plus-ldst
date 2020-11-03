import request from '@/http/config';

// 登录
const login = (params) => {
    return request({
        url:'/login/in',
        method: 'post',
        data:{...params}
    })
}

// 退出登录
const logOut = (params) => {
    return request({
        url:'/login/out',
        method: 'post',
        data: params
    })
}

// 添加
const register = (params) => {
    return request({
        url:'/login/register',
        method: 'post',
        data:{...params}
    })
}

export {
    login,
    logOut,
    register
}
import request from '@/http/config';


const coverList = (params) => {
    return request({
        url:'/ldst-cover/list',
        method: 'post',
        data:{...params}
    })
}

const coverInfo = (params) => {
    return request({
        url:'/ldst-cover/get',
        method: 'get',
        data: {...params}
    })
}

const imgList = (params) => {
    return request({
        url:'/ldst-img/list-all',
        method: 'get',
        data:{...params}
    })
}

const imgInfo = (params) => {
    return request({
        url:'/ldst-img/get',
        method: 'get',
        data:{...params}
    })
}

export {
    coverList,
    coverInfo,
    imgList,
    imgInfo,
}
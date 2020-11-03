/*
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-11-03 21:27:38
 * @LastEditors: jayjay
 * @LastEditTime: 2020-11-04 01:39:44
 */
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
        params: {...params}
    })
}

const imgList = (params) => {
    return request({
        url:'/ldst-img/list-all',
        method: 'get',
        params:{...params}
    })
}

const imgInfo = (params) => {
    return request({
        url:'/ldst-img/get',
        method: 'get',
        data:{...params}
    })
}


const getTypes = (params) => {
    return request({
        url:'/ldst-cover/get-types',
        method: 'get',
        data:{...params}
    })
}

export {
    coverList,
    coverInfo,
    imgList,
    imgInfo,
    getTypes
}
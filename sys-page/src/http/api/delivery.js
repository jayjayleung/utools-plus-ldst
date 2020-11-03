/*
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-07 13:12:00
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-07 19:45:23
 */ 
import request from '@/http/config';

// 添加
const add = (params) => {
    return request({
        url:'/deliveryList/add',
        method: 'post',
        data:{...params}
    })
}

const addItem = (params) => {
    return request({
        url:'/deliveryList/addItem',
        method: 'post',
        data:{...params}
    })
}

// 添加列表
const list = (params) => {
    return request({
        url:'/deliveryList/list',
        method: 'post',
        data:{...params}
    })
}

const getById = (params) => {
    return request({
        url:'/deliveryList/findById',
        method: 'get',
        params:{...params}
    })
}
const getInfoById = (params) => {
    return request({
        url:'/mobile/deliveryListInfo/findById',
        method: 'get',
        params:{...params}
    })
}
const setPrint = (params) => {
    return request({
        url:'/deliveryListInfo/setPrint',
        method: 'get',
        params:{...params}
    })
}
const findListByMap = (params) => {
    return request({
        url:'/deliveryListInfo/findListByMap',
        method: 'post',
        data:{...params}
    })
}

const remove = (params) => {
    return request({
        url:'/deliveryList/delete',
        method: 'get',
        params:{...params}
    })
}

export{
    add,
    list,
    getById,
    findListByMap,
    getInfoById,
    addItem,
    setPrint,
    remove
}
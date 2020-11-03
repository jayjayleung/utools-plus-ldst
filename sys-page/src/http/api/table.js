import request from '@/http/config';

// 表格组件请求list接口
const getList = (url,method,params) => {
    return request({
        url:url,
        method: method,
        data:{...params}
    })
}

export {
  getList
}
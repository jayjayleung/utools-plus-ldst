package com.jayjay.ldst.config;


import java.io.Serializable;
import java.util.List;

/**
 * @author jayjay
 * @date 2019/11/1418:46
 * @Description: 返回类
 */
public class ResultModel<T>  implements Serializable {

    private static final long serialVersionUID = 5858874322899422740L;


    /**
     * 请求标识，默认为失败状态
     */
    private boolean success = false;

    /**
     * 状态码，默认为失败状态
     */
    private Integer code = StatusCode.ERROR;

    /***
     * 操作信息
     */
    private String msg;

    /**
     * 返回对象数据
     */
    private T data;


    /**
     * token 是否过期
     */
    private boolean expired = false;



    /**
     * 成功响应
     */
    public void OK() {
        this.success = true;
        this.code = StatusCode.SUCCESS;
    }

    /**
     * 请求成功，但业务逻辑处理不通过
     */
    public void NO() {
        this.success = true;
        this.code = StatusCode.ERROR;
    }

    public ResultModel() {
        super();
    }

    public ResultModel(int code, String msg) {
        super();
        this.success = true;
        this.code = code;
        this.msg = msg;
    }



    public ResultModel(int code, String msg,T t) {
        super();
        this.success = true;
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.success = true;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "JsonVo [success=" + success + ", code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

    public static ResultModel Success(){
        ResultModel resultModel = new ResultModel();
        resultModel.code = StatusCode.ERROR;
        resultModel.msg = StatusCode.SUCCESS_MSG;
        return resultModel;
    }

    public static ResultModel Success(String msg){
        ResultModel resultModel = new ResultModel();
        resultModel.code = StatusCode.SUCCESS;
        resultModel.msg = msg;
        return resultModel;
    }

    public static ResultModel Success(List list){
        ResultModel resultModel = new ResultModel(StatusCode.SUCCESS,StatusCode.SUCCESS_MSG,list);
        return resultModel;
    }
    public static ResultModel Success(String msg,List list){
        ResultModel resultModel = new ResultModel(StatusCode.SUCCESS,msg,list);
        return resultModel;
    }

    public static ResultModel Success(String msg,Object obj){
        ResultModel resultModel = new ResultModel(StatusCode.SUCCESS,msg,obj);
        return resultModel;
    }

    public static ResultModel Success(Integer code,String msg,List list){
        ResultModel resultModel = new ResultModel(code,msg,list);
        return resultModel;
    }

    public static ResultModel Success(Integer code,String msg,Object t){
        ResultModel resultModel = new ResultModel(code,msg,t);
        return resultModel;
    }

    public static ResultModel Success(Object t){
        ResultModel resultModel = new ResultModel(StatusCode.SUCCESS,StatusCode.SUCCESS_MSG,t);
        return resultModel;
    }

    public static ResultModel ERROR(){
        ResultModel resultModel = new ResultModel();
        resultModel.code = StatusCode.ERROR;
        resultModel.msg = StatusCode.ERROR_MSG;
        return resultModel;
    }

    public static ResultModel ERROR(String msg){
        ResultModel resultModel = new ResultModel();
        resultModel.code = StatusCode.ERROR;
        resultModel.msg = msg;
        return resultModel;
    }

    public static ResultModel ERROR(Integer code,String msg){
        ResultModel resultModel = new ResultModel();
        resultModel.code = code;
        resultModel.msg = msg;
        return resultModel;
    }

    public static ResultModel ERROR(Integer code,String msg,Object t){
        ResultModel resultModel = new ResultModel();
        resultModel.code = code;
        resultModel.msg = msg;
        resultModel.data = t;
        return resultModel;
    }


}

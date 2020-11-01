package com.jayjay.ldst.config;

/**
 * @author jayjay
 * @date 2019/11/1419:02
 * @Description: 状态码
 */
public  class StatusCode {


    public static final Integer ERROR = 500;
    public static final Integer SUCCESS_304 = 304;

    public static final Integer SUCCESS = 200;

    public static final String ERROR_MSG = "请求失败，请联系管理员";

    public static final String SUCCESS_MSG = "请求成功。";
    public static final Integer NOT_FOUND = 501;
    public static final Integer UNLAWFUL = 24;
    public static final int NOT_LOGIN = 10001;
    public static final Integer PARAM_ERROR = 10002;
    public static final Integer PASSWORD_ERROR = 10003;
    public static final String PASSWORD_ERROR_MSG = "密码错误";
}

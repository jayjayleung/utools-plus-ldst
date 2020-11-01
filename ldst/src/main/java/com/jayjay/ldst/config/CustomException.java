package com.jayjay.ldst.config;

/**
 * 自定义异常(CustomException)
 * @author jayjay
 * @date 2019/12/3 13:59
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg){
        super(msg);
    }

    public CustomException() {
        super();
    }
}

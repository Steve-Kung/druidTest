package com.steve.springboot.error;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.error
 * @version: 1.0
 */
public class BusinessException extends RuntimeException {
    public BusinessException(){

    }
    public BusinessException(String message){
        super(message);
    }
}

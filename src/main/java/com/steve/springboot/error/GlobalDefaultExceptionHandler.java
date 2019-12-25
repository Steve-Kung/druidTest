package com.steve.springboot.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.error
 * @version: 1.0
 */
// 定义统一的异常处理类 basePackages属性用于定义扫描哪些包
@ControllerAdvice(basePackages = {"com.steve.springboot"})
public class GlobalDefaultExceptionHandler {
    // 定义函数针对的异常类型，可以传入多个需要捕获的异常类
    @ExceptionHandler({BusinessException.class})
    //如果返回的是json数据或者其他对象，就添加该注解
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception{
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(req.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);
        return errorInfo;
    }
}

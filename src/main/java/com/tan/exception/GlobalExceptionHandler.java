package com.tan.exception;

import com.tan.entity.EntityResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理所有的异常
    @ExceptionHandler(value = Exception.class)
    public EntityResult ex(Exception e) {
        //e.printStackTrace();
        return EntityResult.error(e.getMessage());
    }

}
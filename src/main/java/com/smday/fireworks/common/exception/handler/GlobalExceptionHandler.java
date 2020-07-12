package com.smday.fireworks.common.exception.handler;

import com.smday.fireworks.common.api.AjaxResult;
import com.smday.fireworks.common.exception.ApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author Summerday
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public AjaxResult handle(ApiException e){
        if(e.getErrorCode()!=null){
            return AjaxResult.error(e.getErrorCode().getCode(), e.getMessage());
        }
        return AjaxResult.error(e.getMessage());
    }

}

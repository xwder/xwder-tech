package com.xwder.example.advice;


import com.xwder.example.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @author xwder
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 全局异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult defaultException(HttpServletRequest request, Exception e) {
        String exceptionName = e.getClass().getName();
        log.error("{}", exceptionName, e);
        return CommonResult.failed(e.getMessage());

    }

    /**
     * 自定义异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    public CommonResult myException(HttpServletRequest request, MyException e) {
        Integer code = e.getCode();
        String message = e.getMessage();
        String exceptionName = e.getClass().getName();
        log.error("{}", exceptionName, e);
        return CommonResult.failed(code, message);
    }

    /**
     * 自定义业务异常
     *
     * @param bizException
     * @return
     */
    @ExceptionHandler(BizException.class)
    public CommonResult handleCustomException(BizException bizException) {
        String exceptionName = bizException.getClass().getName();
        log.info("{}-{}", exceptionName, bizException.getMessage(), bizException);
        return CommonResult.failed(bizException.getCode(), bizException.getMessage());
    }


    /**
     * 方法参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}", MethodArgumentNotValidException.class.getName(), e);
        return CommonResult.failed(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
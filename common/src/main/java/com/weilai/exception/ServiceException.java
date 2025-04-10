package com.weilai.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.miku.common.Result;
import com.miku.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ServiceException {

    @ExceptionHandler(CustomException.class)
    public Result bizExceptionHandler(CustomException e) {
        log.error("【业务异常】: {}", e.getMsg());
        return Result.fail("500", e.getMsg());
    }

    @ExceptionHandler(NotLoginException.class)
    public Result NotLoginExceptionExceptionHandler(NotLoginException e) {
        log.error("【登录异常】: {}", ExceptionUtils.getStackTrace(e));
        return Result.fail("401", e.getMessage());
    }
    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("【系统异常】: {}", ExceptionUtils.getStackTrace(e));
        // 处理异常
        if (e instanceof MethodArgumentNotValidException) {
            // 参数检验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            return Result.fail("500", result.getFieldErrors().get(0).getDefaultMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.fail("500", "请求方法不正确");
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            return Result.fail("500", "请求参数缺少: " + ex.getParameterName());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            return Result.fail("500", "请求参数类型不正确：" + ex.getName());
        } else if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            return Result.fail("500", ex.getRequestURL());
        } else {
            log.error("【系统异常】: {}", ExceptionUtils.getStackTrace(e));
            return Result.fail("500", "系统异常，请联系管理员");
        }
    }
}

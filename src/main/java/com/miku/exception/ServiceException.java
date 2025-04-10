package com.miku.exception;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.NumberUtil;
import com.miku.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ServiceException extends RuntimeException{
    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleBusinessException(ServiceException e, HttpServletRequest request) {
        log.error("[{}] {}", request.getMethod(), request.getRequestURI(), e);
        return Result.fail(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
    }

    /**
     * 拦截自定义验证异常-错误请求
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleBadRequestException(ServiceException e, HttpServletRequest request) {
        log.error("[{}] {}", request.getMethod(), request.getRequestURI(), e);
        return Result.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
    }

    /**
     * 拦截校验异常-方法参数类型不匹配异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                       HttpServletRequest request) {
        log.error("[{}] {}", request.getMethod(), request.getRequestURI(), e);
        return Result.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), "参数 '%s' 类型不匹配".formatted(e.getName()));
    }

    /**
     * 拦截文件上传异常-超过上传大小限制
     */
    @ExceptionHandler(MultipartException.class)
    public Result handleRequestTooBigException(MultipartException e, HttpServletRequest request) {
        log.error("[{}] {}", request.getMethod(), request.getRequestURI(), e);
        String msg = e.getMessage();
        Result defaultFail = Result.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), msg);
        if (CharSequenceUtil.isBlank(msg)) {
            return defaultFail;
        }
        String sizeLimit;
        Throwable cause = e.getCause();
        if (null != cause) {
            msg = msg.concat(cause.getMessage().toLowerCase());
        }
        if (msg.contains("size") && msg.contains("exceed")) {
            sizeLimit = CharSequenceUtil.subBetween(msg, "the maximum size ", " for");
        } else if (msg.contains("larger than")) {
            sizeLimit = CharSequenceUtil.subAfter(msg, "larger than ", true);
        } else {
            return defaultFail;
        }
        String errorMsg = "请上传小于 %sKB 的文件".formatted(NumberUtil.parseLong(sizeLimit) / 1024);
        return Result.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorMsg);
    }
}

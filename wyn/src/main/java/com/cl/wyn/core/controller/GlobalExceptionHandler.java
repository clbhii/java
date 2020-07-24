package com.cl.wyn.core.controller;

import com.cl.wyn.core.common.BizException;
import com.cl.wyn.core.common.ErrorCode;
import com.cl.wyn.core.common.Result;
import com.cl.wyn.core.common.ResultSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value =BizException.class)
    @ResponseBody
    public Result<Void> exceptionHandler(BizException e){
        log.error(e.getMessage(), e);
        Result<Void> result = new ResultSupport<>(false, e.getErrorCode(), e.getMessage());
        return result;
    }

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result<Void> exceptionHandler(Exception e){
        log.error(e.getMessage(), e);
        Result<Void> result = new ResultSupport<>(false, ErrorCode.INTERNAL_SERVER_ERROR.getResultCode(), ErrorCode.INTERNAL_SERVER_ERROR.getResultMsg());
        return result;
    }
}
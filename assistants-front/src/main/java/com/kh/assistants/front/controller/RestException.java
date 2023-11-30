package com.kh.assistants.front.controller;

import io.github.youkehai.assistant.core.resp.ErrorResp;
import io.github.youkehai.assistant.exception.OpenaiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class RestException {

    /**
     * openai 错误异常
     * HTTP 状态为 500
     * 可以使用自己封装的返回对象继续包装
     *
     * @param e 异常信息
     * @return 返回值
     */
    @ExceptionHandler(OpenaiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResp handleError(OpenaiException e) {
        return new ErrorResp().setCode(e.getCode()).setType(e.getType())
                .setMessage(e.getMessage()).setParam(e.getParam());
    }
}

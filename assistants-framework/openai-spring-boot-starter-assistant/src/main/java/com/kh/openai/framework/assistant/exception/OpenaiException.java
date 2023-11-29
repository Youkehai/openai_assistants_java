package com.kh.openai.framework.assistant.exception;

import com.kh.openai.framework.assistant.core.resp.ErrorResp;
import lombok.Getter;

/**
 * openai 返回异常
 */
@Getter
public class OpenaiException extends RuntimeException {

    private String type;

    private String param;

    private String code;

    public OpenaiException(ErrorResp errorResp) {
        super(errorResp.getMessage());
        this.code = errorResp.getCode();
        this.param = errorResp.getParam();
        this.type = errorResp.getType();
    }
}

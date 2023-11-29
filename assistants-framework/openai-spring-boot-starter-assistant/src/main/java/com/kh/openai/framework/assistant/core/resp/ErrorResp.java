package com.kh.openai.framework.assistant.core.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * openai 错误返回值
 */
@Data
@Accessors(chain = true)
public class ErrorResp {

    private String message;

    private String type;

    private String param;

    private String code;
}

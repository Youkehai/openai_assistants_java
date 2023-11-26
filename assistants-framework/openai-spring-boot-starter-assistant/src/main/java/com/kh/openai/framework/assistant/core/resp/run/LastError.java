package com.kh.openai.framework.assistant.core.resp.run;

import lombok.Data;

/**
 * 上次任务的报错
 */
@Data
public class LastError {

    private String code;

    private String message;
}

package com.kh.openai.framework.assistant.core.req.message;

import lombok.Data;

/**
 * 发送完消息之后直接 run
 */
@Data
public class CreateAndRunReq {

    /**
     * 助理 ID
     */
    private String assistant_id;

    /**
     * 消息内容
     */
    private String content;
}

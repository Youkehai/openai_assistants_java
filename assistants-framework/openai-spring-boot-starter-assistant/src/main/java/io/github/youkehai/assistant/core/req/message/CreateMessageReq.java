package io.github.youkehai.assistant.core.req.message;

import io.github.youkehai.assistant.core.req.BaseReq;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 发送消息
 */
@Data
@Accessors(chain = true)
public class CreateMessageReq extends BaseReq {

    /**
     * 角色 发送消息只支持 user
     */
    private String role = "user";

    /**
     * 消息内容
     */
    private String content;

    private String[] file_ids;
}

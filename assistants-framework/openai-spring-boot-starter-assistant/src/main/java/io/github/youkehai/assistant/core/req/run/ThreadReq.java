package io.github.youkehai.assistant.core.req.run;

import io.github.youkehai.assistant.core.req.BaseReq;
import io.github.youkehai.assistant.core.req.message.CreateMessageReq;
import lombok.Data;

import java.util.List;

/**
 * 创建线程，并且立即 run
 */
@Data
public class ThreadReq extends BaseReq {

    /**
     * 发送的消息内容
     */
    private List<CreateMessageReq> messages;
}

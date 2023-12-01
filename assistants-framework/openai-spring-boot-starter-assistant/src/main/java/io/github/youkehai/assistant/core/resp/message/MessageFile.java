package io.github.youkehai.assistant.core.resp.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.resp.BaseResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息文件对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageFile extends BaseResp {

    /**
     * 消息 ID
     */
    @JsonProperty("message_id")
    private String message_id;
}

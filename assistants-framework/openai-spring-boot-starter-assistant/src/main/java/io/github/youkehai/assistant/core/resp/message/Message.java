package io.github.youkehai.assistant.core.resp.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.resp.BaseResp;
import lombok.Data;

import java.util.List;

/**
 * 消息内容
 */
@Data
public class Message extends BaseResp {

    /**
     * 线程ID
     */
    @JsonProperty("thread_id")
    private String threadId;

    /**
     * 角色
     * One of user or assistant.
     */
    private String role;

    /**
     * 消息内容
     */
    private List<Content> content;

    /**
     * 助理ID
     */
    @JsonProperty("assistant_id")
    private String assistantId;

    @JsonProperty("run_id")
    private String runId;

    @JsonProperty("file_ids")
    private String[] fileIds;

}

package io.github.youkehai.assistant.core.resp.run;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * run step 的详情信息
 */
@Data
public class RunStepDetail {

    /**
     * 总是 message_creation 或者 tool_calls
     */
    private String type;

    /**
     * 当 type == message_creation 时，该对象会有值
     * 表示改步骤由 messageCreation 中的消息创建
     */
    @JsonProperty("message_creation")
    private MessageCreation messageCreation;

    /**
     * messageCreation
     */
    @Data
    public static class MessageCreation{
        /**
         * 此运行步骤创建的消息的 ID
         */
        @JsonProperty("message_id")
        private String messageId;
    }

    /**
     * 运行步骤所涉及的一组工具调用。
     * 这些调用可以与三种类型的工具之一相关联：
     * code_interpreter、retrieval 或 function。
     * {@link io.github.youkehai.assistant.core.constant.ToolTypeEnum}
     */
    @JsonProperty("tool_calls")
    public List<RunToolCall> toolCalls;

}

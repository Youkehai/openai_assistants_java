package com.kh.openai.framework.assistant.core.resp.run;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 当前任务，必须的动作
 */
@Data
public class RequiredAction {

    /**
     * 当前必须的动作类型，目前只能是 submit_tool_outputs
     */
    private String type;

    @JsonProperty("submit_tool_outputs")
    private SubmitToolOutputs submitToolOutputs;

    /**
     * 需要提交的返回值信息
     */
    @Data
    public static class SubmitToolOutputs{
        /**
         * 目前需要提交返回值的所有函数信息
         */
        @JsonProperty("tool_calls")
        private List<ToolCall> toolCalls;
    }

    /**
     * 每一个需要等到调用的方法详情
     */
    @Data
    public static class ToolCall{
        /**
         * 当前正在等待调用的 id
         * 在提交返回值时，需要用到它
         */
        private String id;

        /**
         * 本次等待 call 的类型，目前只是 function
         */
        private String type;

        /**
         * 等待调用的函数详情，包含方法名称和入参
         */
        private Function function;
    }

    /**
     * 目前正在等待调用的 function 信息
     */
    @Data
    public static class Function{
        /**
         * 函数名称
         */
        private String name;

        /**
         * 调用该函数的入参
         */
        private String arguments;
    }


}

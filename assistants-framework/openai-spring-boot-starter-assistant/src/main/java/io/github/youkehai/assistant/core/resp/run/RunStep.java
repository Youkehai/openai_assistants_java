package io.github.youkehai.assistant.core.resp.run;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 表示运行执行中的一个步骤
 * 一个 run 任务会分为很多步骤，这个是具体说明当前 run 运行过的具体步骤返回值
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RunStep extends RunBaseResp {

    @JsonProperty("run_id")
    private String runId;

    /**
     * 运行步骤的类型，可以是
     * message_creation (模型自己生成)
     * 或
     * tool_calls(通过 assistant 配置的具体 tool 得到的结果)
     */
    private String type;

    /**
     * run step 的详情信息
     * 如果调用了 function ，会记录具体调用了哪个 function，使用了什么参数，并且 function 给出的返回值
     * 如果调用了 code_interpreter ,也会记录具体调用的返回值明细
     */
    @JsonProperty("step_details")
    private RunStepDetail stepDetails;

}

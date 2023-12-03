package io.github.youkehai.assistant.core.resp.run;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 运行任务返回值
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Run extends RunBaseResp {

    /**
     * 当前 run 必须完成的动作，可能为空或一个对象
     */
    @JsonProperty("required_action")
    private RequiredAction requiredAction;

    @JsonIgnore
    private Long started_at;

    /**
     * 开始运行的时间
     */
    private String startedAt;

    public String getStartedAt() {
        return DateUtil.formatDateByTimeStamp(started_at);
    }

    /**
     * 当前任务用的模型
     */
    private String model;
    /**
     * 用到的 assistants 中编写的 instructions 内容
     */
    private String instructions;
    /**
     * 本次 run 的任务中，用到的所有 tool
     */
    private List<Tool> tools;

    @JsonProperty("file_ids")
    private List<String> fileIds;
}

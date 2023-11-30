package io.github.youkehai.assistant.core.resp.run;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.constant.RunStatusEnum;
import io.github.youkehai.assistant.core.resp.BaseResp;
import io.github.youkehai.assistant.core.util.DateUtil;
import lombok.Data;

import java.util.List;

/**
 * 运行任务返回值
 */
@Data
public class Run extends BaseResp {

    @JsonProperty("thread_id")
    private String threadId;

    @JsonProperty("assistant_id")
    private String assistantId;


    /**
     * 当前任务状态
     * {@link RunStatusEnum}
     */
    private String status;

    /**
     * 当前 run 必须完成的动作，可能为空或一个对象
     */
    @JsonProperty("required_action")
    private RequiredAction requiredAction;
    /**
     * 上一次的错误原因
     */
    @JsonProperty("last_error")
    private LastError lastError;

    @JsonIgnore
    private Long expires_at;

    /**
     * 过期时间
     */
    private String expiresAt;

    public String getExpiresAt() {
        return DateUtil.formatDateByTimeStamp(expires_at);
    }

    @JsonIgnore
    private Long started_at;

    /**
     * 开始运行的时间
     */
    private String startedAt;

    public String getStartedAt() {
        return DateUtil.formatDateByTimeStamp(started_at);
    }

    @JsonIgnore
    private Long cancelled_at;

    /**
     * 取消时间
     */
    private String cancelledAt;

    public String getCancelledAt() {
        return DateUtil.formatDateByTimeStamp(cancelled_at);
    }

    @JsonIgnore
    private Long failed_at;

    /**
     * 失败时间
     */
    private String failedAt;

    public String getFailedAt() {
        return DateUtil.formatDateByTimeStamp(failed_at);
    }

    @JsonIgnore
    private Long completed_at;

    /**
     * 完成时间
     */
    private String completedAt;

    public String getCompletedAt() {
        return DateUtil.formatDateByTimeStamp(completed_at);
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
    private String fileIds;
}

package io.github.youkehai.assistant.core.resp.run;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.constant.RunStatusEnum;
import io.github.youkehai.assistant.core.resp.BaseResp;
import io.github.youkehai.assistant.core.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * run 的返回值基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RunBaseResp extends BaseResp {

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
}

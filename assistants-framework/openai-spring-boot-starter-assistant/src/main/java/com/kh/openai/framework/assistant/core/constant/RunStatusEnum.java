package com.kh.openai.framework.assistant.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 运行任务状态枚举
 */
@AllArgsConstructor
@Getter
public enum RunStatusEnum {

    EXPIRED("expired", "过期"),
    COMPLETED("completed", "已完成"),
    FAILED("failed", "执行失败"),
    CANCELLED("cancelled", "已取消"),
    CANCELLING("cancelling", "正在取消"),
    REQUIRES_ACTION("requires_action", "在等待必要动作"),
    IN_PROGRESS("in_progress", "正在处理"),
    QUEUED("queued", "队列中");

    /**
     * 对应状态
     */
    private final String status;

    /**
     * 中文描述
     */
    private final String descZh;

    /**
     * 判断当前状态是否属于结束状态
     * 目前： 过期,已完成，执行失败，已取消 四个状态都算结束状态
     *
     * @param status 被判断的状态
     * @return true 表示该 run 已结束
     */
    public static boolean isEnd(String status) {
        return EXPIRED.status.equals(status) || COMPLETED.status.equals(status)
                || FAILED.status.equals(status) || CANCELLED.status.equals(status);
    }
}

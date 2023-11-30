package io.github.youkehai.assistant.core.resp.thread;

import io.github.youkehai.assistant.core.resp.BaseResp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * https://platform.openai.com/docs/api-reference/threads/object
 * 线程返回值
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Thread extends BaseResp {
    /**
     * 需要有一个属性，不然 jackson 无法处理
     */
    private String id;
}

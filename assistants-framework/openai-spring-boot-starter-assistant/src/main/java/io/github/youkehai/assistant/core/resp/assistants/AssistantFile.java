package io.github.youkehai.assistant.core.resp.assistants;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.resp.BaseResp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 绑定助理的文件
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AssistantFile extends BaseResp {

    /**
     * 助理 ID
     */
    @JsonProperty("assistant_id")
    private String assistantId;
}

package io.github.youkehai.assistant.core.req.assistants;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.req.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 文件绑定 assistant
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BindFileReq extends BaseReq {

    /**
     * 文件ID，已有 file 中选一个绑定具体的 assistant
     */
    @JsonProperty("file_id")
    private String fileId;
}

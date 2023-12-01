package io.github.youkehai.assistant.core.req.assistants;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.req.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * 给指定的助理上传文件
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreateAssistantFileReq extends BaseReq {

    /**
     * 文件ID，已有 file 中选一个绑定具体的 assistant
     */
    @JsonProperty("file_id")
    private String fileId;

    /**
     * 上传新的文件，并绑定 assistant
     */
    private MultipartFile file;

    /**
     * 助理ID
     */
    private String assistantId;
}

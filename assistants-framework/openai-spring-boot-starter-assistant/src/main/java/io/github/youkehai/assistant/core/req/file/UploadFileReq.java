package io.github.youkehai.assistant.core.req.file;

import io.github.youkehai.assistant.core.constant.FilePurposeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
public class UploadFileReq {

    /**
     * 上传文件的意图，用于给 openai 校验文件上传格式是否正确
     * 目前支持 fine-tune 和 assistants
     * {@link FilePurposeEnum}
     */
    private String purpose;

    /**
     * 文件
     */
    private MultipartFile file;
}

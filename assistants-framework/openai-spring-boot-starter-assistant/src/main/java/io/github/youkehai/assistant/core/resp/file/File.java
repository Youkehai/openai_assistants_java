package io.github.youkehai.assistant.core.resp.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.resp.BaseResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 上传文件之后的返回值
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class File extends BaseResp {

    private String purpose;

    private String filename;

    private Long bytes;

    private String status;

    @JsonProperty("status_details")
    private String statusDetails;

    /**
     * 对文件名进行 url 解码
     *
     * @return 解码后的文件名
     */
    public String getFileName() {
        return URLDecoder.decode(this.filename, StandardCharsets.UTF_8);
    }
}

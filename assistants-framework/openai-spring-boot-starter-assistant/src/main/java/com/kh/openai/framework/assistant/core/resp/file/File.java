package com.kh.openai.framework.assistant.core.resp.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kh.openai.framework.assistant.core.resp.BaseResp;
import lombok.Data;

/**
 * 上传文件之后的返回值
 */
@Data
public class File extends BaseResp {

    private String purpose;

    private String filename;

    private Long bytes;

    private String status;

    @JsonProperty("status_details")
    private String statusDetails;
}

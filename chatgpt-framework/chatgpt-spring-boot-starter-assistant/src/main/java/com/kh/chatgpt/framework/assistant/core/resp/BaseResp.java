package com.kh.chatgpt.framework.assistant.core.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.chatgpt.framework.assistant.core.util.DateUtil;
import lombok.Data;

import java.util.Map;

@Data
public class BaseResp {

    private String createAt;

    @JsonIgnore
    private Long created_at;

    public String getCreateAt() {
        return DateUtil.formatDateByTimeStamp(created_at);
    }

    /**
     * ID
     */
    private String id;

    /**
     * 类型，例如：thread , message,assistant
     */
    private String object;

    /**
     * metadata 字段：可以附加到对象的 16 对键值对。
     * 这对于以结构化格式存储有关对象的额外信息很有用。
     * 键的最大长度为 64 个字符，值的最大长度为 512 个字符
     */
    private Map<String, String> metadata;

}

package io.github.youkehai.assistant.core.resp.assistants;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.youkehai.assistant.core.resp.BaseResp;
import io.github.youkehai.assistant.core.resp.run.Tool;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * https://platform.openai.com/docs/api-reference/assistants
 * Assistants 对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Assistants extends BaseResp {

    /**
     * 助手的名称，最大长度为 256 个字符
     */
    private String name;

    /**
     * 助手的描述，最大长度为 512 个字符
     */
    private String description;

    /**
     * 使用的模型 ID。可以使用 List models API 查看所有可用模型
     */
    // model 字段：
    private String model;

    /**
     * 助手使用的系统指令，最大长度为 32768 个字符
     */
    private String instructions;

    /**
     * 助手启用的工具列表。每个助手最多可以有 128 个工具。工具类型可以是 code_interpreter、retrieval 或 function
     */
    private List<Tool> tools;

    /**
     * 附加到此助手的文件 ID 列表。助手最多可以附加 20 个文件。文件按创建日期升序排列
     */
    @JsonProperty("file_ids")
    private List<String> fileIds;

}

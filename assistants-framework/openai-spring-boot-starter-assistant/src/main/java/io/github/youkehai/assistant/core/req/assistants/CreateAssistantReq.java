package io.github.youkehai.assistant.core.req.assistants;

import io.github.youkehai.assistant.core.resp.run.Tool;
import io.github.youkehai.assistant.core.req.BaseReq;
import lombok.Data;

import java.util.List;

/**
 * 创建 assistant
 */
@Data
public class CreateAssistantReq extends BaseReq {

    /**
     * 使用的模型
     */
    private String model;

    /**
     * 助理名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 指令，说明书，这个内容需要写好，
     * 你创建的助理，会按照这个指令优先进行参考，来回答你的问题
     */
    private String instructions;

    /**
     * 创建时，用到的工具
     */
    private List<Tool> tools;

    /**
     * 需要的文件
     */
    private String[] file_ids;

}

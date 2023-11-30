package io.github.youkehai.assistant.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工具类型
 */
@Getter
@AllArgsConstructor
public enum ToolTypeEnum {
    /**
     * 开发者需要处理的函数
     */
    FUNCTION("function"),
    /**
     * 从你上传的知识库中获取知识
     */
    RETRIEVAL("retrieval"),
    /**
     * 能够运行你给的代码片段，并给出返回值
     */
    CODE_INTERPRETER("code_interpreter");


    /**
     * 类型
     */
    private final String type;
}

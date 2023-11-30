package io.github.youkehai.assistant.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePurposeEnum {

    /**
     * 使用 assistants api 时使用到的文件，使用该类型
     */
    ASSISTANTS("assistants"),
    /**
     * 微调模型时，传入该类型
     */
    FINE_TUNE("fine-tune");

    private String name;

}

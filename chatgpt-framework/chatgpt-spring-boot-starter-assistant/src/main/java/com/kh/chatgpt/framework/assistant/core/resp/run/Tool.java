package com.kh.chatgpt.framework.assistant.core.resp.run;

import lombok.Data;

/**
 * 当前 run 用到的 tool
 * 具体有哪些 tool
 * 见页面 ： https://platform.openai.com/playground 并选择 Assistants
 */
@Data
public class Tool {

    /**
     * 类型
     * 目前主要分三个：
     * code_interpreter(能够运行你给的代码片段，并给出返回值) ，
     * retrieval（从你上传的知识库中获取知识） ，
     * function(开发者需要处理的函数)
     */
    private String type;

    private Function function;

    @Data
    public static class Function{
        /**
         * 函数描述
         */
        private String description;

        /**
         * 函数名称
         */
        private String name;

        /**
         * 参数
         * 它会是一个 json 串，可以直接使用 json 框架解析成需要的格式
         */
        private String parameters;
    }
}

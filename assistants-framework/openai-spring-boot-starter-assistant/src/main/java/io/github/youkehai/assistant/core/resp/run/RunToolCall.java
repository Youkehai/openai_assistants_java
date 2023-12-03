package io.github.youkehai.assistant.core.resp.run;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * run step 的详情信息中 type 为 tool_calls 的每种 tool 的明细
 */
@Data
public class RunToolCall {

    /**
     * tool 对应的 ID
     */
    private String id;

    /**
     * 调用的 tool 类型
     * {@link io.github.youkehai.assistant.core.constant.ToolTypeEnum}
     */
    private String type;

    /**
     * 代码解释器的调用详情
     */
    private CodeInterpreter code_interpreter;

    /**
     * 当 type 为 {@link io.github.youkehai.assistant.core.constant.ToolTypeEnum#CODE_INTERPRETER}时
     * 使用该类
     */
    @Data
    public static class CodeInterpreter {
        /**
         * 输入
         */
        private String input;

        /**
         * 输出信息
         */
        public List<CodeInterpreterOutput> outputs;
    }

    /**
     * 代码解释器的输出
     */
    @Data
    public static class CodeInterpreterOutput {
        /**
         * 代码解释器输出类型，分为 logs 和 image
         */
        private String type;

        /**
         * 当 type 为 logs 时，输出到该字段
         */
        private String logs;
        /**
         * 当 type 为 image 时，输出到该字段
         */
        private CodeInterpreterOutputImage image;
    }

    /**
     * 代码解释器的图片输出
     */
    @Data
    public static class CodeInterpreterOutputImage {
        /**
         * 图片输出到的文件 ID
         */
        @JsonProperty("file_id")
        private String fileId;
    }

    /**
     * 当 type == retrieval 时
     * 不过，目前位置，官方文档解释，该对象为空，暂不返回内容
     */
    private Map<String, Object> retrieval;

    /**
     * 当 type == function 时返回具体的函数执行信息
     */
    private Function function;

    /**
     * 当本次 run 的 step 调用到具体定义的 function 时，会写入该对象
     */
    @Data
    public static class Function {
        /**
         * 本次 run 的 step 调用到的 function name
         */
        private String name;

        /**
         * 调用函数时的入参
         */
        private String arguments;

        /**
         * 调用后，函数的返回值
         */
        private String output;
    }

}

package io.github.youkehai.assistant.core.req.run;

import io.github.youkehai.assistant.core.req.BaseReq;
import lombok.Data;

import java.util.List;

/**
 * 提交必要任务的返回值
 */
@Data
public class SubmitOutputReq extends BaseReq {

    private List<Item> tool_outputs;

    /**
     * 提交的内容
     */
    @Data
    public static class Item {

        /**
         * 本次是给哪个 call 提交的返回值
         * 此 id 来自接口 runs 中的返回值
         */
        private String tool_call_id;

        /**
         * 执行完 callId 对应的函数后，得到的返回值，如果为 json，需要转换为 string 提交
         */
        private String output;
    }

}

package io.github.youkehai.assistant.core.constant;

import cn.hutool.core.collection.CollUtil;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

import static io.github.youkehai.assistant.core.constant.RequestEnum.*;


@AllArgsConstructor
@Getter
public enum RequestUrlEnum {

    /*******************************Thread Start************************************/

    CREATE_THREAD("/threads", POST, "创建线程"),
    RETRIEVE_THREAD("/threads/{threadId}", GET, "获取线程详情"),
    DELETE_THREAD("/threads/{threadId}", DELETE, "删除线程"),

    /*******************************Thread End************************************/

    /*******************************Message Start************************************/

    CREATE_MESSAGE("/threads/{threadId}/messages", POST, "发送消息"),
    RETRIEVE_MESSAGE("/threads/{threadId}/messages/{messageId}", GET, "获取消息详情"),
    LIST_MESSAGE("/threads/{threadId}/messages", GET, "通过线程ID，获取所有聊天记录"),
    LIST_MESSAGE_FILE("/threads/{threadId}/messages/{messageId}/files",GET,"获取指定消息用到的文件"),

    /*******************************Message End************************************/

    /*******************************RUNs Start************************************/

    CREATE_RUN("/threads/{threadId}/runs", POST, "运行消息"),
    RETRIEVE_RUN("/threads/{threadId}/runs/{runId}", GET, "获取指定run的运行情况"),
    LIST_RUN("/threads/{threadId}/runs", GET, "获取所有run"),
    SUBMIT_TOOL_OUTPUTS_TO_RUN("/threads/{threadId}/runs/{runId}/submit_tool_outputs", POST, "提交返回值给指定的run"),
    CANCEL_RUN("/threads/{threadId}/runs/{runId}/cancel", POST, "取消指定run的运行"),
    CREATE_THREAD_AND_RUN("/threads/runs", POST, "创建线程，并发送消息和run"),

    /*******************************RUNs End************************************/

    /*******************************Assistant Start************************************/
    CREATE_ASSISTANTS("/assistants", POST, "创建 assistants"),
    RETRIEVE_ASSISTANT("/assistants/{assistantsId}", RequestEnum.GET, "获取 assistants 详情"),
    CREATE_ASSISTANT_FILE("/assistants/{assistantsId}/files", POST,"将文件和 assistant 绑定"),
    LIST_ASSISTANT_FILE("/assistants/{assistantsId}/files",GET,"查询指定 assistant 的 file 列表"),
    DELETE_ASSISTANT_FILE("/assistants/{assistantsId}/files/{fileId}",DELETE,"删除 assistant 中的指定文件"),
    RETRIEVE_ASSISTANT_FILE("/assistants/{assistantsId}/files/{fileId}", RequestEnum.GET, "获取 assistants file 详情"),

    /*******************************Assistant End************************************/
    UPLOAD_FILE("/files", POST, "上传文件"),
    ;

    private final String url;
    private final RequestEnum method;
    private final String desc;

    public String getUrl(String baseurl, CommonPathReq commonPathReq) {
        String result = baseurl + this.url;
        if (commonPathReq == null) {
            return result;
        }
        Map<String, Object> pathParams = commonPathReq.getPathParams();
        if (CollUtil.isNotEmpty(pathParams)) {
            for (Map.Entry<String, Object> param : pathParams.entrySet()) {
                if (param.getValue() != null) {
                    String replaceKey = "\\{" + param.getKey() + "}";
                    result = result.replaceAll(replaceKey, param.getValue().toString());
                }
            }
        }
        return result;
    }
}

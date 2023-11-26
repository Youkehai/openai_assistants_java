package com.kh.openai.framework.assistant.core.constant;

import cn.hutool.core.collection.CollUtil;
import com.kh.openai.framework.assistant.core.req.CommonPathReq;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;


@AllArgsConstructor
@Getter
public enum RequestUrlEnum {

    /*******************************Thread Start************************************/

    CREATE_THREAD("/threads", RequestEnum.POST,"创建线程"),
    RETRIEVE_THREAD("/threads/{threadId}", RequestEnum.GET,"获取线程详情"),
    DELETE_THREAD("/threads/{threadId}", RequestEnum.DELETE,"删除线程"),

    /*******************************Thread End************************************/

    /*******************************Message Start************************************/

    CREATE_MESSAGE("/threads/{threadId}/messages", RequestEnum.POST,"发送消息"),
    RETRIEVE_MESSAGE("/threads/{threadId}/messages/{messageId}", RequestEnum.GET,"获取消息详情"),
    LIST_MESSAGE("/threads/{threadId}/messages", RequestEnum.GET,"通过线程ID，获取所有聊天记录"),

    /*******************************Message End************************************/

    /*******************************RUNs Start************************************/

    CREATE_RUN("/threads/{threadId}/runs", RequestEnum.POST,"运行消息"),
    RETRIEVE_RUN("/threads/{threadId}/runs/{runId}", RequestEnum.GET,"获取指定run的运行情况"),
    LIST_RUN("/threads/{threadId}/runs", RequestEnum.GET,"获取所有run"),
    SUBMIT_TOOL_OUTPUTS_TO_RUN("/threads/{threadId}/runs/{runId}/submit_tool_outputs", RequestEnum.POST,"提交返回值给指定的run"),
    CANCEL_RUN("/threads/{threadId}/runs/{runId}/cancel", RequestEnum.POST,"取消指定run的运行"),
    CREATE_THREAD_AND_RUN("/threads/runs", RequestEnum.POST,"创建线程，并发送消息和run"),

    /*******************************RUNs End************************************/

    /*******************************Assistant Start************************************/
    CREATE_ASSISTANTS("/assistants",RequestEnum.POST,"创建 assistants"),
    RETRIEVE_ASSISTANT("/assistants/{assistantId}", RequestEnum.GET,"获取 assistants 详情")

    /*******************************Assistant End************************************/
    ;

    private final String url;
    private final RequestEnum method;
    private final String desc;

    public String getUrl(String baseurl, CommonPathReq commonPathReq){
        Map<String, Object> pathParams = commonPathReq.getPathParams();
        String result = baseurl + this.url;
        if(CollUtil.isNotEmpty(pathParams)){
            for (Map.Entry<String, Object> param : pathParams.entrySet()) {
                if(param.getValue()!=null){
                    String replaceKey = "\\{"+param.getKey()+"}";
                    result = result.replaceAll(replaceKey,param.getValue().toString());
                }
            }
        }
        return result;
    }
}

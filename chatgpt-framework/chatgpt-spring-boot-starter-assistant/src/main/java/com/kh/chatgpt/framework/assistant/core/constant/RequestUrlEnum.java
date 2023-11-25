package com.kh.chatgpt.framework.assistant.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestUrlEnum {

    /*******************************Thread Start************************************/

    CREATE_THREAD("/threads", RequestEnum.POST,"创建线程"),
    RETRIEVE_THREAD("/threads/%s", RequestEnum.GET,"获取线程详情"),
    DELETE_THREAD("/threads/%s", RequestEnum.DELETE,"删除线程"),

    /*******************************Thread End************************************/

    /*******************************Message Start************************************/

    CREATE_MESSAGE("/threads/%s/messages", RequestEnum.POST,"发送消息"),
    RETRIEVE_MESSAGE("/threads/%s/messages/%s", RequestEnum.GET,"获取消息详情"),
    LIST_MESSAGE("/threads/%s/messages", RequestEnum.GET,"通过线程ID，获取所有聊天记录"),

    /*******************************Message End************************************/

    /*******************************RUNs Start************************************/

    CREATE_RUN("/threads/%s/runs", RequestEnum.POST,"运行消息"),
    RETRIEVE_RUN("/threads/%s/runs/%s", RequestEnum.GET,"获取指定run的运行情况"),
    LIST_RUN("/threads/%s/runs", RequestEnum.GET,"获取所有run"),
    SUBMIT_TOOL_OUTPUTS_TO_RUN("/threads/%s/runs/%s/submit_tool_outputs", RequestEnum.POST,"提交返回值给指定的run"),
    CANCEL_RUN("/threads/%s/runs/%s/cancel", RequestEnum.POST,"取消指定run的运行"),
    CREATE_THREAD_AND_RUN("/threads/runs", RequestEnum.POST,"创建线程，并发送消息和run"),

    /*******************************RUNs End************************************/

    /*******************************Assistant Start************************************/

    RETRIEVE_ASSISTANT("/assistants/%s", RequestEnum.GET,"获取 assistants 详情")

    /*******************************Assistant End************************************/
    ;

    private final String url;
    private final RequestEnum method;
    private final String desc;

    public String getUrl(String baseurl,String... args){
        if(args.length>0){
            return baseurl+String.format(this.url, args);
        }
        return baseurl+this.url;
    }
}

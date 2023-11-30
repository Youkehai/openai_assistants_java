package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.BaseReq;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.resp.BasePageResp;
import io.github.youkehai.assistant.core.resp.message.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息
 */
@Slf4j
public class AssistantsMessageApiService extends BaseService {


    /**
     * 获取线程消息列表
     *
     * @param pathReq   线程 ID
     * @param pageReqVO 分页参数
     * @return 消息列表
     */
    public BasePageResp<Message> getMessageList(CommonPathReq pathReq, PageReq pageReqVO) {
        log.debug("获取线程的消息列表[message-list_message]，路径参数:{},请求参数:{}", pathReq, pageReqVO);
        String request = super.requestByPage(RequestUrlEnum.LIST_MESSAGE, pageReqVO, pathReq);
        log.debug("获取线程的消息列表[message-list_message]，openai返回值：{}", request);
        return super.parsePageData(request, Message.class);
    }

    /**
     * 获取线程消息列表
     *
     * @param pathReq          路径参数
     * @param createMessageReq 消息，文件 id 内容
     * @return 消息列表
     */
    public Message createMessage(CommonPathReq pathReq, BaseReq createMessageReq) {
        log.debug("创建消息[message-list_message]，路径参数:{},请求参数:{}", pathReq, createMessageReq);
        String request = super.request(RequestUrlEnum.CREATE_MESSAGE, createMessageReq, pathReq);
        log.debug("创建消息[message-list_message]，openai返回值：{}", request);
        return parse(request, Message.class);
    }
}

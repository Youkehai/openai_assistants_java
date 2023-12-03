package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.BaseReq;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.resp.BasePageResp;
import io.github.youkehai.assistant.core.resp.message.Message;
import io.github.youkehai.assistant.core.resp.message.MessageFile;

import java.util.Map;

/**
 * 消息
 */
public class AssistantsMessageApiService extends BaseService {


    /**
     * 获取线程消息列表
     *
     * @param pathReq   线程 ID
     * @param pageReqVO 分页参数
     * @return 消息列表
     */
    public BasePageResp<Message> getMessageList(CommonPathReq pathReq, PageReq pageReqVO) {
        return super.parsePageData(super.requestByPage(RequestUrlEnum.LIST_MESSAGE, pageReqVO, pathReq),
                Message.class);
    }

    /**
     * 获取线程消息列表
     *
     * @param threadId         线程ID
     * @param createMessageReq 消息，文件 id 内容
     * @return 消息列表
     */
    public Message createMessage(String threadId, BaseReq createMessageReq) {
        return parse(super.request(RequestUrlEnum.CREATE_MESSAGE, createMessageReq, CommonPathReq.newByThreadId(threadId)),
                Message.class);
    }

    /**
     * 修改线程消息
     *
     * @param threadId  线程 ID
     * @param messageId 消息 ID
     * @param metadata  源数据
     * @return 消息列表
     */
    public Message modifyMessage(String threadId, String messageId, Map<String, String> metadata) {
        return parse(super.request(RequestUrlEnum.MODIFY_MESSAGE, new BaseReq(metadata), CommonPathReq.newByThreadId(threadId).setMessageId(messageId)),
                Message.class);
    }


    /**
     * 检索线程中，某一个消息的指定文件信息
     *
     * @param threadId  线程 ID
     * @param messageId 消息 ID
     * @param fileId    文件 ID
     * @return 文件信息
     */
    public MessageFile retrieveMessageFile(String threadId, String messageId, String fileId) {
        return parse(super.request(RequestUrlEnum.RETRIEVE_MESSAGE_FILE, CommonPathReq.newByThreadId(threadId)
                        .setMessageId(messageId).setFileId(fileId)),
                MessageFile.class);
    }

    /**
     * 获取指定消息用到的文件
     *
     * @param threadId  聊天线程 ID
     * @param messageId 消息 ID
     * @param pageReq   分页参数
     * @return 文件 list
     */
    public BasePageResp<MessageFile> messageFileList(String threadId, String messageId, PageReq pageReq) {
        CommonPathReq commonPathReq = new CommonPathReq().setMessageId(messageId)
                .setThreadId(threadId);
        return super.parsePageData(super.requestByPage(RequestUrlEnum.LIST_MESSAGE_FILE, pageReq, commonPathReq),
                MessageFile.class);
    }
}

package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.message.CreateMessageReq;
import io.github.youkehai.assistant.core.req.thread.UpdateThreadReq;
import io.github.youkehai.assistant.core.resp.thread.Thread;

import java.util.Map;

/**
 * 线程请求类
 */
public class AssistantsThreadApiService extends BaseService {

    /**
     * 获取线程信息
     *
     * @param threadId 线程ID
     * @return 线程信息
     */
    public Thread retrieveThread(String threadId) {
        return super.parse(super.request(RequestUrlEnum.RETRIEVE_THREAD, CommonPathReq.newByThreadId(threadId)),
                Thread.class);
    }

    /**
     * 创建线程，并发送消息
     *
     * @param message 消息内容，可以传一个空对象
     * @return 线程信息
     */
    public Thread createThread(CreateMessageReq message) {
        return parse(super.request(RequestUrlEnum.CREATE_THREAD, message),
                Thread.class);
    }

    /**
     * 删除线程
     *
     * @param threadId 线程ID
     * @return 被删除的线程 ID
     */
    public String deleteThreadId(String threadId) {
        super.request(RequestUrlEnum.DELETE_THREAD, CommonPathReq.newByThreadId(threadId));
        return threadId;
    }

    /**
     * 修改线程的 metadata 信息
     *
     * @param threadId 线程 ID
     * @param metadata metadata
     * @return 修改后的 thread 对象
     */
    public Thread modifyThread(String threadId, Map<String, String> metadata) {
        return super.parse(super.request(RequestUrlEnum.MODIFY_THREAD,
                new UpdateThreadReq().setMetadata(metadata),
                CommonPathReq.newByThreadId(threadId)), Thread.class);
    }
}

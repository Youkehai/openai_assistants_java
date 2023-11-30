package io.github.youkehai.assistant.core.service;

import cn.hutool.json.JSONUtil;
import io.github.youkehai.assistant.core.resp.thread.Thread;
import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.message.CreateMessageReq;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程请求类
 */
@Slf4j
public class AssistantsThreadApiService extends BaseService {

    /**
     * 获取线程信息
     *
     * @param pathReq 线程ID
     * @return 线程信息
     */
    public Thread retrieveThread(CommonPathReq pathReq) {
        log.info("获取线程详情[thread-retrieve_thread]，threadId:{}", pathReq);
        String request = super.request(RequestUrlEnum.RETRIEVE_THREAD, pathReq);
        log.info("获取线程详情[thread-retrieve_thread]，openai返回值：{}", request);
        return JSONUtil.toBean(request, Thread.class);
    }

    /**
     * 创建线程，并发送消息
     *
     * @param message 消息内容，可以传一个空对象
     * @return 线程信息
     */
    public Thread createThread(CreateMessageReq message) {
        log.info("创建线程[thread-create_thread]，请求参数:{}", message);
        String request = super.request(RequestUrlEnum.CREATE_THREAD, message);
        log.info("创建线程[thread-create_thread]，openai返回值：{}", request);
        return JSONUtil.toBean(request, Thread.class);
    }

    /**
     * 删除线程
     *
     * @param pathReq 线程ID
     * @return 被删除的线程 ID
     */
    public String deleteThreadId(CommonPathReq pathReq) {
        log.info("删除线程[thread-create_thread]，threadId:{}", pathReq);
        String request = super.request(RequestUrlEnum.DELETE_THREAD, pathReq);
        log.info("删除线程[thread-create_thread]，openai返回值：{}", request);
        return pathReq.getThreadId();
    }
}

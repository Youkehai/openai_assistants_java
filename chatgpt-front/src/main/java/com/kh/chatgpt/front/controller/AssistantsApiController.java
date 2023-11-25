package com.kh.chatgpt.front.controller;

import com.kh.chatgpt.framework.assistant.core.req.PageReq;
import com.kh.chatgpt.framework.assistant.core.resp.BasePageResp;
import com.kh.chatgpt.framework.assistant.core.resp.message.Message;
import com.kh.chatgpt.framework.assistant.core.service.AssistantsMessageApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * assistants api
 */
@AllArgsConstructor
@Tag(name = "assistantsAPI")
@RestController
@RequestMapping("/assistant")
@Slf4j
public class AssistantsApiController {

    @Resource
    private final AssistantsMessageApiService assistantsMessageApiService;


    @Operation(summary = "获取消息列表")
    @GetMapping("/message/{threadId}")
    public BasePageResp<Message> messageList(@PathVariable("threadId") String threadId, PageReq pageReqVO) {
        return assistantsMessageApiService.getMessageList(threadId, pageReqVO);
    }

}

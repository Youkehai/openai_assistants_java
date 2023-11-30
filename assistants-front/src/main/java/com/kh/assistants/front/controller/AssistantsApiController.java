package com.kh.assistants.front.controller;

import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.req.file.UploadFileReq;
import io.github.youkehai.assistant.core.req.message.CreateAndRunReq;
import io.github.youkehai.assistant.core.req.message.CreateMessageReq;
import io.github.youkehai.assistant.core.req.run.CreateRunReq;
import io.github.youkehai.assistant.core.resp.BasePageResp;
import io.github.youkehai.assistant.core.resp.assistants.Assistants;
import io.github.youkehai.assistant.core.resp.file.File;
import io.github.youkehai.assistant.core.resp.message.Message;
import io.github.youkehai.assistant.core.resp.run.Run;
import io.github.youkehai.assistant.core.resp.thread.Thread;
import io.github.youkehai.assistant.core.service.AssistantsMessageApiService;
import io.github.youkehai.assistant.core.service.AssistantsRunApiService;
import io.github.youkehai.assistant.core.service.AssistantsService;
import io.github.youkehai.assistant.core.service.AssistantsThreadApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * assistants api
 */
@AllArgsConstructor
@Tag(name = "assistantsAPI")
@RestController
@RequestMapping("/assistant")
@Slf4j
public class AssistantsApiController {

    private final AssistantsMessageApiService assistantsMessageApiService;

    private final AssistantsRunApiService assistantsRunApiService;

    private final AssistantsService assistantsService;

    private final AssistantsThreadApiService assistantsThreadApiService;


    @Operation(summary = "获取消息列表")
    @GetMapping("/message/{threadId}")
    public BasePageResp<Message> messageList(@PathVariable("threadId") String threadId, PageReq pageReqVO) {
        BasePageResp<Message> messageList = assistantsMessageApiService.getMessageList(CommonPathReq.newByThreadId(threadId), pageReqVO);
        for (Message datum : messageList.getData()) {
            log.info("测试：{}",datum);
        }
        return messageList;
    }

    @Operation(summary = "发送并运行消息")
    @PostMapping("/message/{threadId}")
    public Run messageList(@PathVariable("threadId") String threadId, CreateAndRunReq req) {
        assistantsMessageApiService.createMessage(CommonPathReq.newByThreadId(threadId), new CreateMessageReq().setContent(req.getContent()));
        return assistantsRunApiService.create(CommonPathReq.newByThreadId(threadId), new CreateRunReq().setAssistant_id(req.getAssistant_id()));
    }

    @Operation(summary = "获取线程信息")
    @GetMapping("/thread/{threadId}")
    public Thread messageList(@PathVariable("threadId") String threadId) {
        return assistantsThreadApiService.retrieveThread(CommonPathReq.newByThreadId(threadId));
    }

    @Operation(summary = "获取线程信息")
    @GetMapping("/assistants/{assistantsId}")
    public Assistants retrieveAssistants(@PathVariable("assistantsId") String assistantsId) {
        return assistantsService.retrieveAssistants(new CommonPathReq().setAssistantsId(assistantsId));
    }


    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public File messageList(MultipartFile file) {
        return assistantsService.uploadFile(new UploadFileReq().setFile(file));
    }
}

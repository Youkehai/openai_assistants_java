package com.kh.assistants.front.controller;

import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.req.assistants.CreateAssistantFileReq;
import io.github.youkehai.assistant.core.req.message.CreateAndRunReq;
import io.github.youkehai.assistant.core.req.message.CreateMessageReq;
import io.github.youkehai.assistant.core.req.run.CreateRunReq;
import io.github.youkehai.assistant.core.resp.BasePageResp;
import io.github.youkehai.assistant.core.resp.assistants.AssistantFile;
import io.github.youkehai.assistant.core.resp.assistants.Assistants;
import io.github.youkehai.assistant.core.resp.file.File;
import io.github.youkehai.assistant.core.resp.message.Message;
import io.github.youkehai.assistant.core.resp.run.Run;
import io.github.youkehai.assistant.core.resp.run.RunStep;
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
        BasePageResp<Message> messageList = assistantsMessageApiService.getMessageList(threadId, pageReqVO);
        for (Message datum : messageList.getData()) {
            log.info("测试：{}", datum);
        }
        return messageList;
    }

    @Operation(summary = "发送并运行消息")
    @PostMapping("/message/{threadId}")
    public Run messageList(@PathVariable("threadId") String threadId, CreateAndRunReq req) {
        assistantsMessageApiService.createMessage(threadId, new CreateMessageReq().setContent(req.getContent()));
        return assistantsRunApiService.createRun(threadId, new CreateRunReq().setAssistant_id(req.getAssistant_id()));
    }

    @Operation(summary = "获取线程信息")
    @GetMapping("/thread/{threadId}")
    public Thread messageList(@PathVariable("threadId") String threadId) {
        return assistantsThreadApiService.retrieveThread(threadId);
    }

    @Operation(summary = "获取线程信息")
    @GetMapping("/assistants/{assistantsId}")
    public Assistants retrieveAssistants(@PathVariable("assistantsId") String assistantsId) {
        return assistantsService.retrieveAssistants(assistantsId);
    }

    @Operation(summary = "单纯的只上传文件，给某次问答使用")
    @GetMapping("/upload")
    public File retrieveAssistants(MultipartFile file) {
        return assistantsService.upload(file);
    }

    @Operation(summary = "上传文件并绑定具体的 assistant")
    @PostMapping("/createAssistantFile/{assistantsId}")
    public AssistantFile CreateAssistantFileReq(@PathVariable("assistantsId") String assistantsId, MultipartFile file, String fileId) {
        //上传新文件并绑定
        return assistantsService.uploadFile(new CreateAssistantFileReq()
                .setAssistantId(assistantsId).setFile(file));
        //从已有文件中，选出一个 id 来绑定
//        assistantsService.uploadFile(new CreateAssistantFileReq()
//                .setAssistantId(assistantsId).setFileId(fileId));
    }

    @Operation(summary = "获取指定 assistant 的文件列表")
    @GetMapping("/assistantFileList/{assistantsId}")
    public BasePageResp<AssistantFile> CreateAssistantFileReq(@PathVariable("assistantsId") String assistantsId, PageReq pageReq) {
        return assistantsService.assistantsFileList(assistantsId, pageReq);
    }

    @Operation(summary = "获取指定 threadId 中run运行情况")
    @GetMapping("/runList/{threadId}")
    public BasePageResp<Run> getRunList(@PathVariable("threadId") String threadId,
                                                        PageReq pageReq) {
        return assistantsRunApiService.getRunList(threadId, pageReq);
    }

    @Operation(summary = "获取指定 threadId 中某一个 run 的运行步骤情况")
    @GetMapping("/runStep/{threadId}/{runId}")
    public BasePageResp<RunStep> getRunStepList(@PathVariable("threadId") String threadId,
                                                        @PathVariable("runId") String runId) {
        return assistantsRunApiService.listRunSteps(threadId, runId, new PageReq());
    }

    @Operation(summary = "获取指定 threadId 中某一个 run 的运行步骤情况")
    @GetMapping("/runStepDetail/{threadId}/{runId}/{stepId}")
    public RunStep getRunStepList(@PathVariable("threadId") String threadId,
                                                @PathVariable("runId") String runId,
                                                @PathVariable("stepId")String stepId) {
        return assistantsRunApiService.retrieveRunStep(threadId, runId,stepId);
    }
}

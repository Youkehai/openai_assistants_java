package io.github.youkehai.assistant.core.service;

import cn.hutool.json.JSONUtil;
import io.github.youkehai.assistant.core.constant.FilePurposeEnum;
import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.assistants.CreateAssistantReq;
import io.github.youkehai.assistant.core.req.file.UploadFileReq;
import io.github.youkehai.assistant.core.resp.assistants.Assistants;
import io.github.youkehai.assistant.core.resp.file.File;
import lombok.extern.slf4j.Slf4j;

/**
 * assistant 相关
 */
@Slf4j
public class AssistantsService extends BaseService {

    public Assistants createAssistant(CreateAssistantReq createAssistantReq) {
        log.info("创建助理[assistant-create_assistants],请求参数:{}", createAssistantReq);
        String request = super.request(RequestUrlEnum.CREATE_ASSISTANTS, createAssistantReq);
        log.info("创建助理[assistant-create_assistants]，openai返回值：{}", request);
        return JSONUtil.toBean(request, Assistants.class);
    }

    public File uploadFile(UploadFileReq req) {
        req.setPurpose(FilePurposeEnum.ASSISTANTS.getName());
        log.info("上传文件[assistant-upload_file],请求参数:{}", req);
        String request = super.upload(req);
        log.info("上传文件[assistant-upload_file]，openai返回值：{}", request);
        return JSONUtil.toBean(request, File.class);
    }

}

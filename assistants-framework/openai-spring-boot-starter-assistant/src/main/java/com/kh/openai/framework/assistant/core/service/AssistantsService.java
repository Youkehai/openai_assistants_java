package com.kh.openai.framework.assistant.core.service;

import cn.hutool.json.JSONUtil;
import com.kh.openai.framework.assistant.core.constant.FilePurposeEnum;
import com.kh.openai.framework.assistant.core.constant.RequestEnum;
import com.kh.openai.framework.assistant.core.constant.RequestUrlEnum;
import com.kh.openai.framework.assistant.core.req.assistants.CreateAssistantReq;
import com.kh.openai.framework.assistant.core.req.file.UploadFileReq;
import com.kh.openai.framework.assistant.core.resp.assistants.Assistants;
import com.kh.openai.framework.assistant.core.resp.file.File;
import lombok.extern.slf4j.Slf4j;

/**
 *  assistant 相关
 */
@Slf4j
public class AssistantsService extends BaseService{

    public Assistants createAssistant(CreateAssistantReq createAssistantReq){
        log.info("创建助理[assistant-create_assistants],请求参数:{}", createAssistantReq);
        String request = super.request(RequestUrlEnum.CREATE_ASSISTANTS, createAssistantReq);
        log.info("创建助理[assistant-create_assistants]，openai返回值：{}",request);
        return JSONUtil.toBean(request, Assistants.class);
    }

    public File uploadFile(UploadFileReq req){
        req.setPurpose(FilePurposeEnum.ASSISTANTS.getName());
        log.info("上传文件[assistant-upload_file],请求参数:{}", req);
        String request = super.upload(req);
        log.info("上传文件[assistant-upload_file]，openai返回值：{}",request);
        return JSONUtil.toBean(request, File.class);
    }

}

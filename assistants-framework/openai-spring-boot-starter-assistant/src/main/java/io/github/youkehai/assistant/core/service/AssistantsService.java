package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.FilePurposeEnum;
import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.CommonPathReq;
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

    /**
     * 创建一个AI助理
     * @param createAssistantReq 创建参数
     * @return 创建成功的对象
     */
    public Assistants createAssistant(CreateAssistantReq createAssistantReq) {
        log.debug("创建助理[assistant-create_assistants],请求参数:{}", createAssistantReq);
        String request = super.request(RequestUrlEnum.CREATE_ASSISTANTS, createAssistantReq);
        log.debug("创建助理[assistant-create_assistants]，openai返回值：{}", request);
        return parse(request, Assistants.class);
    }

    /**
     * 上传文件到 openai
     *
     * @param req 上传请求参数
     * @return 文件对象，包含文件ID，名称等
     */
    public File uploadFile(UploadFileReq req) {
        req.setPurpose(FilePurposeEnum.ASSISTANTS.getName());
        log.debug("上传文件[assistant-upload_file],请求参数:{}", req);
        String request = super.upload(req);
        log.debug("上传文件[assistant-upload_file]，openai返回值：{}", request);
        return parse(request, File.class);
    }

    /**
     * 获取助理信息
     *
     * @param pathReq 线程ID
     * @return 线程信息
     */
    public Assistants retrieveAssistants(CommonPathReq pathReq) {
        log.debug("获取助理详情[assistants-retrieve_assistants]，路径参数:{}", pathReq);
        String request = super.request(RequestUrlEnum.RETRIEVE_ASSISTANT, pathReq);
        log.debug("获取助理详情[assistants-retrieve_assistants]，openai返回值：{}", request);
        return parse(request, Assistants.class);
    }

}

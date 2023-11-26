package com.kh.openai.framework.assistant.core.service;

import cn.hutool.json.JSONUtil;
import com.kh.openai.framework.assistant.core.constant.RequestUrlEnum;
import com.kh.openai.framework.assistant.core.req.assistants.CreateAssistantReq;
import com.kh.openai.framework.assistant.core.resp.assistants.Assistants;
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

}

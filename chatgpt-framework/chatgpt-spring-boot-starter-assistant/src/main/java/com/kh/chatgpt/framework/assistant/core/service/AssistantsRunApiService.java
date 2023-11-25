package com.kh.chatgpt.framework.assistant.core.service;

import cn.hutool.json.JSONUtil;
import com.kh.chatgpt.framework.assistant.core.constant.RequestUrlEnum;
import com.kh.chatgpt.framework.assistant.core.req.PageReq;
import com.kh.chatgpt.framework.assistant.core.req.run.CreateRunReq;
import com.kh.chatgpt.framework.assistant.core.req.run.SubmitOutputReq;
import com.kh.chatgpt.framework.assistant.core.resp.BasePageResp;
import com.kh.chatgpt.framework.assistant.core.resp.run.Run;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 运行任务
 */
@Slf4j
public class AssistantsRunApiService extends BaseService {

    /**
     * 获取线程消息列表
     * @param threadId 线程 ID
     * @param pageReqVO 分页参数
     * @return 消息列表
     */
    public BasePageResp<Run> getRunList(String threadId, PageReq pageReqVO) {
        log.info("获取运行任务列表[run-list_run]，threadId:{},请求参数:{}",threadId,pageReqVO);
        String request = super.requestByPage(RequestUrlEnum.LIST_RUN, pageReqVO, threadId);
        log.info("获取运行任务列表[run-list_run]，openai返回值：{}",request);
        return super.parsePageData(request, Run.class);
    }

    /**
     * 创建运行任务
     * @param threadId 线程ID
     * @param createRunReq 创建运行任务参数
     * @return 创建完成后的 run 任务信息
     */
    public Run createRun(String threadId, CreateRunReq createRunReq) {
        log.info("创建运行任务[run-create_run]，threadId:{},请求参数:{}",threadId, createRunReq);
        String request = super.request(RequestUrlEnum.CREATE_RUN, createRunReq, threadId);
        log.info("创建运行任务[run-create_run]，openai返回值：{}",request);
        return JSONUtil.toBean(request, Run.class);
    }

    /**
     * 提交运行中任务的返回值
     * @param threadId 线程 ID
     * @param runId 运行任务的 ID
     * @param submitOutputs 提交的数据
     * @return run 对象
     */
    public Run submitToolOutputsToRun(String threadId, String runId, List<SubmitOutputReq.Item> submitOutputs){
        SubmitOutputReq reqVo = new SubmitOutputReq();
        reqVo.setTool_outputs(submitOutputs);
        log.info("提交运行任务返回值[run-submit_tool_outputs_to_run]，threadId:{},请求参数:{}",threadId,reqVo);
        String request = super.request(RequestUrlEnum.SUBMIT_TOOL_OUTPUTS_TO_RUN,reqVo,threadId,runId);
        log.info("提交运行任务返回值[run-submit_tool_outputs_to_run]，openai返回值：{}",request);
        return JSONUtil.toBean(request, Run.class);
    }

    /**
     * 创建线程-发送消息-运行任务
     * @param createRunReq 对应参数
     * @return 任务的信息
     */
    public Run createAndRun(CreateRunReq createRunReq){
        log.info("提交运行任务返回值[run-submit_tool_outputs_to_run]，请求参数:{}", createRunReq);
        String request = super.request(RequestUrlEnum.CREATE_THREAD_AND_RUN, createRunReq);
        log.info("提交运行任务返回值[run-submit_tool_outputs_to_run]，openai返回值：{}",request);
        return JSONUtil.toBean(request, Run.class);
    }
}

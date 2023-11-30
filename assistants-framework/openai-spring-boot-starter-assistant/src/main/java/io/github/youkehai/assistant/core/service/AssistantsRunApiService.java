package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.req.run.CreateRunReq;
import io.github.youkehai.assistant.core.req.run.SubmitOutputReq;
import io.github.youkehai.assistant.core.resp.BasePageResp;
import io.github.youkehai.assistant.core.resp.run.Run;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 运行任务
 */
@Slf4j
public class AssistantsRunApiService extends BaseService {

    /**
     * 获取线程消息列表
     *
     * @param pathReq   线程 ID
     * @param pageReqVO 分页参数
     * @return 消息列表
     */
    public BasePageResp<Run> getRunList(CommonPathReq pathReq, PageReq pageReqVO) {
        log.debug("获取运行任务列表[run-list_run]，路径参数:{},请求参数:{}", pathReq, pageReqVO);
        String request = super.requestByPage(RequestUrlEnum.LIST_RUN, pageReqVO, pathReq);
        log.debug("获取运行任务列表[run-list_run]，openai返回值：{}", request);
        return super.parsePageData(request, Run.class);
    }

    /**
     * 创建运行任务
     *
     * @param pathReq      线程ID
     * @param createRunReq 创建运行任务参数
     * @return 创建完成后的 run 任务信息
     */
    public Run create(CommonPathReq pathReq, CreateRunReq createRunReq) {
        log.debug("创建运行任务[run-create_run]，路径参数:{},请求参数:{}", pathReq, createRunReq);
        String request = super.request(RequestUrlEnum.CREATE_RUN, createRunReq, pathReq);
        log.debug("创建运行任务[run-create_run]，openai返回值：{}", request);
        return parse(request, Run.class);
    }

    /**
     * 提交运行中任务的返回值
     *
     * @param pathReq       线程 ID，运行任务的 ID
     * @param submitOutputs 提交的数据
     * @return run 对象
     */
    public Run submitToolOutputsToRun(CommonPathReq pathReq, List<SubmitOutputReq.Item> submitOutputs) {
        SubmitOutputReq reqVo = new SubmitOutputReq();
        reqVo.setTool_outputs(submitOutputs);
        log.debug("提交运行任务返回值[run-submit_tool_outputs_to_run]，路径参数:{},请求参数:{}", pathReq, reqVo);
        String request = super.request(RequestUrlEnum.SUBMIT_TOOL_OUTPUTS_TO_RUN, reqVo, pathReq);
        log.debug("提交运行任务返回值[run-submit_tool_outputs_to_run]，openai返回值：{}", request);
        return parse(request, Run.class);
    }

    /**
     * 创建线程-发送消息-运行任务
     *
     * @param createRunReq 对应参数
     * @return 任务的信息
     */
    public Run createAndRun(CreateRunReq createRunReq) {
        log.debug("提交运行任务返回值[run-submit_tool_outputs_to_run]，请求参数:{}", createRunReq);
        String request = super.request(RequestUrlEnum.CREATE_THREAD_AND_RUN, createRunReq);
        log.debug("提交运行任务返回值[run-submit_tool_outputs_to_run]，openai返回值：{}", request);
        return parse(request, Run.class);
    }
}

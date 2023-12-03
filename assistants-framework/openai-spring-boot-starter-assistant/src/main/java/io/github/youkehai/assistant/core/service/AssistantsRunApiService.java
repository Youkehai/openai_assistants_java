package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.BaseReq;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.req.run.CreateRunReq;
import io.github.youkehai.assistant.core.req.run.SubmitOutputReq;
import io.github.youkehai.assistant.core.resp.BasePageResp;
import io.github.youkehai.assistant.core.resp.run.Run;
import io.github.youkehai.assistant.core.resp.run.RunStep;

import java.util.List;
import java.util.Map;

/**
 * 运行任务
 */
public class AssistantsRunApiService extends BaseService {

    /**
     * 获取线程消息列表
     *
     * @param threadId  线程 ID
     * @param pageReqVO 分页参数
     * @return 消息列表
     */
    public BasePageResp<Run> getRunList(String threadId, PageReq pageReqVO) {
        return super.parsePageData(super.requestByPage(RequestUrlEnum.LIST_RUN, pageReqVO, CommonPathReq.newByThreadId(threadId)),
                Run.class);
    }

    /**
     * 创建运行任务
     *
     * @param threadId     线程ID
     * @param createRunReq 创建运行任务参数
     * @return 创建完成后的 run 任务信息
     */
    public Run createRun(String threadId, CreateRunReq createRunReq) {
        return super.parse(super.request(RequestUrlEnum.CREATE_RUN, createRunReq, CommonPathReq.newByThreadId(threadId)),
                Run.class);
    }

    /**
     * 检索 run 的具体信息
     *
     * @param threadId 线程 ID
     * @param runId    run 任务 ID
     * @return run 的详情信息
     */
    public Run retireveRun(String threadId, String runId) {
        return super.parse(super.request(RequestUrlEnum.RETRIEVE_RUN, CommonPathReq.newByThreadId(threadId)
                        .setRunId(runId)),
                Run.class);
    }

    /**
     * 取消正在 run 的任务
     *
     * @param threadId 线程 ID
     * @param runId    run 任务 ID
     * @return run 的详情信息
     */
    public Run cancelRun(String threadId, String runId) {
        return super.parse(super.request(RequestUrlEnum.CANCEL_RUN, CommonPathReq.newByThreadId(threadId)
                        .setRunId(runId)),
                Run.class);
    }

    /**
     * 修改线程消息
     *
     * @param threadId 线程 ID
     * @param runId    run 任务 ID
     * @param metadata 源数据
     * @return 消息列表
     */
    public Run modifyRun(String threadId, String runId, Map<String, String> metadata) {
        return parse(super.request(RequestUrlEnum.MODIFY_RUN, new BaseReq(metadata), CommonPathReq.newByThreadId(threadId)
                        .setRunId(runId)),
                Run.class);
    }


    /**
     * 提交运行中任务的返回值
     *
     * @param threadId      线程 ID
     * @param runId         运行任务的 ID
     * @param submitOutputs 提交的数据
     * @return run 对象
     */
    public Run submitToolOutputsToRun(String threadId, String runId, List<SubmitOutputReq.Item> submitOutputs) {
        SubmitOutputReq reqVo = new SubmitOutputReq();
        reqVo.setTool_outputs(submitOutputs);
        return super.parse(super.request(RequestUrlEnum.SUBMIT_TOOL_OUTPUTS_TO_RUN, reqVo,
                        CommonPathReq.newByThreadId(threadId).setRunId(runId)),
                Run.class);
    }

    /**
     * 创建线程-发送消息-运行任务
     *
     * @param createRunReq 对应参数
     * @return 任务的信息
     */
    public Run createAndRun(CreateRunReq createRunReq) {
        return super.parse(super.request(RequestUrlEnum.CREATE_THREAD_AND_RUN, createRunReq),
                Run.class);
    }

    /**
     * 获取 run 具体的运行情况，并获取某一个步骤的具体运行情况
     *
     * @param threadId 线程 ID
     * @param runId    运行任务 ID
     * @param stepId   步骤 ID
     * @return 具体步骤的详细运行情况
     */
    public RunStep retrieveRunStep(String threadId, String runId, String stepId) {
        return super.parse(super.request(RequestUrlEnum.RETRIEVE_RUN_STEP, CommonPathReq.newByThreadId(threadId)
                        .setRunId(runId).setStepId(stepId)),
                RunStep.class);
    }

    /**
     * 获取 run 所有的运行步骤列表
     *
     * @param threadId 线程 ID
     * @param runId    运行任务 ID
     * @return 具体步骤的详细运行情况
     */
    public BasePageResp<RunStep> listRunSteps(String threadId, String runId, PageReq pageReq) {
        return super.parsePageData(super.requestByPage(RequestUrlEnum.LIST_RUN_STEPS, pageReq,
                        CommonPathReq.newByThreadId(threadId).setRunId(runId)),
                RunStep.class);
    }
}

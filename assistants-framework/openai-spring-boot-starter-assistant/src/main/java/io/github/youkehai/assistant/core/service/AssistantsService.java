package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.req.assistants.BindFileReq;
import io.github.youkehai.assistant.core.req.assistants.CreateAssistantFileReq;
import io.github.youkehai.assistant.core.req.assistants.AssistantReq;
import io.github.youkehai.assistant.core.resp.BasePageResp;
import io.github.youkehai.assistant.core.resp.assistants.AssistantFile;
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
     *
     * @param assistantReq 创建参数
     * @return 创建成功的对象
     */
    public Assistants createAssistant(AssistantReq assistantReq) {
        return super.parse(super.request(RequestUrlEnum.CREATE_ASSISTANTS, assistantReq),
                Assistants.class);
    }

    /**
     * 编辑指定的 assistant
     *
     * @param assistantId  本次修改的 assistantId
     * @param assistantReq 修改参数
     * @return 修改后的对象
     */
    public Assistants modifyAssistant(String assistantId, AssistantReq assistantReq) {
        return super.parse(super.request(RequestUrlEnum.MODIFY_ASSISTANT, assistantReq, new CommonPathReq().setAssistantsId(assistantId)),
                Assistants.class);
    }

    /**
     * 删除指定的 assistant
     *
     * @param assistantId 被删除的 assistantId
     * @return 被删除的 assistantId
     */
    public String deleteAssistant(String assistantId) {
        super.request(RequestUrlEnum.DELETE_ASSISTANT, new CommonPathReq().setAssistantsId(assistantId));
        return assistantId;
    }

    /**
     * 获取所有 assistants 集合，分页
     *
     * @param pageReq 分页参数
     * @return assistants 列表
     */
    public BasePageResp<Assistants> getAssistantList(PageReq pageReq) {
        return super.parsePageData(super.requestByPage(RequestUrlEnum.LIST_ASSISTANTS, pageReq, CommonPathReq.empty()),
                Assistants.class);
    }


    /**
     * 上传文件到 openai 中的 assistant 中,并关联具体的 assistant
     * 如果未传递 file，则必须传递 fileId
     *
     * @param req 上传请求参数，其中 file 和 fileId 只能二选一传递，其中 file 的优先级 大于 fileId
     *            如果传了 file,则不会用 fileId 再去继续绑定
     * @return 文件对象，包含文件ID，名称等
     */
    public AssistantFile uploadFile(CreateAssistantFileReq req) {
        log.debug("上传文件并绑定具体的assistant[assistant-create_assistant_file],请求参数:{}", req);
        //1.传了文件，则先上传文件
        BindFileReq bindFileReq = new BindFileReq().setFileId(req.getFileId());
        if (req.getFile() != null) {
            File file = upload(req.getFile());
            //1.1 获取到新上传的文件 ID
            bindFileReq.setFileId(file.getId());
        }
        //2. 将具体的 file 绑定至 assistant中
        String request = super.request(RequestUrlEnum.CREATE_ASSISTANT_FILE, bindFileReq, new CommonPathReq().setAssistantsId(req.getAssistantId()));
        log.debug("上传文件[assistant-create_assistant_file]，openai返回值：{}", request);
        return parse(request, AssistantFile.class);
    }

    /**
     * 获取助理信息
     *
     * @param assistantId 助理 Assistant ID
     * @return 线程信息
     */
    public Assistants retrieveAssistants(String assistantId) {
        return super.parse(super.request(RequestUrlEnum.RETRIEVE_ASSISTANT, new CommonPathReq().setAssistantsId(assistantId)),
                Assistants.class);
    }

    /**
     * 查询指定 assistant 的文件列表
     *
     * @param assistantsId 助理ID
     * @param pageReq      分页参数
     * @return A list of assistant file objects.
     */
    public BasePageResp<AssistantFile> assistantsFileList(String assistantsId, PageReq pageReq) {
        CommonPathReq commonPathReq = new CommonPathReq().setAssistantsId(assistantsId);
        return super.parsePageData(super.requestByPage(RequestUrlEnum.LIST_ASSISTANT_FILE, pageReq, commonPathReq),
                AssistantFile.class);
    }

    /**
     * 删除助理的指定文件
     *
     * @param assistantsId 助理 ID
     * @param fileId       文件 ID
     * @return 文件 ID
     */
    public String deleteAssistantFile(String assistantsId, String fileId) {
        CommonPathReq commonPathReq = new CommonPathReq().setAssistantsId(assistantsId).setFileId(fileId);
        super.request(RequestUrlEnum.DELETE_ASSISTANT_FILE, commonPathReq);
        return fileId;
    }

    /**
     * "获取 assistants file 详情
     *
     * @param assistantsId 助理 ID
     * @param fileId       文件 ID
     * @return 文件信息
     */
    public AssistantFile retrieveAssistantFile(String assistantsId, String fileId) {
        CommonPathReq commonPathReq = new CommonPathReq().setAssistantsId(assistantsId).setFileId(fileId);
        return parse(super.request(RequestUrlEnum.RETRIEVE_ASSISTANT_FILE, commonPathReq),
                AssistantFile.class);
    }
}

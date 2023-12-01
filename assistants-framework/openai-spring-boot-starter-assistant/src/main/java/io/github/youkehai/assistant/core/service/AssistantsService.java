package io.github.youkehai.assistant.core.service;

import io.github.youkehai.assistant.core.constant.RequestUrlEnum;
import io.github.youkehai.assistant.core.req.CommonPathReq;
import io.github.youkehai.assistant.core.req.PageReq;
import io.github.youkehai.assistant.core.req.assistants.BindFileReq;
import io.github.youkehai.assistant.core.req.assistants.CreateAssistantFileReq;
import io.github.youkehai.assistant.core.req.assistants.CreateAssistantReq;
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
     * 上传文件到 openai 中的 assistant 中,并关联具体的 assistant
     *  如果未传递 file，则必须传递 fileId
     * @param req 上传请求参数，其中 file 和 fileId 只能二选一传递，其中 file 的优先级 大于 fileId
     *            如果传了 file,则不会用 fileId 再去继续绑定
     * @return 文件对象，包含文件ID，名称等
     */
    public AssistantFile uploadFile(CreateAssistantFileReq req) {
        log.debug("上传文件并绑定具体的assistant[assistant-create_assistant_file],请求参数:{}", req);
        //1.传了文件，则先上传文件
        BindFileReq bindFileReq = new BindFileReq().setFileId(req.getFileId());
        if(req.getFile()!=null){
            File file = upload(req.getFile());
            //1.1 获取到新上传的文件 ID
            bindFileReq.setFileId(file.getId());
        }
        //2. 将具体的 file 绑定至 assistant中
        String request = super.request(RequestUrlEnum.CREATE_ASSISTANT_FILE,bindFileReq,new CommonPathReq().setAssistantsId(req.getAssistantId()));
        log.debug("上传文件[assistant-create_assistant_file]，openai返回值：{}", request);
        return parse(request, AssistantFile.class);
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

    /**
     * 查询指定 assistant 的文件列表
     * @param assistantsId 助理ID
     * @param pageReq 分页参数
     * @return A list of assistant file objects.
     */
    public BasePageResp<AssistantFile> assistantsFileList(String assistantsId, PageReq pageReq){
        CommonPathReq commonPathReq = new CommonPathReq().setAssistantsId(assistantsId);
        log.debug("获取助理文件列表[assistant-file_list]，路径参数:{},请求参数:{}", commonPathReq, pageReq);
        String request = super.requestByPage(RequestUrlEnum.LIST_ASSISTANT_FILE, pageReq,commonPathReq);
        log.debug("获取助理文件列表[assistant-file_list]，openai返回值：{}", request);
        return super.parsePageData(request, AssistantFile.class);
    }

    /**
     * 删除助理的指定文件
     * @param assistantsId 助理 ID
     * @param fileId 文件 ID
     * @return 文件 ID
     */
    public String deleteAssistantFile(String assistantsId,String fileId){
        CommonPathReq commonPathReq = new CommonPathReq().setAssistantsId(assistantsId).setFileId(fileId);
        log.debug("删除助理的指定文件[assistant-file_list]，路径参数:{}", commonPathReq);
        String request = super.request(RequestUrlEnum.DELETE_ASSISTANT_FILE,commonPathReq);
        log.debug("获取运行任务列表[assistant-file_list]，openai返回值：{}", request);
        return fileId;
    }

    /**
     * "获取 assistants file 详情
     * @param assistantsId 助理 ID
     * @param fileId 文件 ID
     * @return 文件信息
     */
    public AssistantFile retrieveAssistantFile(String assistantsId,String fileId){
        CommonPathReq commonPathReq = new CommonPathReq().setAssistantsId(assistantsId).setFileId(fileId);
        log.debug("删除助理的指定文件[assistant-file_list]，路径参数:{}", commonPathReq);
        String request = super.request(RequestUrlEnum.RETRIEVE_ASSISTANT_FILE,commonPathReq);
        log.debug("获取运行任务列表[assistant-file_list]，openai返回值：{}", request);
        return parse(request,AssistantFile.class);
    }
}

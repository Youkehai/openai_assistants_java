package io.github.youkehai.assistant.core.req;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 通用路径参数
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommonPathReq {
    private final static CommonPathReq emptyObj = new CommonPathReq();

    /**
     * 单独用一个这个方法，是因为该方法很常用
     * 创建一个带线程 ID 的路径参数对象
     *
     * @param threadId 线程ID
     * @return 路径参数对象
     */
    public static CommonPathReq newByThreadId(String threadId) {
        return new CommonPathReq().setThreadId(threadId);
    }

    /**
     * 线程 ID
     */
    private String threadId;

    /**
     * 助理 ID
     */
    private String assistantsId;

    /**
     * 运行任务 ID
     */
    private String runId;

    /**
     * 文件 ID
     */
    private String fileId;

    /**
     * 步骤 ID
     */
    private String stepId;

    /**
     * 消息 ID
     */
    private String messageId;

    /**
     * 获取路径参数，只获取不为空的
     *
     * @return
     */
    public Map<String, Object> getPathParams() {
        return BeanUtil.beanToMap(this);
    }

    public static CommonPathReq empty() {
        return emptyObj;
    }
}

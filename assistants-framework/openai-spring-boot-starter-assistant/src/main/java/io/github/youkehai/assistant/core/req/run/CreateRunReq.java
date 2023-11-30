package io.github.youkehai.assistant.core.req.run;

import io.github.youkehai.assistant.core.resp.run.Tool;
import io.github.youkehai.assistant.core.req.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CreateRunReq extends BaseReq {

    /**
     * 助理 ID
     */
    private String assistant_id;

    /**
     * 运行的模型
     */
    private String model;

    /**
     * 本次运行，使用该 instructions 重写并覆盖掉原始设定的 instructions
     */
    private String instructions;

    /**
     * 本次运行，想执行的工具
     */
    private List<Tool> tools;

    /**
     * 当需要使用 创建一个新线程，发完消息之后，立即 run 时使用
     * 相当于将 创建线程-发送消息-开启运行 变为一个动作
     */
    private ThreadReq threadReq;
}

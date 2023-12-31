package io.github.youkehai.assistant.core.req.thread;

import io.github.youkehai.assistant.core.req.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 修改线程信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UpdateThreadReq extends BaseReq {

}

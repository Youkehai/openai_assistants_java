package io.github.youkehai.assistant.core.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 基础请求参数基类
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseReq {

    private final static BaseReq emptyObj = new BaseReq();

    /**
     * 源数据
     * 一组可以附加到对象的16个键值对。
     * 这对于以结构化格式存储关于对象的附加信息非常有用。
     * 键的长度最多为64个字符，值的长度最多可为512个字符。
     */
    private Map<String, String> metadata;

    public static BaseReq empty() {
        return emptyObj;
    }
}

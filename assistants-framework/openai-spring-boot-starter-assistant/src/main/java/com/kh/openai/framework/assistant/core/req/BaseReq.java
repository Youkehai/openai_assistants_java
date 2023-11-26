package com.kh.openai.framework.assistant.core.req;

import lombok.Data;

import java.util.Map;

/**
 * 基础请求参数基类
 */
@Data
public class BaseReq {

    private final static BaseReq emptyObj = new BaseReq();

    /**
     * 源数据
     */
    private Map<String,String> metadata;

    public static BaseReq empty(){
        return emptyObj;
    }
}

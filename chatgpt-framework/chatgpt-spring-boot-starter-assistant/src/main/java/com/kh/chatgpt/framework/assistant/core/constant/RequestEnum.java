package com.kh.chatgpt.framework.assistant.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestEnum {

    PUT("put"),
    DELETE("delete"),
    POST("post"),
    GET("get");

    private final String method;
}

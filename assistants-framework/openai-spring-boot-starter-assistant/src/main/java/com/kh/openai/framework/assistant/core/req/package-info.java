package com.kh.openai.framework.assistant.core.req;

/**
 * 该包为请求参数包
 * 可以看到里面的属性命名，没有遵循驼峰命名
 * 原因1：如果使用 jackson 的 JsonProperty 注解来转换，会导致不必要的开销
 * 原因2：如果使用了 JsonProperty 来转换，前端要传递的参数照样是转换后的值
 * 所以为了方便，入参直接与 openai 官网文档保持一直
 */
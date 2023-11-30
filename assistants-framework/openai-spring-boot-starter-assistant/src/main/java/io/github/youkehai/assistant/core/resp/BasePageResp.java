package io.github.youkehai.assistant.core.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BasePageResp<T> {

    /**
     * 类型，基本就是 list
     */
    private String object;

    /**
     * 分页数据
     */
    private List<T> data;

    /**
     * 当前页第一个ID
     */
    @JsonProperty("first_id")
    private String firstId;

    /**
     * 当前页最后一个ID
     */
    @JsonProperty("last_id")
    private String lastId;

    /**
     * 是否还有更多数据
     */
    @JsonProperty("has_more")
    private boolean hasMore;
}

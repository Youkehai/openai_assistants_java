package io.github.youkehai.assistant.core.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReq extends BaseReq {

    /**
     * 查询条数
     */
    private Integer limit = 50;

    /**
     * 排序
     * 不传默认 desc
     * 按 createAt排序
     */
    private String order = "desc";

    /**
     * 分页使用，表示只查询 after 指定的 ID 之后的数据
     */
    private String after;

    /**
     * 分页使用，表示只查询 before 指定的 ID 之前的数据
     */
    private String before;
}

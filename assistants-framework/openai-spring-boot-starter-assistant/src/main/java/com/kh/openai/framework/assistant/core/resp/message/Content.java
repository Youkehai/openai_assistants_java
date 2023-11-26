package com.kh.openai.framework.assistant.core.resp.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Content {

    /**
     * 类型 images or text
     */
    private String type;

    /**
     * 内容
     */
    private List<Text> text;

    /**
     * 内容
     */
    @Data
    public static class Text {

        private String value;

        private List<Annotation> annotations;
    }

    /**
     * images 类型时用到
     */
    @Data
    public static class Annotation {

        /**
         * Always file_citation.
         */
        private String type;

        /**
         * 消息内容中需要替换的文本
         */
        private String text;

        @JsonProperty("file_citation")
        private FileCitation fileCitation;

        @JsonProperty("file_path")
        private FileCitation filePath;

        @JsonProperty("start_index")
        private Integer startIndex;
        @JsonProperty("end_index")
        private Integer endIndex;

    }

    @Data
    public static class FileCitation {

        @JsonProperty("file_id")
        private String fileId;

        private String quote;
    }

}

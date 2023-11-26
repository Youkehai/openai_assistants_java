package com.kh.openai.framework.assistant.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.openai.framework.assistant.core.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@Slf4j
public class ChatGptAssistantAutoConfiguration {

    @Bean
    public AssistantsMessageApiService assistantsMessageApiService() {
        return new AssistantsMessageApiService();
    }

    @Bean
    public AssistantsThreadApiService assistantsThreadApiService() {
        return new AssistantsThreadApiService();
    }

    @Bean
    public AssistantsRunApiService assistantsRunApiService() {
        return new AssistantsRunApiService();
    }

    @Bean
    public AssistantsService assistantsService() {
        return new AssistantsService();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

}

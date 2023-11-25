package com.kh.chatgpt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@Slf4j
public class ChatGptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatGptApplication.class, args);
    }
}

package com.kh.openai.framework.assistant.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Random;

@Data
@AutoConfiguration
@ConfigurationProperties(prefix = "kh.openai")
public class OpenAIProperties implements InitializingBean {

    /**
     * 如果有多个 key 随机抽选
     */
    private List<String> apiKey;

    private String baseurl;

    /**
     * 请求代理 Ip
     */
    private String proxyHost;

    /**
     * 代理端口
     */
    private Integer proxyPort;

    /**
     * 是否需要请求代理
     * @return
     */
    public boolean hasProxy(){
        return StrUtil.isNotEmpty(proxyHost);
    }

    public void setApiKey(List<String> apiKey) {
        this.apiKey = apiKey;
    }

    public String randomApiKey() {
        if (apiKey == null || apiKey.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return apiKey.get(random.nextInt(apiKey.size()));
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if(StrUtil.isEmpty(this.baseurl)){
            throw new RuntimeException("必须设置 openai baseurl");
        }
        if(CollUtil.isEmpty(this.apiKey)){
            throw new RuntimeException("必须设置 openai apikey");
        }
    }
}

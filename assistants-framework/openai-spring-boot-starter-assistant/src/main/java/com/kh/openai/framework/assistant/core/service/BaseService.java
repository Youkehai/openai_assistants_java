package com.kh.openai.framework.assistant.core.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.openai.framework.assistant.config.OpenAIProperties;
import com.kh.openai.framework.assistant.core.constant.RequestUrlEnum;
import com.kh.openai.framework.assistant.core.req.BaseReq;
import com.kh.openai.framework.assistant.core.req.CommonPathReq;
import com.kh.openai.framework.assistant.core.req.PageReq;
import com.kh.openai.framework.assistant.core.resp.BasePageResp;
import com.kh.openai.framework.assistant.core.util.HttpUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;

import java.util.List;
import java.util.Map;


/**
 * 定义为抽象类，不能让它被实例化
 */
@Slf4j
public abstract class BaseService {

    /**
     * 在 autoConfiguration 中实例化子类时会注入
     */
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private OpenAIProperties properties;

    //请求头
    protected Map<String, String> headMap = MapUtil.newIdentityMap(3);
    //请求代理对象
    private HttpHost proxy = null;

    @PostConstruct
    public void init() {
        headMap.put("Content-Type", "application/json; charset=utf-8");
        headMap.put("OpenAI-Beta", "assistants=v1");
        if(properties.hasProxy()){
            proxy=new HttpHost(properties.getProxyHost(),properties.getProxyPort()==null?-1:properties.getProxyPort());
        }
    }

    private Map<String, String> getHeadMap() {
        //为了达到 key 负载问题，每次请求从多个 key 中随机一个
        headMap.put("Authorization", "Bearer " + properties.randomApiKey());
        return headMap;
    }

    /**
     * 统一请求方法
     * 封装请求头，格式化 url
     *
     * @param requestUrlEnum {@link RequestUrlEnum},请求地址枚举，包含请求方法
     * @param reqVO          请求体中的请求参数
     * @param commonPathReq  路径参数
     * @return openai返回的结果，子类处理为需要的类型
     */
    protected String request(RequestUrlEnum requestUrlEnum, BaseReq reqVO, CommonPathReq commonPathReq) {
        String url = requestUrlEnum.getUrl(properties.getBaseurl(), commonPathReq);
        try {
            String valueAsString = objectMapper.writeValueAsString(reqVO);
            return request(url, requestUrlEnum, valueAsString);
        } catch (JsonProcessingException e) {
            log.error("jackson 序列化异常:{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected String request(String url, RequestUrlEnum requestUrlEnum, String reqVO) {
        return HttpUtil.doJson(url, getHeadMap(), reqVO,
                requestUrlEnum.getMethod().getMethod(),proxy);
    }

    /**
     * 统一请求方法的重载
     */
    protected String request(RequestUrlEnum requestUrlEnum, CommonPathReq commonPathReq) {
        return request(requestUrlEnum, BaseReq.empty(), commonPathReq);
    }

    protected String request(RequestUrlEnum requestUrlEnum, BaseReq req) {
        return request(requestUrlEnum, req, CommonPathReq.empty());
    }

    /**
     * 分页请求
     *
     * @param requestUrlEnum {@link RequestUrlEnum},请求地址枚举，包含请求方法
     * @param pageReqVO      分页参数
     * @param commonPathReq  路径参数
     * @return 请求参数
     */
    protected String requestByPage(RequestUrlEnum requestUrlEnum, PageReq pageReqVO, CommonPathReq commonPathReq) {
        String url = requestUrlEnum.getUrl(properties.getBaseurl(), commonPathReq);
        url += "?limit=" + pageReqVO.getLimit() + "&order=" + pageReqVO.getOrder();
        if (StrUtil.isNotEmpty(pageReqVO.getAfter())) {
            url += "&after=" + pageReqVO.getAfter();
        }
        if (StrUtil.isNotEmpty(pageReqVO.getBefore())) {
            url += "&before=" + pageReqVO.getBefore();
        }
        return request(url, requestUrlEnum, "");
    }

    /**
     * 转换 page 返回值
     *
     * @param result      openai 返回值
     * @param elementType data 的 class 类型参数
     * @param <T>         data 的泛型
     * @return 转换好后的数据
     */
    public <T> BasePageResp<T> parsePageData(String result, Class<T> elementType) {
        JSONObject all = JSONUtil.parseObj(result);
        String data = all.getStr("data");
        //移除 data 单独转换
        all.remove("data");
        BasePageResp<T> bean = JSONUtil.toBean(all, BasePageResp.class);
        if (StrUtil.isNotEmpty(data)) {
            // 此处有坑， 需要在 JVM 启动参数加上 ： --add-opens java.base/java.util=ALL-UNNAMED
            List<T> dataList = JSONUtil.toList(data, elementType);
            bean.setData(dataList);
        }
        return bean;
    }

}

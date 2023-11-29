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
import com.kh.openai.framework.assistant.core.req.file.UploadFileReq;
import com.kh.openai.framework.assistant.core.resp.BasePageResp;
import com.kh.openai.framework.assistant.core.resp.ErrorResp;
import com.kh.openai.framework.assistant.core.util.HttpUtil;
import com.kh.openai.framework.assistant.exception.OpenaiException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


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
        String result = HttpUtil.doJson(url, getHeadMap(), reqVO,
                requestUrlEnum.getMethod().getMethod(), proxy);
        throwException(result);
        return result;
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
     * 上传
     * @param req 上传必要参数
     * @return file对象
     */
    protected String upload(UploadFileReq req) {
        String url = RequestUrlEnum.UPLOAD_FILE.getUrl(properties.getBaseurl(), null);
        MultipartFile file = req.getFile();
        if (file == null) {
            throw new RuntimeException("上传文件为空");
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(StandardCharsets.UTF_8);
        try {
            //为了兼容中文文件名，对文件名进行 url 编码，后续 file.getFileName 方法中进行了解码操作
            String encodedFileName = URLEncoder.encode(Objects.requireNonNull(file.getOriginalFilename()), StandardCharsets.UTF_8);
            builder.addPart("file", new InputStreamBody(file.getInputStream(), ContentType.create("application/octet-stream", StandardCharsets.UTF_8), encodedFileName));
            builder.addTextBody("purpose", req.getPurpose());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = HttpUtil.doPostByFormData(url, getHeadMap(), builder.build(), proxy);
        throwException(result);
        return result;
    }


    /**
     * 抛出 openai 返回的异常
     * @param result 结果
     */
    private void throwException(String result){
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if(jsonObject.containsKey("error") && jsonObject.getJSONObject("error")!=null){
            throw new OpenaiException(JSONUtil.toBean(jsonObject.getStr("error"), ErrorResp.class));
        }
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

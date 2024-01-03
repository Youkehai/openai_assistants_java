# Assistants API starter

## 1.版本要求

- jdk17+
- springboot 3.1.5 +

## 2.如何使用

### 2.1 直接引入starter

```xml
<dependency>
    <groupId>io.github.youkehai</groupId>
    <artifactId>openai-spring-boot-starter-assistant</artifactId>
    <version>1.0.1</version>
</dependency>
```

### 2.2 在项目中使用

**需要提前配置两个属性，不然无法启动**

```yaml
kh:
  openai:
    api-key:  #必须配置，不然无法启动
      - k1
      - k2
    baseurl: https://api.openai.com/v1 #必须配置，不然无法启动
    proxy-host: 127.0.0.1  #请求代理 ip，不配置则不会走代理
    proxy-port: 7890 #请求代理端口
```

**使用spi自动注入了四个service**

- AssistantsMessageApiService 发送消息，消息列表等
- AssistantsRunApiService 获取当前thread中运行任务，运行情况，发起运行等
- AssistantsThreadApiService 创建，删除，查询线程
- AssistantsService 创建，删除，查询助理信息

```
所有 service 中的方法，基本可以全部在官网中查询到对应的方法，所有 assistant api 对应的方法基本全部封装完成，
并兼容 错误处理，请求代理，apikey负载，转对象操作，提供更便利的调用方式，以及使用 demo的展示
```

**代码中使用**

```java
@Resource
private AssistantsMessageApiService assistantsMessageApiService;
```

## 3.代码结构

```
├─src
│  └─main
│      ├─java
│      │  └─com
│      │      └─kh
│      │          └─openai
│      │              └─framework
│      │                  └─assistant
│      │                      ├─config                 -- 自动注入，配置文件加载
│      │                      └─core
│      │                          ├─constant           -- 常量包，请求地址，状态值封装
│      │                          ├─req                -- 请求参数封装
│      │                          │  ├─assistants      -- assistant模块相关
│      │                          │  ├─file			  -- file模块相关
│      │                          │  ├─message         -- message模块
│      │                          │  ├─run			  -- run模块
│      │                          │  └─thread          -- thread模块
│      │                          ├─resp               -- 返回值模块封装
│      │                          │  ├─assistants
│      │                          │  ├─file
│      │                          │  ├─message
│      │                          │  ├─run
│      │                          │  └─thread
│      │                          ├─service             -- 具体操作的service封装
│      │                          ├─util                -- http请求util类等其他常用类
│      │                          └─exception           -- 异常类的封装，业务系统可捕获具体异常，自定义错误    
│      └─resources                                      -- 资源文件
│          └─META-INF
│              └─spring

```

## 4.后续计划

1.尽量跟进官网版本实时更新

##  
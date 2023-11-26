# Assistants API starter

## 1.版本要求

- jdk17+
- springboot 3.1.5 +



## 2.如何使用

### 2.1 直接引入starter

**注意：目前还未放入maven中央仓库，需要使用，请先下载源码引入使用**

```xml
<dependency>
    <groupId>com.kh.openai</groupId>
    <artifactId>chatgpt-spring-boot-starter-assistant</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2.2 在项目中使用

**需要提前配置两个属性，不然无法启动**

```yaml
kh:
  openai:
    api-key:
      - k1
      - k2
    baseurl: https://api.openai.com/v1
```

**使用spi自动注入了三个service**

- AssistantsMessageApiService   发送消息，消息列表等
- AssistantsRunApiService            获取当前thread中运行任务，运行情况，发起运行等
- AssistantsThreadApiService       创建，删除，查询线程

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
│      │          └─chatgpt
│      │              └─framework
│      │                  └─assistant
│      │                      ├─config                 -- 自动注入，配置文件加载
│      │                      └─core
│      │                          ├─constant           -- 常量包，请求地址，状态值封装
│      │                          ├─req                -- 请求参数封装
│      │                          │  ├─assistants      -- assistant模块相关(待完善)
│      │                          │  ├─file			  -- file模块相关(待完善)
│      │                          │  ├─message         -- message模块（基本完成）
│      │                          │  ├─run			  -- run模块（基本完成）
│      │                          │  └─thread          -- thread模块（基本完成）
│      │                          ├─resp               -- 返回值模块封装
│      │                          │  ├─assistants
│      │                          │  ├─file
│      │                          │  ├─message
│      │                          │  ├─run
│      │                          │  └─thread
│      │                          ├─service             -- 具体操作的service封装
│      │                          └─util                -- http请求util类等其他常用类
│      └─resources                                      -- 资源文件
│          └─META-INF
│              └─spring

```

## 4.后续计划

1.封装完所有api

2.加强错误模块和日志的处理

3.尽量跟进官网版本实时更新

## 
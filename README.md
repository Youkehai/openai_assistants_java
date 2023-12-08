# Assistants API starter

当前项目展示了如何使用starter中的各种service

版本说明，目录结构，配置说明，详见：

**[assistants-framework/openai-spring-boot-starter-assistant](https://github.com/Youkehai/openai_assistants_java/tree/master/assistants-framework/openai-spring-boot-starter-assistant)**

## 1.如何使用

```xml
 <dependency>
    <groupId>io.github.youkehai</groupId>
    <artifactId>openai-spring-boot-starter-assistant</artifactId>
    <version>1.0.1</version>
</dependency>
```

## 2.方法说明

### 2.1 核心 service

#### AssistantsMessageApiService  消息相关

##### getMessageList

获取线程消息列表

- 参数:

  `threadId` - 线程 ID

  `pageReq` - 分页参数

- 返回:

  消息列表

##### createMessage

发送消息

- 参数:

  `threadId` - 线程ID

  `createMessageReq` - 消息，文件 id 内容

- 返回:

  发送完之后的消息

##### modifyMessage

修改线程消息

- 参数:

  `threadId` - 线程 ID

  `messageId` - 消息 ID

  `metadata` - 源数据

- 返回:

  修改后的消息

##### retrieveMessageFile

检索线程中，某一个消息的指定文件信息

- 参数:

  `threadId` - 线程 ID

  `messageId` - 消息 ID

  `fileId` - 文件 ID

- 返回:

  文件信息

##### messageFileList

获取指定消息用到的文件

- 参数:

  `threadId` - 聊天线程 ID

  `messageId` - 消息 ID

  `pageReq` - 分页参数

- 返回:

  文件 list

#### AssistantsRunApiService  运行任务相关

##### getRunList

获取线程所有的 run 任务列表分页

- 参数:

  `threadId` - 线程 ID

  `pageReq` - 分页参数

- 返回:

  run 任务列表分页

##### createRun

创建运行任务

- 参数:

  `threadId` - 线程ID

  `createRunReq` - 创建运行任务参数

- 返回:

  创建完成后的 run 任务信息

##### retireveRun

检索 run 的具体信息

- 参数:

  `threadId` - 线程 ID

  `runId` - run 任务 ID

- 返回:

  run 的详情信息

##### cancelRun

取消正在 run 的任务

- 参数:

  `threadId` - 线程 ID

  `runId` - run 任务 ID

- 返回:

  run 的详情信息

##### modifyRun

修改线程消息

- 参数:

  `threadId` - 线程 ID

  `runId` - run 任务 ID

  `metadata` - 源数据

- 返回:

  修改后的 run 对象

##### submitToolOutputsToRun

提交运行中任务的返回值

- 参数:

  `threadId` - 线程 ID

  `runId` - 运行任务的 ID

  `submitOutputs` - 提交的数据

- 返回:

  run 对象

##### createAndRun

创建线程-发送消息-运行任务

- 参数:

  `createRunReq` - 对应参数

- 返回:

  任务的信息

##### retrieveRunStep

获取 run 具体的运行情况，并获取某一个步骤的具体运行情况

- 参数:

  `threadId` - 线程 ID

  `runId` - 运行任务 ID

  `stepId` - 步骤 ID

- 返回:

  具体步骤的详细运行情况

##### listRunSteps

获取 run 所有的运行步骤列表

- 参数:

  `threadId` - 线程 ID

  `runId` - 运行任务 ID

- 返回:

  具体步骤的详细运行情况

#### AssistantsService 助理对象相关

##### createAssistant

创建一个AI助理

- 参数:

  `assistantReq` - 创建参数

- 返回:

  创建成功的对象

##### modifyAssistant

编辑指定的 assistant

- 参数:

  `assistantId` - 本次修改的 assistantId

  `assistantReq` - 修改参数

- 返回:

  修改后的对象

##### deleteAssistant

删除指定的 assistant

- 参数:

  `assistantId` - 被删除的 assistantId

- 返回:

  被删除的 assistantId

##### getAssistantList

获取所有 assistants 集合，分页

- 参数:

  `pageReq` - 分页参数

- 返回:

  assistants 列表

##### uploadFile

上传文件到 openai 中的 assistant 中,并关联具体的 assistant 如果未传递 file，则必须传递 fileId

- 参数:

  `req` - 上传请求参数，其中 file 和 fileId 只能二选一传递，其中 file 的优先级 大于 fileId 如果传了 file,则不会用 fileId 再去继续绑定

- 返回:

  文件对象，包含文件ID，名称等

##### retrieveAssistants

获取助理信息

- 参数:

  `assistantId` - 助理 Assistant ID

- 返回:

  助理信息

##### assistantsFileList

查询指定 assistant 的文件列表

- 参数:

  `assistantsId` - 助理ID

  `pageReq` - 分页参数

- 返回:

  A list of assistant file objects.

##### deleteAssistantFile

删除助理的指定文件

- 参数:

  `assistantsId` - 助理 ID

  `fileId` - 文件 ID

- 返回:

  文件 ID

##### retrieveAssistantFile

"获取 assistants file 详情

- 参数:

  `assistantsId` - 助理 ID

  `fileId` - 文件 ID

- 返回:

  文件信息

#### AssistantsThreadApiService 线程相关

##### retrieveThread

获取线程信息

- 参数:

  `threadId` - 线程ID

- 返回:

  线程信息

##### createThread

创建线程，并发送消息

- 参数:

  `message` - 消息内容，可以传一个空对象

- 返回:

  线程信息

##### deleteThreadId

删除线程

- 参数:

  `threadId` - 线程ID

- 返回:

  被删除的线程 ID

##### modifyThread

修改线程的 metadata 信息

- 参数:

  `threadId` - 线程 ID

  `metadata` - metadata

- 返回:

  修改后的 thread 对象

### 2.2 异常捕获

当遇到 openai 返回错误时，将会抛出  io.github.youkehai.assistant.exception.OpenaiException  异常，如果使用 springboot-web框架，可使用以下方法统一捕获

```java
@ExceptionHandler(OpenaiException.class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public ErrorResp handleError(OpenaiException e) {
    return new ErrorResp().setCode(e.getCode()).setType(e.getType())
            .setMessage(e.getMessage()).setParam(e.getParam());
}
```




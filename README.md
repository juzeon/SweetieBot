# SweetieBot
基于SmartQQ协议，使用Java开发的QQ机器人。
## 使用方法
入口main方法位于`sb/Gate`

已编译文件为`target/eventprocessor.jar`，运行方法：

```
java -jar eventprocessor.jar
```

然后扫描生成的`qrcode.png`即可登录。
## 配置文件
`groups.txt`为启用机器人的群名称，一行一个**关键字**。如`淀粉`将会匹配群名称中包含*淀粉*二字的所有群。

`script`文件夹存放JavaScript脚本。
## 内置命令
命令应以`sb#`开头

`sb#close` 关闭机器人 （仅私聊）

`sb#short <网址>` 使用`url.cn`将给出的网址缩短。

`sb#baidu <搜索词>` 使用`lmbtfy.cn`生成页面并使用`url.cn`缩短。

`sb#emotion <语段>` 使用腾讯语义识别API分析语段的积极度与消极度。

`sb#say <文本>` 命令机器人发送给出的文本内容。

`sb#load script` 重载所有脚本。

`sb#load group` 重载`groups.txt`。

## 脚本编写
脚本应命名为`<文件名>.js`放置于`script`文件夹下。

脚本必须包含一个且只有一个`onMessage(obj)`函数，`obj`是程序传入脚本的对象。

**如果你发现调用示例脚本时QQ输出乱码，多半是编码问题导致的（因为写代码是在macOS上）。Windows用户请用Notepad++将脚本编码改为`ASCII`**

### obj对象
(int)`obj.type` 当前消息类别。1=私聊；2=群聊；3=讨论组。

(long)`obj.userId` 当前发消息用户的ID

(long)`obj.groupId` 当前发消息群的ID，无则为null

(long)`obj.discussId` 当前发消息讨论组的ID，无则为null

(String)`obj.content` 当前消息内容

(String)`obj.command` 当前命令内容。命令即以`sb#`开头的文本，如消息内容为`sb#test`则`obj.command`为`test`。无则为null

(String)`obj.commandKey` 当前命令头，无则为null

(String)`obj.commandArgument` 当前命令参数，无则为null。如消息内容为`sb#say hello`则`obj.commandKey`为`say`，`obj.commandArgument`为`hello`

(function)`obj.send(String content)` 通过此函数发送内容到接收消息的私聊/群/讨论组。

### 示例脚本
`script`文件夹下有一些示例脚本。

`example.js` 输入命令`sb#testscript`会发送`I'm working`

`ha.js` 膜蛤检测，检测到即发送`-1s`

`bracket.js` 自动闭合别人未闭合的括号，如遇到`蛤蛤（逃`会回复`）`



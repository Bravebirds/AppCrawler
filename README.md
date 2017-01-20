# AppCrawler
### 使用 gradle 编译
 ```
 gradlew shadowJar
 ```
会在 `build/libs` 目录下生成 `appcrawler2-all.jar` 文件 

一个基于自动遍历的app爬虫工具. 支持android和iOS, 支持真机和模拟器. 最大的特点是灵活性. 可通过配置来设定遍历的规则.

## 为什么做这个工具

* 各大云市场上自动遍历功能都多有限制企业无法自由定制.
* 解决monkey等工具可控性差的缺点
* 发现深层次的UI兼容性问题
* 通过新老版本的diff可以发现每个版本的UI变动范围

## 设计目标

* 自动爬取加上规则引导(完成)
* 支持定制化, 可以自己设定遍历深度(完成)
* 支持插件化, 允许别人改造和增强(完成)
* 支持滑动等更多动作(完成)
* 支持自动截获接口请求(完成)
* 支持新老版本的界面对比(Doing)
* 云端兼容性测试服务利用, 支持Testin MQC MTC(Doing)


## 安装依赖

### mac下安装appium

```bash
#安装node和依赖
brew install node
brew install ideviceinstaller
brew install libimobiledevice
#安装appium
npm install -g appium
#检查appium环境正确性
appium-doctor
```

真机或者模拟器均可. 确保adb devices可以看到就行

### 启动appium

使用此工具需要一定的appium基础知识, 请自行google.  
目前已经在appium 1.5.3下做过测试  

启动appium

```bash
appium --session-override
```

### 运行
工具以jar包方式发布，需要java8以上的运行环境
```bash
java -jar appcrawler.jar  
```

### 快速遍历

```bash
#查看帮助文档
java -jar appcrawler.jar
#运行测试
java -jar appcrawler.jar -a xueqiu.apk
```

### 配置文件运行方式

```bash
#配置文件的方式运行
#Android测试
java -jar AppCrawler.jar -c conf/monkey.yaml -a MrYu.apk
#iOS测试
java -jar AppCrawler.jar -c conf/monkey.yaml -a MrYu.app
```
### 输出结果
默认在当前目录下会生成一个包含输出结果的目录, 以时间命名. 包含了如下的测试结果
* 所有遍历过的控件组成的思维导图
* 包含了遍历覆盖的html报告
* 用于做diff分析的数据文件


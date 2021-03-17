# 青瓜电影院购票接口
青瓜电影院购票系统,基于Springboot框架

# 接口风格:
<br>RESTful风格: POST、GET、PUT、DELETE

# 全局设置:
这里没有采用yml文件来配置程序内部的一些属性,
在utils包下的Global类中可以配置定时器corn表达式、采集源以及钉钉消息机器人的WebHook等.

# 接口分类
### admin: 后台管理平台接口
### common: 公共接口
### desktop: 客户端接口

# swagger接口文档:

### step1. Run
<br>启动项目

### step2. View
<br>使用浏览器输入:域名( IP:port )/doc.html<br><br>
 E.g localhost:8088/doc.html

# 爬虫组件:
使用自动任务来定时采集豆瓣网最新电影数据.

# CI/CD:
使用jenkins完成CI/CD工作流: 项目将分支分为dev和master分支,
不使用test分支,开发完成后,将代码推到master分支上,jenkins完成自动部署.

# 钉钉消息机器人预警:
使用钉钉消息机器人发送电影数据采集任务启动提示以及采集结果报告,
后期电影的开播提醒也统一使用钉钉消息机器人发送.

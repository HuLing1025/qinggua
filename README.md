# 青瓜电影院购票接口
&emsp;&emsp;青瓜电影院购票系统后端接口,基于Springboot、MyBatis框架实现

# 接口风格:
&emsp;&emsp;RESTful风格: POST、GET、PUT、DELETE

# 全局设置:
&emsp;&emsp;这里没有采用yml文件来配置程序内部的一些属性,
在utils包下的Global类中可以配置定时器corn表达式、采集源以及钉钉消息机器人的WebHook等.

# 接口分类
### admin: 后台管理平台接口
### common: 公共接口
### desktop: 客户端接口

# swagger接口文档:

### step1. Run
&emsp;启动项目

### step2. View
使用浏览器输入:<br>
&emsp;域名( IP:port )/doc.html<br>
&emsp;E.g localhost:8088/doc.html

# 爬虫组件:
&emsp;&emsp;使用自动任务来定时采集豆瓣网最新电影数据,
utils包下的UserAgents中保存着一些真是的浏览器UserAgent,
目的是为了模拟真用户来采集电影数据,
同时在单次采集完成后线程休眠一段时间,防止IP遭到限制.

# 钉钉消息机器人预警:
&emsp;&emsp;使用钉钉消息机器人发送电影数据采集任务启动提示以及采集结果报告,
后期电影的开播提醒也统一使用钉钉消息机器人发送.

# CI/CD:
&emsp;&emsp;使用jenkins完成CI/CD工作流: 项目将分支分为dev和master分支,
不使用test分支,开发完成后,将代码推到master分支上,jenkins完成自动部署.

# 法律声明
&emsp;&emsp;本项目中的爬虫组件存在法律风险,只能用于学习交流,不能用于商业用途就行买卖盈利
若出现非法使用带来的法律问题,项目作者概不负责!<br>
&emsp;&emsp;项目中出现作者私有的数据库链接、WebHook和一些其他私有链接,
都将视为作者的私有财产,非法使用或者破坏他人私有数据将会承担法律责任!
--消息推送
极光推送插件
地址：http://jpush.cn/
登录名/密码：ecp888_wxms/wxms_push


IOS证书_dev_密码: tract45_begs
IOS证书_dev_path: /test/resources/remark/ytx_ios_push证书_dev.p12

https://github.com/jpush/jpush-api-java-client
http://docs.jpush.cn/display/dev/Index
https://github.com/jpush/jpush-api-java-client/releases
http://docs.jpush.cn/display/dev/Server-SDKs
http://docs.jpush.cn/display/dev/REST+API


iOS 推送基础知识
http://www.ibm.com/developerworks/cn/mobile/mo-ios-push/

http://bbs.csdn.net/topics/390501684
【转载】Android消息推送(一)--AndroidPn(XMPP协议)Demo版到正式上线

http://www.apkbus.com/android-131635-1-1.html
IOS 基于APNS消息推送原理与实现(JAVA后台)     
王新芬可以参考   

Android推送框架 androidpn
(Android Push Notification)是一个基于XMPP协议的java开源Android push notification实现。它包含了完整的客户端和服务器端。

Smack 结合 Openfire服务器，建立IM通信，发送聊天消息
http://www.cnblogs.com/hoojo/archive/2012/06/25/2561576.html

---------------------------------------------------------

memcached
http://www.fireflysource.com/  Firefly framework

尤其是后台是Hibernate的话，要解决性能问题，建议最好自己扩展实现或封装一下JSON-lib，
目前没有很好的可以拿来直接用的JSON序列化的框架。
#jackson2 spring mvc
#json-lib 

#Spring3+MyBatis3整合无法输出SQL语句问题的解决 
http://blog.csdn.net/zht666/article/details/9042373

http://www.cnblogs.com/crazy-fox/archive/2012/02/18/2357688.html

扩展Spring MVC 用以支持Controller对Ajax的处理
http://javaest.iteye.com/blog/187504

[spring 3.0] mvc 整合 restful 、maven实例 上载 
http://www.myexception.cn/vc-mfc/1016474.html

spring mvc3中JACKSON序列化日期格式的有关问题
http://www.myexception.cn/vc-mfc/445525.html

Spring应用Jackson过滤！JSON 
http://www.myexception.cn/javascript/411543.html

10套精美的免费网站后台管理系统模板下载
http://www.ithome.com/html/soft/28245.htm

ERROR [InjectionUtils.java] Parameter Class com.ecp888.wxms.vo.user.DeptVO has no constructor 
with single String parameter, static valueOf(String) or fromString(String) methods
http://www.codeweblog.com/apache-cxf-line-reading-http-cxf-apache-org-docs-jax-rs-html/

 使用Spring MVC统一异常处理实战
http://cgs1999.iteye.com/blog/1547197

http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program

#########################  
根据model直接生成，dao,service,daoImpl,sqlMap,以及单元测试类
https://github.com/txteam/tx-core/tree/1.0.x 
mybatis3.1.1主要是体现在插件，拦截器能做的，能够获取到的参数更多的方面 
http://p7engqingyang.iteye.com/blog/1746761
#########################

在Spring中使用Hessian Remoting技术 
http://blog.csdn.net/caihaijiang/article/details/5903253

#Spring 加载Mybaties映射文件
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory

#Mybatis之Oracle增删查改示例(含Date、Clob数据类型操作)
http://www.tuicool.com/articles/YjieM3

#MyBatis一对多双向关联
http://legend2011.blog.51cto.com/3018495/973579

#MyBatis的动态SQL
http://haohaoxuexi.iteye.com/blog/1338557

#
jquery.imgareaselect.pack.js


#Subclipse常规操作
图标说明：
1.灰色向右箭头:本地修改过 
2.蓝色向左箭头:SVN上修改过 
3.灰色向右且中间有个加号的箭头:本地比SVN上多出的文件 
4.蓝色向左且中间有个加号的箭头:SVN上比本地多出的文件 
5.灰色向右且中间有个减号的箭头:本地删除了,而SVN上未删除的文件 
6.蓝色向左且中间有个减号的箭头:SVN上删除了,而本地未删除的文件 
7.红色双向箭头:SVN上修改过,本地也修改过的文件
8.更新操作：更新操作是把代码库最新的取到本地，不是是上传，上传操作是提交。
9.添加文件或目录：如果将文件添加到项目中，它不会自动成为版本控制的一部分 —— 您需要明确将其添加到特定的存储库中。
    右击新文件，然后选择 Team > Add to Version Control。就是这样！下一次将此项目中的变更提交给存储库时，新文件也会检入。
10.删除文件或目录：如果添加到了与项目不在相关文件，您可以轻松地删除它。右击文件，然后选择 
   Delete（subclipse没有对应的删除菜单），插件会自动从本地svn配置文件上删除该文件。
     不需要使用 Team 菜单，Subclipse 自动将标记要删除的文件，并将它从项目中移除。下一次将变更提交给存储库时，该文件被删除了。
11.重命名文件或目录：要重命名 Subclipse 控制的文件或目录，请右击它，然后选择 Rename。在输入字段中键入项的新名称，
     按 Enter 键。你会发现它调用move命令修改文件名，但此时未还未正正修改配置库，需等特下次提交。
     注意，Subclipse 为 修改名后的“新” 文件添加的蓝色小加号而不是惊叹号。
12.查看整个项目的修改的历史记录：选中工程，右击|小组|显示资源历史记录。
13.退回修改文件或找回删除的文件：在历史记录中找到那条删除的日志，在日志上 右击|从修订版本XX回复更改。
	覆盖/更新：在Synchronize with Repository后，可对修改过的文件进行覆盖/更新操作，即用库上的代码覆盖本地代码。
14.在提交你的变化之前，你可能想看一下自从你的上次更新以来是否服务器上有任何文件被修改，
	想要对比本地和服务器上的源代码，请使用Team——Synchronize with Repository。这个命令让你看到有哪些内容已经被
	局部地修改，有哪些内容在服务器上修改，以及这两种修改之间的任何冲突。你还可以以可视化方式看到冲突的版本，
	并且在提交你的变化之前纠正任何比较突出的冲突。
15.忽略文件：如果项目生成文件，或者包括您不想检入的 Subversion 存储库文件，则可以通知 Subclipse 忽略它们。
	右击您要在版本控制中排除的文件或目录，然后选择 Team > Add to svn:ignore 即可显示Add to svn:ignore 对话框。
	提交更改：如果您对项目的变更感到满意，确定了您的代码可以编译，并且已经对变更进行测试，
	则应将它们提交给 Subversion 存储库。




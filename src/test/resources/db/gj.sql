1.公告管理，push管理，弹窗管理是新增模块,每一个模块都是独立的，
2.资讯管理就是原来的公告系统功能，只是分为广告位和新闻资讯，广告位就是公告。

HTML5公告标题
WAP公告标题
是否跳转
跳转链接地址
公告配图
公告内容
有效期
发布平台
渠道
目标用户
测试用户

lotteryId   
noticeId  
htmlUrl 
statusCode  A.等待,B.生效中,C.已过期,D.已撤消
title 
imgUrl  
openUrl  
contentUrl  公告内容url
time startDate endDate  
type(notic.classId)  -1.不显示立即参与,0.普通活动,1.充值活动,2.购彩活动,3.其他



跳转页面(notic.lotteryId) 
type=-1   无
type=0    500.注册页,501.反馈页,502.设置页
type=1    503.充值页
type=2    1.胜负彩,5.任选9场,11.双色球,12.福彩3D,13.大乐透,4.排列3,6.排列5,8.七星彩,31.老11选5(十一夺运金),
          34.11选5(多乐彩),30.广东11选5,32.江西时时彩,14.幸运赛车,15.时时彩(重庆),16.广东快乐10分,
          89.北单-让球胜平负,92.北单-比分,50.冠军竞猜,46.竞彩足球-胜平负,47.竞彩足球-比分,
          36.竞彩篮球-胜负,37.竞彩篮球-让分胜负,39">竞彩篮球-大小分
type=3    无


notic.iconUrl   插入图标
notic.htmlUrl   HTML5图标
notic.imgUrl    插入图片
活动平台 addZtc.安卓直通车,iosZtc.苹果直通车,addGj.安卓彩票管家,iosGj.苹果彩票管家,wap.wap,html5.客户端HTML5   
"addZtc": "202,2",
"iosZtc": "203,3",
"addGj": "204,d",
"iosGj": "205,e",
"wap": "201,1",
"html5": "210"             

  



{"statusCode": "0",
    "counts": "1",
    "noticeArray": [
        {
            "noticeId": "306",
            "title": "彩票直通车V4.3上线啦 ",
            "type": "-1",
            "lotteryId": "",
            "imgUrl": "/gjgonggao/images/notice/upload__22a501f3_147247f5515__8000_00000198.tmp.jpg",
            "contentUrl": "/gjgonggao/notic!showContent.shtml?id=306",
            "time": "2014-07-15 14:10",
            "htmlUrl": "",
            "startDate": "2014-07-15 00:00:00",
            "endDate": "2014-08-31 00:00:00",
            "openUrl": ""
        }]}
        
{
    "statusCode": "0",
    "title": "14086期大乐透专家预测：看好5尾开出1-2码",
    "imgUrl": "",
    "openUrl": "",
    "contentUrl": "/gjgonggao/notic!showContent.shtml?id=325",
    "lotteryId": "13",
    "time": "2014-07-24 17:12:57",
    "startDate": "2014-07-24 00:00:00",
    "endDate": "2014-07-25 00:00:00",
    "type": "2"
}






--列表
public static class Notic {
        String noticeId;
        String title;
        String contentUrl;
        String imgUrl;
        String openUrl;
        String htmlUrl;
        String startDate;
        String endDate;
        String lotteryId;         
        String type;               
        String time;
                
        
        

--明细
        String title;
        String contentUrl;
        String imgUrl;
        String openUrl;                 
        Date startDate;
        Date endDate;
        String lotteryId;
        String type;
        Date time;


--sys_notic  sys_notic_class
create table dbo.sys_notic (
   class_id             int                  null,
   title                varchar(255)         collate Chinese_PRC_CI_AS null,
   order_num            int                  null,
   img_url              varchar(255)         collate Chinese_PRC_CI_AS null,
   content              varchar(4000)        collate Chinese_PRC_CI_AS null,
   open_url             varchar(255)         collate Chinese_PRC_CI_AS null,
   channel              varchar(4000)        collate Chinese_PRC_CI_AS null,
   time                 datetime             null,
   act_status           int                  null,
   notic_id             int                  identity(1, 1),
   start_date           datetime             null,
   end_date             datetime             null,
   lottery_id           int                  null,
   platform             varchar(255)         collate Chinese_PRC_CI_AS null,
   icon_url             varchar(255)         collate Chinese_PRC_CI_AS null,
   html_url             varchar(255)         collate Chinese_PRC_CI_AS null,
   constraint PK__sys_activity__31190FD5 primary key (notic_id)
         on "PRIMARY"
);


create table dbo.sys_notic_class (
   class_id             int                  identity(1, 1),
   upid                 int                  null,
   name                 varchar(100)         collate Chinese_PRC_CI_AS null,
   explain              varchar(255)         collate Chinese_PRC_CI_AS null,
   link                 varchar(512)         collate Chinese_PRC_CI_AS null,
   constraint PK__sys_activity_cla__33F57C80 primary key (class_id)
         on "PRIMARY"
);



-- t_wx_bulletin
notic_id            int                  identity(1, 1),
title                varchar(255)         collate Chinese_PRC_CI_AS null,
content              varchar(4000)        collate Chinese_PRC_CI_AS null,
img_url              varchar(255)         collate Chinese_PRC_CI_AS null,
html_url             varchar(255)         collate Chinese_PRC_CI_AS null,
icon_url             varchar(255)         collate Chinese_PRC_CI_AS null,
open_url             varchar(255)         collate Chinese_PRC_CI_AS null,
start_date           datetime             null,
end_date             datetime             null,
order_num            int                  null,
act_status           int                  null,

class_id             int                  null,
lottery_id           int                  null,   
channel              varchar(4000)        collate Chinese_PRC_CI_AS null,   
platform             varchar(255)         collate Chinese_PRC_CI_AS null,

1.�������push������������������ģ��,ÿһ��ģ�鶼�Ƕ����ģ�
2.��Ѷ�������ԭ���Ĺ���ϵͳ���ܣ�ֻ�Ƿ�Ϊ���λ��������Ѷ�����λ���ǹ��档

HTML5�������
WAP�������
�Ƿ���ת
��ת���ӵ�ַ
������ͼ
��������
��Ч��
����ƽ̨
����
Ŀ���û�
�����û�

lotteryId   
noticeId  
htmlUrl 
statusCode  A.�ȴ�,B.��Ч��,C.�ѹ���,D.�ѳ���
title 
imgUrl  
openUrl  
contentUrl  ��������url
time startDate endDate  
type(notic.classId)  -1.����ʾ��������,0.��ͨ�,1.��ֵ�,2.���ʻ,3.����



��תҳ��(notic.lotteryId) 
type=-1   ��
type=0    500.ע��ҳ,501.����ҳ,502.����ҳ
type=1    503.��ֵҳ
type=2    1.ʤ����,5.��ѡ9��,11.˫ɫ��,12.����3D,13.����͸,4.����3,6.����5,8.���ǲ�,31.��11ѡ5(ʮһ���˽�),
          34.11ѡ5(���ֲ�),30.�㶫11ѡ5,32.����ʱʱ��,14.��������,15.ʱʱ��(����),16.�㶫����10��,
          89.����-����ʤƽ��,92.����-�ȷ�,50.�ھ�����,46.��������-ʤƽ��,47.��������-�ȷ�,
          36.��������-ʤ��,37.��������-�÷�ʤ��,39">��������-��С��
type=3    ��


notic.iconUrl   ����ͼ��
notic.htmlUrl   HTML5ͼ��
notic.imgUrl    ����ͼƬ
�ƽ̨ addZtc.��׿ֱͨ��,iosZtc.ƻ��ֱͨ��,addGj.��׿��Ʊ�ܼ�,iosGj.ƻ����Ʊ�ܼ�,wap.wap,html5.�ͻ���HTML5   
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
            "title": "��Ʊֱͨ��V4.3������ ",
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
    "title": "14086�ڴ���͸ר��Ԥ�⣺����5β����1-2��",
    "imgUrl": "",
    "openUrl": "",
    "contentUrl": "/gjgonggao/notic!showContent.shtml?id=325",
    "lotteryId": "13",
    "time": "2014-07-24 17:12:57",
    "startDate": "2014-07-24 00:00:00",
    "endDate": "2014-07-25 00:00:00",
    "type": "2"
}






--�б�
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
                
        
        

--��ϸ
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

--删除用户及权限
/*drop user wxms cascade;*/

--删除数据表空间
/*drop tablespace tbs_wxms_temp including contents and datafiles;
drop tablespace tbs_wxms including contents and datafiles;*/


--删除表
drop table T_APP_DICTIONARY_ITEM;
drop table T_APP_DICTIONARY;
drop table T_APP_USER_ROLE_PROGRAM;
drop table T_APP_PROGRAM_ITEM;
drop table T_APP_PROGRAM;
drop table T_APP_DIMENSION;
drop table T_APP_ROLE_RIGHT;
drop table T_APP_ROLE_CATALOG;
drop table T_APP_ATTRIBUTE;
drop table T_APP_ATTRIBUTE_GROUP_ROLE;
drop table T_APP_ATTRIBUTE_GROUP;
drop table T_APP_DEPT;
drop table t_app_attachment;
drop table t_app_log;
drop table T_APP_USER;
drop table T_APP_ROLE;
drop table T_APP_RIGHT;
drop table T_APP_CATALOG;

drop table t_wx_bulletin_platform;
drop table t_wx_bulletin_channel;
drop table t_wx_bulletin_target;
drop table t_wx_bulletin;
drop table t_wx_dialog_platform;
drop table t_wx_dialog_channel;
drop table t_wx_dialog_version;
drop table t_wx_dialog_target;
drop table t_wx_dialog;
drop table t_wx_notice_platform;
drop table t_wx_notice_channel;
drop table t_wx_notice_target;
drop table t_wx_notice;
drop table t_wx_advert_platform;
drop table t_wx_advert_channel;
drop table t_wx_advert_target;
drop table t_wx_advert;
drop table t_wx_push_statistic;
drop table t_wx_push_register;
drop table t_wx_push_version;
drop table t_wx_push_task;
drop table t_wx_push_platform;
drop table t_wx_push_channel;
drop table t_wx_push_target;
drop table t_wx_push;


--drop sequence
drop sequence seq_app_dictionary;
drop sequence seq_app_dictionary_item;
drop sequence seq_app_catalog;
drop sequence seq_app_user;
drop sequence seq_app_role;
drop sequence seq_app_right;
drop sequence seq_app_user_role_program;
drop sequence seq_app_role_right;
drop sequence seq_app_role_catalog;
drop sequence seq_app_attachment;
drop sequence seq_app_attachnumber;
drop sequence seq_app_log;
drop sequence seq_app_dept;

drop sequence seq_wx_bulletin_platform;
drop sequence seq_wx_bulletin_channel;
drop sequence seq_wx_bulletin_target;
drop sequence seq_wx_bulletin;
drop sequence seq_wx_dialog_platform;
drop sequence seq_wx_dialog_channel;
drop sequence seq_wx_dialog_version;
drop sequence seq_wx_dialog_target;
drop sequence seq_wx_dialog;
drop sequence seq_wx_notice_platform;
drop sequence seq_wx_notice_channel;
drop sequence seq_wx_notice_target;
drop sequence seq_wx_notice;
drop sequence seq_wx_advert_platform;
drop sequence seq_wx_advert_channel;
drop sequence seq_wx_advert_target;
drop sequence seq_wx_advert;
drop sequence seq_wx_push_statistic;
drop sequence seq_wx_push_register;
drop sequence seq_wx_push_version;
drop sequence seq_wx_push_task;
drop sequence seq_wx_push_platform;
drop sequence seq_wx_push_channel;
drop sequence seq_wx_push_target;
drop sequence seq_wx_push;





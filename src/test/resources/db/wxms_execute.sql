--创建数据表空间  
--create tablespace tbs_wxms  logging  datafile       'D:\ProgramFiles\oracle\product\10.2.0\oradata\orcl\tbs_wxms.dbf' size 124m  autoextend on  next 5m  maxsize unlimited extent management local;        
--create temporary tablespace tbs_wxms_temp  tempfile 'D:\ProgramFiles\oracle\product\10.2.0\oradata\orcl\tbs_wxms_temp.dbf' size 124m  autoextend on  next 5m  maxsize unlimited extent management local;

--创建用户并指定表空间
--create user wxms identified by wxms;
--alter user wxms default tablespace tbs_wxms;
--alter user wxms temporary tablespace tbs_wxms_temp;     
--给用户授予权限  dba    
--grant connect,resource to wxms;

--conn wxms/wxms@orcl as normal;


--------------------------------------Create table
-- Create table
create table T_APP_DICTIONARY
(
  DICTIONARY_ID     NUMBER not null,
  DICTIONARY_NAME   VARCHAR2(200),
  DESCRIPTION        VARCHAR2(1000),
  DICTIONARY_STATUS NUMBER,
  PARENT_ID NUMBER,
  CREATE_USER        NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE        DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table T_APP_DICTIONARY
  is '数据字典主表';
-- Add comments to the columns 
comment on column T_APP_DICTIONARY.DICTIONARY_NAME
  is '类别名称';
comment on column T_APP_DICTIONARY.DESCRIPTION
  is '描述';
 comment on column T_APP_DICTIONARY.PARENT_ID
  is '父ID';
comment on column T_APP_DICTIONARY.DICTIONARY_STATUS
  is '状态 1：启用，0禁用(数据字典编码)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_DICTIONARY
  add constraint PK_T_APP_DICTIONARY primary key (DICTIONARY_ID);


-- Create table
create table T_APP_DICTIONARY_ITEM
(
  ITEM_ID         NUMBER not null,
  ITEM_NAME       VARCHAR2(200),
  item_code      VARCHAR2(200),
  DICTIONARY_ID   NUMBER,
  DESCRIPTION     VARCHAR2(100),
  ITEM_STATUS     NUMBER,
  PARENT_ID     NUMBER,
  ORDER_BY      NUMBER,
  CREATE_USER       NUMBER,
  UPDATE_USER  NUMBER,
  CREATE_DATE       DATE,
  UPDATE_DATE  DATE
);
-- Add comments to the table 
comment on table T_APP_DICTIONARY_ITEM
  is '数据字典从表';
-- Add comments to the columns 
comment on column T_APP_DICTIONARY_ITEM.ITEM_NAME
  is '子项名称';
comment on column T_APP_DICTIONARY_ITEM.item_code
  is '子项编码';  
comment on column T_APP_DICTIONARY_ITEM.DICTIONARY_ID
  is '类别（t_app_dictionary.dictionary_id）';
comment on column T_APP_DICTIONARY_ITEM.DESCRIPTION
  is '描述';
comment on column T_APP_DICTIONARY_ITEM.ITEM_STATUS
  is '状态 1：启用，0禁用(数据字典编码)';
 comment on column T_APP_DICTIONARY.PARENT_ID
  is '父ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_DICTIONARY_ITEM
  add constraint PK_T_APP_DICTIONARY_ITEM primary key (ITEM_ID);
alter table T_APP_DICTIONARY_ITEM
  add constraint FK_T_APP_DICTIONARY_ITEM foreign key (DICTIONARY_ID)
  references T_APP_DICTIONARY (DICTIONARY_ID) on delete cascade;
  

-- Create table
create table T_APP_CATALOG
(
  CATALOG_ID          NUMBER not null,
  CATALOG_NAME        VARCHAR2(300),
  CATALOG_VALUE       VARCHAR2(300),
  CATALOG_URL         VARCHAR2(1024),
  PARENT_ID           NUMBER,
  CATALOG_FLAG        VARCHAR2(10),
  CATALOG_DESCRIPTION VARCHAR2(500),
  ORDER_NUM           number,
  CREATE_USER         NUMBER,
  UPDATE_USER         NUMBER,
  CREATE_DATE         DATE,
  UPDATE_DATE         DATE
);
-- Add comments to the table 
comment on table T_APP_CATALOG
  is '系统栏目表';
-- Add comments to the columns 
comment on column T_APP_CATALOG.CATALOG_ID
  is '序号';
comment on column T_APP_CATALOG.CATALOG_NAME
  is '栏目名称';
comment on column T_APP_CATALOG.CATALOG_VALUE
  is '栏目值';
comment on column T_APP_CATALOG.CATALOG_URL
  is '栏目URL';
comment on column T_APP_CATALOG.PARENT_ID
  is '父ID';
comment on column T_APP_CATALOG.CATALOG_FLAG
  is '栏目标识 1：有效，0：无效 状态 (数据字典编码)';
comment on column T_APP_CATALOG.CATALOG_DESCRIPTION
  is '栏目描述';
comment on column T_APP_CATALOG.ORDER_NUM
  is '排序号';  
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_CATALOG
  add constraint PK_T_APP_CATALOG primary key (CATALOG_ID);


-- Create table
create table T_APP_USER
(
  USER_ID          NUMBER not null,
  USER_NAME        VARCHAR2(200),
  USER_PASSWORD    VARCHAR2(200) default 1,
  USER_SEX         NUMBER,
  USER_EMAIL       VARCHAR2(200),
  USER_PHONE       VARCHAR2(50),
  USER_MOBILE      VARCHAR2(50),
  USER_FAX         VARCHAR2(50),
  USER_ADDRESS     VARCHAR2(300),
  USER_ZIP         VARCHAR2(100),
  USER_AGE         NUMBER,
  USER_BIRTHDAY    DATE,
  USER_STATUS      NUMBER,
  DEPT_ID          NUMBER,
  CREATE_USER      NUMBER,
  UPDATE_USER NUMBER,
  CREATE_DATE      DATE,
  UPDATE_DATE DATE
);
-- Add comments to the table 
comment on table T_APP_USER
  is '用户表';
-- Add comments to the columns 
comment on column T_APP_USER.USER_ID
  is '序列';
comment on column T_APP_USER.USER_NAME
  is '用户名';
comment on column T_APP_USER.USER_PASSWORD
  is '密码';
comment on column T_APP_USER.USER_SEX
  is '性别 1：男，0女(数据字典编码)';
comment on column T_APP_USER.USER_EMAIL
  is '邮箱';
comment on column T_APP_USER.USER_PHONE
  is '电话';
comment on column T_APP_USER.USER_MOBILE
  is '手机';
comment on column T_APP_USER.USER_FAX
  is '传真';
comment on column T_APP_USER.USER_ADDRESS
  is '地址';
comment on column T_APP_USER.USER_ZIP
  is '邮编';
comment on column T_APP_USER.USER_STATUS
  is '状态 1：启用，0禁用(数据字典编码)';
comment on column T_APP_USER.DEPT_ID
  is '部门（T_APP_DEPT.dept_id）';
comment on column T_APP_USER.USER_AGE
  is '年龄';
comment on column T_APP_USER.USER_BIRTHDAY
  is '出生日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_USER
  add constraint PK_T_APP_USER primary key (USER_ID);


-- Create table
create table T_APP_ROLE
(
  ROLE_ID          NUMBER not null,
  ROLE_NAME        VARCHAR2(100),
  ROLE_DESCRIPTION VARCHAR2(300),
  ROLE_STATUS      NUMBER,
  CREATE_USER        NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE        DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table T_APP_ROLE
  is '角色表';
-- Add comments to the columns 
comment on column T_APP_ROLE.ROLE_ID
  is '序列';
comment on column T_APP_ROLE.ROLE_NAME
  is '角色名称';
comment on column T_APP_ROLE.ROLE_DESCRIPTION
  is '描述';
comment on column T_APP_ROLE.ROLE_STATUS
  is '状态 1：启用，0禁用(数据字典编码)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_ROLE
  add constraint PK_T_APP_ROLE primary key (ROLE_ID);


-- Create table
create table T_APP_RIGHT
(
  RIGHT_ID         NUMBER not null,
  RIGHT_NAME       VARCHAR2(300),
  RIGHT_VALUE      VARCHAR2(300),
  RIGHT_ITEM_NAME  VARCHAR2(300),
  RIGHT_ITEM_VALUE VARCHAR2(300),
  RIGHT_TYPE       VARCHAR2(50) ,
  RIGHT_STATUS     NUMBER,
  CREATE_USER      NUMBER,
  UPDATE_USER NUMBER,
  CREATE_DATE      DATE,
  UPDATE_DATE DATE
);
-- Add comments to the table 
comment on table T_APP_RIGHT
  is '权限表';
-- Add comments to the columns 
comment on column T_APP_RIGHT.RIGHT_ID
  is '序列';
comment on column T_APP_RIGHT.RIGHT_NAME
  is '权限名称';
comment on column T_APP_RIGHT.RIGHT_TYPE
  is '权限类型';
comment on column T_APP_RIGHT.RIGHT_STATUS
  is '状态 1：启用，0禁用(数据字典编码)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_RIGHT
  add constraint PK_T_APP_RIGHT primary key (RIGHT_ID);


-- Create table
create table T_APP_PROGRAM
(
  PROGRAM_ID       NUMBER not null,
  PROGRAM_NAME     VARCHAR2(300),
  DESCRIPTION      VARCHAR2(500),
  STATUS           VARCHAR2(2),
  CREATE_USER      NUMBER,
  UPDATE_USER NUMBER,
  CREATE_DATE      DATE,
  UPDATE_DATE DATE
);
-- Add comments to the table 
comment on table T_APP_PROGRAM
  is '维度表';
-- Add comments to the columns 
comment on column T_APP_PROGRAM.PROGRAM_ID
  is '序列';
comment on column T_APP_PROGRAM.PROGRAM_NAME
  is '维度名称';
comment on column T_APP_PROGRAM.DESCRIPTION
  is '描述';
comment on column T_APP_PROGRAM.STATUS
  is '状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_PROGRAM
  add constraint PK_T_APP_PROGRAM primary key (PROGRAM_ID);


-- Create table
create table T_APP_PROGRAM_ITEM
(
  PROGRAM_ITEM_ID    NUMBER not null,
  PROGRAM_ITEM_NAME  VARCHAR2(200),
  PROGRAM_ITEM_VALUE VARCHAR2(200),
  PROGRAM_ID         NUMBER,
  CREATE_USER        NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE        DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table T_APP_PROGRAM_ITEM
  is '维度条目表';
-- Add comments to the columns 
comment on column T_APP_PROGRAM_ITEM.PROGRAM_ITEM_ID
  is '序列';
comment on column T_APP_PROGRAM_ITEM.PROGRAM_ITEM_NAME
  is '维度条目名称';
comment on column T_APP_PROGRAM_ITEM.PROGRAM_ITEM_VALUE
  is '维度条目值';
comment on column T_APP_PROGRAM_ITEM.PROGRAM_ID
  is 'fk T_APP_PROGRAM.PROGRAM_ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_PROGRAM_ITEM
  add constraint PK_T_APP_PROGRAM_ITEM primary key (PROGRAM_ITEM_ID);
alter table T_APP_PROGRAM_ITEM
  add constraint FK_T_APP_PROGRAM_ITEM foreign key (PROGRAM_ID)
  references T_APP_PROGRAM (PROGRAM_ID) on delete cascade;


-- Create table
create table T_APP_DIMENSION
(
  DIMENSION_ID    NUMBER not null,
  DIMENSION_NAME  VARCHAR2(500),
  DIMENSION_CODE  VARCHAR2(500),
  DIMENSION_FIELD VARCHAR2(500),
  DIMENSION_URL   VARCHAR2(500),
  DIMENSION_TYPE  VARCHAR2(10)
);
-- Add comments to the table 
comment on table T_APP_DIMENSION
  is '维度定义表';
-- Add comments to the columns 
comment on column T_APP_DIMENSION.DIMENSION_ID
  is '序列';
comment on column T_APP_DIMENSION.DIMENSION_NAME
  is '维度名称';
comment on column T_APP_DIMENSION.DIMENSION_CODE
  is '维度编码';
comment on column T_APP_DIMENSION.DIMENSION_FIELD
  is '维度字段';
comment on column T_APP_DIMENSION.DIMENSION_URL
  is '维度连接';
comment on column T_APP_DIMENSION.DIMENSION_TYPE
  is '维度类型';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_DIMENSION
  add constraint PK_APP_DIMENSION primary key (DIMENSION_ID);


-- Create table
create table T_APP_ROLE_RIGHT
(
  ROLE_RIGHT_ID NUMBER not null,
  ROLE_ID       NUMBER,
  RIGHT_ID      NUMBER,
  CREATE_USER        NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE        DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table T_APP_ROLE_RIGHT
  is '角色与权限关联表';
-- Add comments to the columns 
comment on column T_APP_ROLE_RIGHT.ROLE_RIGHT_ID
  is '序列';
comment on column T_APP_ROLE_RIGHT.ROLE_ID
  is 'FK T_APP_ROLE.ROLE_ID';
comment on column T_APP_ROLE_RIGHT.RIGHT_ID
  is 'FK T_APP_RIGHT.RIGHT_ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_ROLE_RIGHT
  add constraint PK_T_APP_ROLE_RIGHT primary key (ROLE_RIGHT_ID);
alter table T_APP_ROLE_RIGHT
  add constraint FK_T_APP_ROLE_RIGHT foreign key (ROLE_ID)
  references T_APP_ROLE (ROLE_ID) on delete cascade;
alter table T_APP_ROLE_RIGHT
  add constraint FK_T_APP_ROLE_RIGHT1 foreign key (RIGHT_ID)
  references T_APP_RIGHT (RIGHT_ID) on delete cascade;

-- Create table
create table T_APP_ROLE_CATALOG  
(
  ROLE_CATALOG_ID NUMBER not null,
  ROLE_ID         NUMBER,
  CATALOG_ID      NUMBER,
  CREATE_USER     NUMBER,
  UPDATE_USER     NUMBER,
  CREATE_DATE     DATE,
  UPDATE_DATE     DATE
);
-- Add comments to the table 
comment on table T_APP_ROLE_CATALOG
  is '角色与栏目菜单关联表';
-- Add comments to the columns 
comment on column T_APP_ROLE_CATALOG.ROLE_CATALOG_ID
  is '序列';
comment on column T_APP_ROLE_CATALOG.ROLE_ID
  is 'FK T_APP_ROLE.ROLE_ID';
comment on column T_APP_ROLE_CATALOG.CATALOG_ID
  is 'FK T_APP_CATALOG.CATALOG_ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_ROLE_CATALOG
  add constraint PK_T_APP_ROLE_CATALOG primary key (ROLE_CATALOG_ID);
alter table T_APP_ROLE_CATALOG
  add constraint FK_T_APP_ROLE_CATALOG foreign key (ROLE_ID)
  references T_APP_ROLE (ROLE_ID) on delete cascade;
alter table T_APP_ROLE_CATALOG
  add constraint FK_T_APP_ROLE_CATALOG1 foreign key (CATALOG_ID)
  references T_APP_CATALOG (CATALOG_ID) on delete cascade;

-- Create table
create table T_APP_USER_ROLE_PROGRAM
(
  USER_ROLE_PROGRAM_ID NUMBER not null,
  USER_ID              NUMBER,
  ROLE_ID              NUMBER,
  PROGRAM_ID           NUMBER,
  CREATE_USER        NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE        DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table T_APP_USER_ROLE_PROGRAM
  is '用户角色维度关联表';
-- Add comments to the columns 
comment on column T_APP_USER_ROLE_PROGRAM.USER_ROLE_PROGRAM_ID
  is '序列';
comment on column T_APP_USER_ROLE_PROGRAM.USER_ID
  is 'fk T_APP_USER.USER_ID';
comment on column T_APP_USER_ROLE_PROGRAM.ROLE_ID
  is 'fk T_APP_ROLE.ROLE_ID';
comment on column T_APP_USER_ROLE_PROGRAM.PROGRAM_ID
  is 'fk T_APP_PROGRAM.PROGRAM_ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_USER_ROLE_PROGRAM
  add constraint PK_T_APP_USER_ROLE_PROGRAM primary key (USER_ROLE_PROGRAM_ID);
alter table T_APP_USER_ROLE_PROGRAM
  add constraint FK_T_APP_USER_ROLE_PROGRAM foreign key (USER_ID)
  references T_APP_USER (USER_ID) on delete cascade;
alter table T_APP_USER_ROLE_PROGRAM
  add constraint FK_T_APP_USER_ROLE_PROGRAM1 foreign key (ROLE_ID)
  references T_APP_ROLE (ROLE_ID) on delete cascade;
alter table T_APP_USER_ROLE_PROGRAM
  add constraint FK_T_APP_USER_ROLE_PROGRAM2 foreign key (PROGRAM_ID)
  references T_APP_PROGRAM (PROGRAM_ID) on delete cascade;


-- Create table
create table T_APP_ATTRIBUTE_GROUP
(
  ATTRIBUTE_GROUP_ID    NUMBER not null,
  ATTRIBUTE_GROUP_NAME  VARCHAR2(200),
  ATTRIBUTE_GROUP_VALUE VARCHAR2(200),
  STATUS                VARCHAR2(2),
  CREATE_USER           NUMBER,
  UPDATE_USER      NUMBER,
  CREATE_DATE           DATE,
  UPDATE_DATE      DATE
);
-- Add comments to the table 
comment on table T_APP_ATTRIBUTE_GROUP
  is '数据权限属性组表';
-- Add comments to the columns 
comment on column T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_ID
  is '序列';
comment on column T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_NAME
  is '属性组名称';
comment on column T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_VALUE
  is '属性组值';
comment on column T_APP_ATTRIBUTE_GROUP.STATUS
  is '状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_ATTRIBUTE_GROUP
  add constraint PK_T_APP_ATTRIBUTE_GROUP primary key (ATTRIBUTE_GROUP_ID);


-- Create table
create table T_APP_ATTRIBUTE
(
  ATTRIBUTE_ID       NUMBER not null,
  ATTRIBUTE_NAME     VARCHAR2(200),
  ATTRIBUTE_VALUE    VARCHAR2(200),
  ATTRIBUTE_GROUP_ID NUMBER,
  CREATE_USER        NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE        DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table T_APP_ATTRIBUTE
  is '数据权限属性表';
-- Add comments to the columns 
comment on column T_APP_ATTRIBUTE.ATTRIBUTE_ID
  is '序列';
comment on column T_APP_ATTRIBUTE.ATTRIBUTE_NAME
  is '属性名称';
comment on column T_APP_ATTRIBUTE.ATTRIBUTE_VALUE
  is '属性值';
comment on column T_APP_ATTRIBUTE.ATTRIBUTE_GROUP_ID
  is 'fk T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_ATTRIBUTE
  add constraint PK_T_APP_ATTRIBUTE primary key (ATTRIBUTE_ID);
alter table T_APP_ATTRIBUTE
  add constraint FK_T_APP_ATTRIBUTE foreign key (ATTRIBUTE_GROUP_ID)
  references T_APP_ATTRIBUTE_GROUP (ATTRIBUTE_GROUP_ID) on delete cascade;


-- Create table
create table T_APP_ATTRIBUTE_GROUP_ROLE
(
  ATTRIBUTE_GROUP_ROLE_ID NUMBER not null,
  ATTRIBUTE_GROUP_ID      NUMBER,
  ROLE_ID                 NUMBER,
  CREATE_USER             NUMBER,
  UPDATE_USER        NUMBER,
  CREATE_DATE             DATE,
  UPDATE_DATE        DATE
);
-- Add comments to the table 
comment on table T_APP_ATTRIBUTE_GROUP_ROLE
  is '数据权限属性组与角色关联表';
-- Add comments to the columns 
comment on column T_APP_ATTRIBUTE_GROUP_ROLE.ATTRIBUTE_GROUP_ROLE_ID
  is '序列';
comment on column T_APP_ATTRIBUTE_GROUP_ROLE.ATTRIBUTE_GROUP_ID
  is 'fk T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_ID';
comment on column T_APP_ATTRIBUTE_GROUP_ROLE.ROLE_ID
  is 'fk T_APP_ROLE.ROLE_ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_ATTRIBUTE_GROUP_ROLE
  add constraint PK_T_APP_ATTRIBUTE_GROUP_ROLE primary key (ATTRIBUTE_GROUP_ROLE_ID);
alter table T_APP_ATTRIBUTE_GROUP_ROLE
  add constraint FK_T_APP_ATTRIBUTE_GROUP_ROLE foreign key (ATTRIBUTE_GROUP_ID)
  references T_APP_ATTRIBUTE_GROUP (ATTRIBUTE_GROUP_ID) on delete cascade;
alter table T_APP_ATTRIBUTE_GROUP_ROLE
  add constraint FK_T_APP_ATTRIBUTE_GROUP_ROLE1 foreign key (ROLE_ID)
  references T_APP_ROLE (ROLE_ID) on delete cascade;



-- Create table
create table T_APP_DEPT
(
  DEPT_ID     NUMBER not null,
  DEPT_NAME   VARCHAR2(200),
  DESCRIPTION VARCHAR2(4000),
  PARENT_ID   NUMBER,
  DEPT_STATUS NUMBER,
  CREATE_USER        NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE        DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table T_APP_DEPT
  is '部门表';
-- Add comments to the columns 
comment on column T_APP_DEPT.DEPT_NAME
  is '部门名称';
comment on column T_APP_DEPT.DESCRIPTION
  is '描述';
comment on column T_APP_DEPT.PARENT_ID
  is '父ID';  
comment on column T_APP_DEPT.DEPT_STATUS
  is '状态 1：启用，0禁用(数据字典编码)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_DEPT
  add constraint PK_T_APP_DEPT primary key (DEPT_ID);  

-- Create table
create table T_APP_ATTACHMENT
(
  attach_id      NUMBER not null,
  attach_name    VARCHAR2(300),
  attach_size    NUMBER,
  attach_path    VARCHAR2(800),
  attach_version VARCHAR2(100),
  attach_moudle  VARCHAR2(100),
  attach_key     NUMBER,
  create_user    NUMBER,
  update_user    NUMBER,
  create_date    DATE,
  update_date    DATE
);
-- Add comments to the table 
comment on table T_APP_ATTACHMENT
  is '附件表';         
-- Add comments to the columns 
comment on column T_APP_ATTACHMENT.attach_id
  is '序列';
comment on column T_APP_ATTACHMENT.attach_name
  is '附件名称';
comment on column T_APP_ATTACHMENT.attach_size
  is '附件大小';
comment on column T_APP_ATTACHMENT.attach_path
  is '附件路径';
comment on column T_APP_ATTACHMENT.attach_version
  is '附件版本';
comment on column T_APP_ATTACHMENT.attach_moudle
  is '附件所属模块';
comment on column T_APP_ATTACHMENT.attach_moudle
  is '附件外键';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_APP_ATTACHMENT
  add constraint PK_APP_ATTACHMENT primary key (ATTACH_ID);

-- create table
create table t_app_log
(
  log_id           number not null,
  operate          varchar2(2000),
  user_ip          varchar2(50),
  log_module       varchar2(50),       
  create_user      number,
  UPDATE_USER      number,
  create_date      timestamp(6),
  UPDATE_DATE timestamp(6)
);
-- add comments to the table 
comment on table t_app_log
  is '系统日志';
-- add comments to the columns 
comment on column t_app_log.operate
  is '操作内容';
comment on column t_app_log.user_ip
  is '操作人ip';
comment on column t_app_log.log_module
  is '模块';  
-- create/recreate primary, unique and foreign key constraints 
alter table t_app_log
  add constraint pk_app_log_id primary key (log_id);
  
  
-------------------------------业务表
create table t_wx_bulletin
(
  bulletin_id    number,
  title       varchar2(300),
  title_wap   varchar2(300),
  content     clob,
  img_url     varchar2(300),
  html_url    varchar2(300),
  open_url    varchar2(300),
  direct_flag varchar2(10),
  start_date  date,
  end_date    date,
  order_num   number,
  act_status  number,
  class_id    number,
  lottery_id  number,
  test_user varchar2(300),
  target_user varchar2(300),
  bulletin_status number,
  create_user NUMBER,
  update_user NUMBER,
  create_date DATE,
  update_date DATE
);
-- Add comments to the table 
comment on table t_wx_bulletin
  is 'APP公告信息表';
-- Add comments to the columns 
comment on column t_wx_bulletin.bulletin_id
  is '序列';
comment on column t_wx_bulletin.title
  is '标题';
comment on column t_wx_bulletin.title_wap
  is 'wap标题';  
comment on column t_wx_bulletin.content
  is '内容';
comment on column t_wx_bulletin.img_url
  is '图片链接';
comment on column t_wx_bulletin.html_url
  is 'H5图片链接';
comment on column t_wx_bulletin.open_url
  is '跳转链接地址,direct_flag=1时必填';
comment on column t_wx_bulletin.direct_flag
  is '是否跳转';
comment on column t_wx_bulletin.start_date
  is '有效开始日期';
comment on column t_wx_bulletin.end_date
  is '有效截止日期';
comment on column t_wx_bulletin.order_num
  is '排序号';
comment on column t_wx_bulletin.act_status
  is '状态,数据字典值';
comment on column t_wx_bulletin.class_id
  is '活动类型';
comment on column t_wx_bulletin.lottery_id
  is '跳转页面,采种ID';  
comment on column t_wx_bulletin.test_user
  is '测试用户';
comment on column t_wx_bulletin.target_user
  is '目标用户';  
comment on column t_wx_bulletin.bulletin_status
  is '状态(数据字典编码)';     
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_bulletin
  add constraint pk_wx_bulletin primary key (bulletin_id);

-- Create table
create table t_wx_bulletin_target
(
  target_id   number,
  target_user varchar2(300),
  bulletin_id    number
);
-- Add comments to the table 
comment on table t_wx_bulletin_target
  is 'APP公告目标用户表';
-- Add comments to the columns 
comment on column t_wx_bulletin_target.target_id
  is '序列';
comment on column t_wx_bulletin_target.target_user
  is '目标用户';
comment on column t_wx_bulletin_target.bulletin_id
  is 't_wx_bulletin.bulletin_id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_bulletin_target
  add constraint pk_wx_bulletin_target primary key (TARGET_ID);
alter table t_wx_bulletin_target
  add constraint fk_wx_bulletin_target foreign key (bulletin_id)
  references t_wx_bulletin (bulletin_id) on delete cascade;

-- create table
create table t_wx_bulletin_platform
(
  platform_id   number,
  platform_value varchar2(300),
  bulletin_id    number
);
-- add comments to the table 
comment on table t_wx_bulletin_platform
  is 'app公告平台';
-- add comments to the columns 
comment on column t_wx_bulletin_platform.platform_id
  is '序列';
comment on column t_wx_bulletin_platform.platform_value
  is '平台编码';  
comment on column t_wx_bulletin_platform.bulletin_id
  is 't_wx_bulletin.bulletin_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_bulletin_platform
  add constraint pk_wx_bulletin_platform primary key (platform_id);
alter table t_wx_bulletin_platform
  add constraint fk_wx_bulletin_platform foreign key (bulletin_id)
  references t_wx_bulletin (bulletin_id) on delete cascade;

-- create table
create table t_wx_bulletin_channel
(
  channel_id   number,
  channel_value varchar2(300),
  bulletin_id    number
);
-- add comments to the table 
comment on table t_wx_bulletin_channel
  is 'app公告渠道';
-- add comments to the columns 
comment on column t_wx_bulletin_channel.channel_id
  is '序列';
comment on column t_wx_bulletin_channel.channel_value
  is '渠道编码';  
comment on column t_wx_bulletin_channel.bulletin_id
  is 't_wx_bulletin.bulletin_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_bulletin_channel
  add constraint pk_wx_bulletin_channel primary key (channel_id);
alter table t_wx_bulletin_channel
  add constraint fk_wx_bulletin_channel foreign key (bulletin_id)
  references t_wx_bulletin (bulletin_id) on delete cascade;  
  

create table t_wx_dialog
(
  dialog_id    number,
  title       varchar2(300),
  content     clob,
  img_url     varchar2(300),
  html_url    varchar2(300),
  open_url    varchar2(300),
  direct_flag varchar2(10),
  start_date  date,
  end_date    date,
  order_num   number,
  act_status  number,
  class_id    number,
  lottery_id  number,
  test_user varchar2(300),
  target_user varchar2(300),
  dialog_status number,
  notice_id   number,
  create_user NUMBER,
  update_user NUMBER,
  create_date DATE,
  update_date DATE
);
-- Add comments to the table 
comment on table t_wx_dialog
  is 'APP弹窗信息表';
-- Add comments to the columns 
comment on column t_wx_dialog.dialog_id
  is '序列';
comment on column t_wx_dialog.title
  is '标题';
comment on column t_wx_dialog.content
  is '内容';
comment on column t_wx_dialog.img_url
  is '图片链接';
comment on column t_wx_dialog.html_url
  is 'H5图片链接';  
comment on column t_wx_dialog.open_url
  is '跳转链接地址,direct_flag=1时必填';
comment on column t_wx_dialog.direct_flag
  is '是否跳转';
comment on column t_wx_dialog.start_date
  is '有效开始日期';
comment on column t_wx_dialog.end_date
  is '有效截止日期';
comment on column t_wx_dialog.order_num
  is '排序号';
comment on column t_wx_dialog.act_status
  is '状态,数据字典值';
comment on column t_wx_dialog.class_id
  is '活动类型';
comment on column t_wx_dialog.lottery_id
  is '跳转页面,采种ID';  
comment on column t_wx_dialog.test_user
  is '测试用户';
comment on column t_wx_dialog.target_user
  is '目标用户';    
comment on column t_wx_dialog.dialog_status
  is '状态(数据字典编码)'; 
comment on column t_wx_dialog.notice_id
  is '广告位ID';   
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_dialog
  add constraint pk_wx_dialog primary key (dialog_id);

-- Create table
create table t_wx_dialog_target
(
  target_id   number,
  target_user varchar2(300),
  dialog_id    number
);
-- Add comments to the table 
comment on table t_wx_dialog_target
  is 'APP弹窗目标用户表';
-- Add comments to the columns 
comment on column t_wx_dialog_target.target_id
  is '序列';
comment on column t_wx_dialog_target.target_user
  is '目标用户';
comment on column t_wx_dialog_target.dialog_id
  is 't_wx_dialog.dialog_id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_dialog_target
  add constraint pk_wx_dialog_target primary key (target_id);
alter table t_wx_dialog_target
  add constraint fk_wx_dialog_target foreign key (dialog_id)
  references t_wx_dialog (dialog_id) on delete cascade;

-- create table
create table t_wx_dialog_platform
(
  platform_id   number,
  platform_value varchar2(300),
  dialog_id    number
);
-- add comments to the table 
comment on table t_wx_dialog_platform
  is 'app弹窗平台';
-- add comments to the columns 
comment on column t_wx_dialog_platform.platform_id
  is '序列';
comment on column t_wx_dialog_platform.platform_value
  is '平台编码';  
comment on column t_wx_dialog_platform.dialog_id
  is 't_wx_dialog.dialog_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_dialog_platform
  add constraint pk_wx_dialog_platform primary key (platform_id);
alter table t_wx_dialog_platform
  add constraint fk_wx_dialog_platform foreign key (dialog_id)
  references t_wx_dialog (dialog_id) on delete cascade;

-- create table
create table t_wx_dialog_channel
(
  channel_id   number,
  channel_value varchar2(300),
  dialog_id    number
);
-- add comments to the table 
comment on table t_wx_dialog_channel
  is 'app弹窗渠道';
-- add comments to the columns 
comment on column t_wx_dialog_channel.channel_id
  is '序列';
comment on column t_wx_dialog_channel.channel_value
  is '渠道编码';  
comment on column t_wx_dialog_channel.dialog_id
  is 't_wx_dialog.dialog_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_dialog_channel
  add constraint pk_wx_dialog_channel primary key (channel_id);
alter table t_wx_dialog_channel
  add constraint fk_wx_dialog_channel foreign key (dialog_id)
  references t_wx_dialog (dialog_id) on delete cascade;  

create table t_wx_dialog_version
(
  version_id    number,
  version_value     VARCHAR2(200),
  dialog_id     number
);
-- Add comments to the table 
comment on table t_wx_dialog_version
  is 'APP弹窗版本信息表';
-- Add comments to the columns 
comment on column t_wx_dialog_version.version_id
  is '序列';
comment on column t_wx_dialog_version.version_value
  is '数据字典item_code值';
comment on column t_wx_dialog_version.dialog_id
  is 't_wx_dialog.dialog_id'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_dialog_version
  add constraint pk_wx_dialog_version primary key (version_id);
alter table t_wx_dialog_version
  add constraint fk_wx_dialog_version foreign key (dialog_id)
  references t_wx_dialog (dialog_id) on delete cascade;


-- Create table
create table t_wx_notice
(
  notice_id    number,
  title       varchar2(300),
  content     clob,
  img_url     varchar2(300),
  html_url    varchar2(300),
  open_url    varchar2(300),
  direct_flag varchar2(10),
  start_date  date,
  end_date    date,
  order_num   number,
  act_status  number,
  class_id    number,
  lottery_id  number,
  test_user varchar2(300),
  target_user varchar2(300),
  notice_status number,
  create_user NUMBER,
  update_user NUMBER,
  create_date DATE,
  update_date DATE
);
-- Add comments to the table 
comment on table t_wx_notice
  is 'APP资讯信息表';
-- Add comments to the columns 
comment on column t_wx_notice.notice_id
  is '序列';
comment on column t_wx_notice.title
  is '标题';
comment on column t_wx_notice.content
  is '内容';
comment on column t_wx_notice.img_url
  is '图片链接';
comment on column t_wx_notice.html_url
  is 'H5图片链接';
comment on column t_wx_notice.open_url
  is '跳转链接地址,direct_flag=1时必填';
comment on column t_wx_notice.direct_flag
  is '是否跳转';
comment on column t_wx_notice.start_date
  is '有效开始日期';
comment on column t_wx_notice.end_date
  is '有效截止日期';
comment on column t_wx_notice.order_num
  is '排序号';
comment on column t_wx_notice.act_status
  is '状态,数据字典值';
comment on column t_wx_notice.class_id
  is '活动类型';
comment on column t_wx_notice.lottery_id
  is '跳转页面,采种ID';
comment on column t_wx_notice.test_user
  is '测试用户'; 
comment on column t_wx_notice.target_user
  is '目标用户';   
comment on column t_wx_notice.notice_status
  is '状态(数据字典编码)'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_notice
  add constraint pk_wx_notice primary key (NOTICE_ID);

-- Create table
create table t_wx_notice_target
(
  target_id   number,
  target_user varchar2(300),
  notice_id    number
);
-- Add comments to the table 
comment on table t_wx_notice_target
  is 'APP资讯目标用户';
-- Add comments to the columns 
comment on column t_wx_notice_target.target_id
  is '序列';
comment on column t_wx_notice_target.target_user
  is '目标用户';
comment on column t_wx_notice_target.notice_id
  is 't_wx_notice.notice_id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_notice_target
  add constraint pk_wx_notice_target primary key (TARGET_ID);
alter table t_wx_notice_target
  add constraint fk_wx_notice_target foreign key (NOTICE_ID)
  references t_wx_notice (NOTICE_ID) on delete cascade;

-- create table
create table t_wx_notice_platform
(
  platform_id   number,
  platform_value varchar2(300),
  notice_id    number
);
-- add comments to the table 
comment on table t_wx_notice_platform
  is 'app资讯平台';
-- add comments to the columns 
comment on column t_wx_notice_platform.platform_id
  is '序列';
comment on column t_wx_notice_platform.platform_value
  is '平台编码';  
comment on column t_wx_notice_platform.notice_id
  is 't_wx_notice.notice_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_notice_platform
  add constraint pk_wx_notice_platform primary key (platform_id);
alter table t_wx_notice_platform
  add constraint fk_wx_notice_platform foreign key (notice_id)
  references t_wx_notice (notice_id) on delete cascade;

-- create table
create table t_wx_notice_channel
(
  channel_id   number,
  channel_value varchar2(300),
  notice_id    number
);
-- add comments to the table 
comment on table t_wx_notice_channel
  is 'app资讯渠道';
-- add comments to the columns 
comment on column t_wx_notice_channel.channel_id
  is '序列';
comment on column t_wx_notice_channel.channel_value
  is '渠道编码';  
comment on column t_wx_notice_channel.notice_id
  is 't_wx_notice.notice_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_notice_channel
  add constraint pk_wx_notice_channel primary key (channel_id);
alter table t_wx_notice_channel
  add constraint fk_wx_notice_channel foreign key (notice_id)
  references t_wx_notice (notice_id) on delete cascade;  


-- Create table
create table t_wx_advert
(
  advert_id    number,
  title       varchar2(300),
  content     clob,
  img_url     varchar2(300),
  html_url    varchar2(300),
  open_url    varchar2(300),
  direct_flag varchar2(10),
  start_date  date,
  end_date    date,
  order_num   number,
  act_status  number,
  class_id    number,
  lottery_id  number,
  test_user varchar2(300),
  target_user varchar2(300),
  advert_status number,
  create_user NUMBER,
  update_user NUMBER,
  create_date DATE,
  update_date DATE
);
-- Add comments to the table 
comment on table t_wx_advert
  is 'APP广告位信息表';
-- Add comments to the columns 
comment on column t_wx_advert.advert_id
  is '序列';
comment on column t_wx_advert.title
  is '标题';
comment on column t_wx_advert.content
  is '内容';
comment on column t_wx_advert.img_url
  is '图片链接';
comment on column t_wx_advert.html_url
  is 'H5图片链接';
comment on column t_wx_advert.open_url
  is '跳转链接地址,direct_flag=1时必填';
comment on column t_wx_advert.direct_flag
  is '是否跳转';
comment on column t_wx_advert.start_date
  is '有效开始日期';
comment on column t_wx_advert.end_date
  is '有效截止日期';
comment on column t_wx_advert.order_num
  is '排序号';
comment on column t_wx_advert.act_status
  is '状态,数据字典值';
comment on column t_wx_advert.class_id
  is '活动类型';
comment on column t_wx_advert.lottery_id
  is '跳转页面,采种ID';
comment on column t_wx_advert.test_user
  is '测试用户'; 
comment on column t_wx_advert.target_user
  is '目标用户';  
comment on column t_wx_advert.advert_status
  is '状态(数据字典编码)'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_advert
  add constraint pk_wx_advert primary key (advert_ID);

-- Create table
create table t_wx_advert_target
(
  target_id   number,
  target_user varchar2(300),
  advert_id    number
);
-- Add comments to the table 
comment on table t_wx_advert_target
  is 'APP广告位目标用户';
-- Add comments to the columns 
comment on column t_wx_advert_target.target_id
  is '序列';
comment on column t_wx_advert_target.target_user
  is '目标用户';
comment on column t_wx_advert_target.advert_id
  is 't_wx_advert.advert_id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_advert_target
  add constraint pk_wx_advert_target primary key (TARGET_ID);
alter table t_wx_advert_target
  add constraint fk_wx_advert_target foreign key (advert_ID)
  references t_wx_advert (advert_ID) on delete cascade;

-- create table
create table t_wx_advert_platform
(
  platform_id   number,
  platform_value varchar2(300),
  advert_id    number
);
-- add comments to the table 
comment on table t_wx_advert_platform
  is 'app广告位平台';
-- add comments to the columns 
comment on column t_wx_advert_platform.platform_id
  is '序列';
comment on column t_wx_advert_platform.platform_value
  is '平台编码';  
comment on column t_wx_advert_platform.advert_id
  is 't_wx_advert.advert_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_advert_platform
  add constraint pk_wx_advert_platform primary key (platform_id);
alter table t_wx_advert_platform
  add constraint fk_wx_advert_platform foreign key (advert_id)
  references t_wx_advert (advert_id) on delete cascade;

-- create table
create table t_wx_advert_channel
(
  channel_id   number,
  channel_value varchar2(300),
  advert_id    number
);
-- add comments to the table 
comment on table t_wx_advert_channel
  is 'app广告位渠道';
-- add comments to the columns 
comment on column t_wx_advert_channel.channel_id
  is '序列';
comment on column t_wx_advert_channel.channel_value
  is '渠道编码';  
comment on column t_wx_advert_channel.advert_id
  is 't_wx_advert.advert_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_advert_channel
  add constraint pk_wx_advert_channel primary key (channel_id);
alter table t_wx_advert_channel
  add constraint fk_wx_advert_channel foreign key (advert_id)
  references t_wx_advert (advert_id) on delete cascade;  



-- Create table
create table t_wx_push
(
  push_id    number,
  title       varchar2(300),
  content     clob,
  img_url     varchar2(300),
  html_url    varchar2(300),
  open_url    varchar2(300),
  direct_flag varchar2(10),
  start_date  date,
  end_date    date,
  push_date   date,
  order_num   number,
  act_status  number,
  class_id    number,
  lottery_id  number,
  push_type   varchar2(10),
  test_user varchar2(300),
  target_user varchar2(300),
  push_status number,
  notice_id   number,
  create_user NUMBER,
  update_user NUMBER,
  create_date DATE,
  update_date DATE
);
-- Add comments to the table 
comment on table t_wx_push
  is 'APP Push信息表';
-- Add comments to the columns 
comment on column t_wx_push.push_id
  is '序列';
comment on column t_wx_push.title
  is '标题';
comment on column t_wx_push.content
  is '内容';
comment on column t_wx_push.img_url
  is '图片链接';
comment on column t_wx_push.html_url
  is 'H5图片链接';  
comment on column t_wx_push.open_url
  is '跳转链接地址,direct_flag=1时必填';
comment on column t_wx_push.direct_flag
  is '是否跳转';
comment on column t_wx_push.start_date
  is '有效开始日期';
comment on column t_wx_push.end_date
  is '有效截止日期';  
comment on column t_wx_push.push_date
  is '最后一次即时推送时间';  
comment on column t_wx_push.order_num
  is '排序号';
comment on column t_wx_push.act_status
  is '状态,数据字典值';
comment on column t_wx_push.class_id
  is '活动类型';
comment on column t_wx_push.lottery_id
  is '跳转页面,采种ID';  
comment on column t_wx_push.push_type
  is '推送类型,数据字典值';  
comment on column t_wx_push.test_user
  is '测试用户'; 
comment on column t_wx_push.target_user
  is '目标用户';   
comment on column t_wx_push.push_status
  is '状态(数据字典编码)'; 
comment on column t_wx_push.notice_id
  is '公告位ID'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_push
  add constraint pk_wx_push primary key (push_id);  

-- Create table
create table t_wx_push_target
(
  target_id   number,
  target_user varchar2(300),
  push_id    number
);
-- Add comments to the table 
comment on table t_wx_push_target
  is 'APP push 表';
-- Add comments to the columns 
comment on column t_wx_push_target.target_id
  is '序列';
comment on column t_wx_push_target.target_user
  is '目标用户';
comment on column t_wx_push_target.push_id
  is 't_wx_push.push_id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_push_target
  add constraint pk_wx_push_target primary key (TARGET_ID);
alter table t_wx_push_target
  add constraint fk_wx_push_target foreign key (push_id)
  references t_wx_push (push_id) on delete cascade;

-- create table
create table t_wx_push_platform
(
  platform_id   number,
  platform_value varchar2(300),
  push_id    number
);
-- add comments to the table 
comment on table t_wx_push_platform
  is 'app Push平台';
-- add comments to the columns 
comment on column t_wx_push_platform.platform_id
  is '序列';
comment on column t_wx_push_platform.platform_value
  is '平台编码';  
comment on column t_wx_push_platform.push_id
  is 't_wx_push.push_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_push_platform
  add constraint pk_wx_push_platform primary key (platform_id);
alter table t_wx_push_platform
  add constraint fk_wx_push_platform foreign key (push_id)
  references t_wx_push (push_id) on delete cascade;

-- create table
create table t_wx_push_channel
(
  channel_id   number,
  channel_value varchar2(300),
  push_id    number
);
-- add comments to the table 
comment on table t_wx_push_channel
  is 'app Push渠道';
-- add comments to the columns 
comment on column t_wx_push_channel.channel_id
  is '序列';
comment on column t_wx_push_channel.channel_value
  is '渠道编码';  
comment on column t_wx_push_channel.push_id
  is 't_wx_push.push_id';
-- create/recreate primary, unique and foreign key constraints 
alter table t_wx_push_channel
  add constraint pk_wx_push_channel primary key (channel_id);
alter table t_wx_push_channel
  add constraint fk_wx_push_channel foreign key (push_id)
  references t_wx_push (push_id) on delete cascade;  


create table t_wx_push_version
(
  version_id    number,
  version_value     VARCHAR2(200),
  push_id     number
);
-- Add comments to the table 
comment on table t_wx_push_version
  is 'APP Push版本信息表';
-- Add comments to the columns 
comment on column t_wx_push_version.version_id
  is '序列';
comment on column t_wx_push_version.version_value
  is '数据字典item_code值';
comment on column t_wx_push_version.push_id
  is 't_wx_push.push_id'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_push_version
  add constraint pk_wx_push_version primary key (version_id);
alter table t_wx_push_version
  add constraint fk_wx_push_version foreign key (push_id)
  references t_wx_push (push_id) on delete cascade;  

create table t_wx_push_task
(
  task_id    number,
  task_date  date,
  task_flag   varchar2(10),
  push_id     number
);
-- Add comments to the table 
comment on table t_wx_push_task
  is 'APP Push定时推送表';
-- Add comments to the columns 
comment on column t_wx_push_task.task_id
  is '序列';
comment on column t_wx_push_task.task_date
  is '任务时间';
comment on column t_wx_push_task.task_flag
  is '任务标识';  
comment on column t_wx_push_task.push_id
  is 't_wx_push.push_id'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_push_task
  add constraint pk_wx_push_task primary key (task_id);
alter table t_wx_push_task
  add constraint fk_wx_push_task foreign key (push_id)
  references t_wx_push (push_id) on delete cascade;

-- Create table
create table t_wx_push_register
(
  register_id   number,
  target_user varchar2(300),
  RegistrationID    varchar2(500)
);
-- Add comments to the table 
comment on table t_wx_push_register
  is 'APP push 关联设备表';
-- Add comments to the columns 
comment on column t_wx_push_register.register_id
  is '序列';
comment on column t_wx_push_register.target_user
  is '目标用户';
comment on column t_wx_push_register.RegistrationID
  is 'jPush RegistrationID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_push_register
  add constraint pk_wx_push_register primary key (register_id);


create table t_wx_push_statistic
(
  statistic_id  number,
  platform      varchar2(300),
  version       varchar2(300),
  push_count    number,
  user_count    number,
  hit_count     number,
  msg_id        number,
  push_id       number,
  update_flag   number,
  CREATE_USER   NUMBER,
  UPDATE_USER   NUMBER,
  CREATE_DATE   DATE,
  UPDATE_DATE   DATE
);
-- Add comments to the table 
comment on table t_wx_push_statistic
  is 'APP Push数据统计表';
-- Add comments to the columns 
comment on column t_wx_push_statistic.statistic_id
  is '序列';
comment on column t_wx_push_statistic.platform
  is 'Push平台';
comment on column t_wx_push_statistic.version
  is 'Push版本';    
comment on column t_wx_push_statistic.push_count
  is 'Push发送次数';
comment on column t_wx_push_statistic.user_count
  is 'Push用户登陆次数';
comment on column t_wx_push_statistic.hit_count
  is 'Push点击次数'; 
comment on column t_wx_push_statistic.msg_id
  is '关联JPush的msg_id';      
comment on column t_wx_push_statistic.push_id
  is 't_wx_push.push_id'; 
comment on column t_wx_push_statistic.update_flag
  is '更新标识';   
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_push_statistic
  add constraint t_wx_push_statistic primary key (statistic_id);
alter table t_wx_push_statistic
  add constraint fk_wx_push_statistic foreign key (push_id)
  references t_wx_push (push_id) on delete cascade; 



--------------------------------------Create sequence
create sequence seq_app_dictionary
minvalue 1000
start with 1000
increment by 1;

create sequence seq_app_dictionary_item
minvalue 1000
start with 1000
increment by 1;

create sequence seq_app_catalog
minvalue 1000
start with 1000
increment by 1;
 
create sequence seq_app_user
minvalue 1000
start with 1000
increment by 1;

create sequence seq_app_role
minvalue 1000
start with 1000
increment by 1;
 
create sequence seq_app_user_role_program
minvalue 1000
start with 1000
increment by 1;
 
create sequence seq_app_right
minvalue 1000
start with 1000
increment by 1;
 
create sequence seq_app_role_right
minvalue 1000
start with 1000
increment by 1;

create sequence seq_app_role_catalog
minvalue 1000
start with 1000
increment by 1;

create sequence seq_app_attachment
minvalue 1000
start with 1000
increment by 1;

create sequence seq_app_attachnumber
minvalue 1000
start with 1000
increment by 1;
 
create sequence seq_app_log
minvalue 1
start with 1
increment by 1;
 
create sequence seq_app_dept
minvalue 2000
start with 2000
increment by 1;

-------------------业务序列
create sequence seq_wx_bulletin
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_bulletin_target
minvalue 1000
start with 1000
increment by 1;  

create sequence seq_wx_bulletin_platform
minvalue 1000
start with 1000
increment by 1; 

create sequence seq_wx_bulletin_channel
minvalue 1000
start with 1000
increment by 1; 

create sequence seq_wx_dialog
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_dialog_target
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_dialog_platform
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_dialog_channel
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_dialog_version
minvalue 1000
start with 1000
increment by 1;


create sequence seq_wx_advert
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_advert_target
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_advert_platform
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_advert_channel
minvalue 1000
start with 1000
increment by 1;


create sequence seq_wx_notice
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_notice_target
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_notice_platform
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_notice_channel
minvalue 1000
start with 1000
increment by 1;


create sequence seq_wx_push
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_push_target
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_push_platform
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_push_channel
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_push_version
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_push_task
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_push_register
minvalue 1000
start with 1000
increment by 1;

create sequence seq_wx_push_statistic
minvalue 1000
start with 1000
increment by 1;





--�������ݱ�ռ�  
--create tablespace tbs_wxms  logging  datafile       'D:\ProgramFiles\oracle\product\10.2.0\oradata\orcl\tbs_wxms.dbf' size 124m  autoextend on  next 5m  maxsize unlimited extent management local;        
--create temporary tablespace tbs_wxms_temp  tempfile 'D:\ProgramFiles\oracle\product\10.2.0\oradata\orcl\tbs_wxms_temp.dbf' size 124m  autoextend on  next 5m  maxsize unlimited extent management local;

--�����û���ָ����ռ�
--create user wxms identified by wxms;
--alter user wxms default tablespace tbs_wxms;
--alter user wxms temporary tablespace tbs_wxms_temp;     
--���û�����Ȩ��  dba    
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
  is '�����ֵ�����';
-- Add comments to the columns 
comment on column T_APP_DICTIONARY.DICTIONARY_NAME
  is '�������';
comment on column T_APP_DICTIONARY.DESCRIPTION
  is '����';
 comment on column T_APP_DICTIONARY.PARENT_ID
  is '��ID';
comment on column T_APP_DICTIONARY.DICTIONARY_STATUS
  is '״̬ 1�����ã�0����(�����ֵ����)';
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
  is '�����ֵ�ӱ�';
-- Add comments to the columns 
comment on column T_APP_DICTIONARY_ITEM.ITEM_NAME
  is '��������';
comment on column T_APP_DICTIONARY_ITEM.item_code
  is '�������';  
comment on column T_APP_DICTIONARY_ITEM.DICTIONARY_ID
  is '���t_app_dictionary.dictionary_id��';
comment on column T_APP_DICTIONARY_ITEM.DESCRIPTION
  is '����';
comment on column T_APP_DICTIONARY_ITEM.ITEM_STATUS
  is '״̬ 1�����ã�0����(�����ֵ����)';
 comment on column T_APP_DICTIONARY.PARENT_ID
  is '��ID';
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
  is 'ϵͳ��Ŀ��';
-- Add comments to the columns 
comment on column T_APP_CATALOG.CATALOG_ID
  is '���';
comment on column T_APP_CATALOG.CATALOG_NAME
  is '��Ŀ����';
comment on column T_APP_CATALOG.CATALOG_VALUE
  is '��Ŀֵ';
comment on column T_APP_CATALOG.CATALOG_URL
  is '��ĿURL';
comment on column T_APP_CATALOG.PARENT_ID
  is '��ID';
comment on column T_APP_CATALOG.CATALOG_FLAG
  is '��Ŀ��ʶ 1����Ч��0����Ч ״̬ (�����ֵ����)';
comment on column T_APP_CATALOG.CATALOG_DESCRIPTION
  is '��Ŀ����';
comment on column T_APP_CATALOG.ORDER_NUM
  is '�����';  
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
  is '�û���';
-- Add comments to the columns 
comment on column T_APP_USER.USER_ID
  is '����';
comment on column T_APP_USER.USER_NAME
  is '�û���';
comment on column T_APP_USER.USER_PASSWORD
  is '����';
comment on column T_APP_USER.USER_SEX
  is '�Ա� 1���У�0Ů(�����ֵ����)';
comment on column T_APP_USER.USER_EMAIL
  is '����';
comment on column T_APP_USER.USER_PHONE
  is '�绰';
comment on column T_APP_USER.USER_MOBILE
  is '�ֻ�';
comment on column T_APP_USER.USER_FAX
  is '����';
comment on column T_APP_USER.USER_ADDRESS
  is '��ַ';
comment on column T_APP_USER.USER_ZIP
  is '�ʱ�';
comment on column T_APP_USER.USER_STATUS
  is '״̬ 1�����ã�0����(�����ֵ����)';
comment on column T_APP_USER.DEPT_ID
  is '���ţ�T_APP_DEPT.dept_id��';
comment on column T_APP_USER.USER_AGE
  is '����';
comment on column T_APP_USER.USER_BIRTHDAY
  is '��������';
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
  is '��ɫ��';
-- Add comments to the columns 
comment on column T_APP_ROLE.ROLE_ID
  is '����';
comment on column T_APP_ROLE.ROLE_NAME
  is '��ɫ����';
comment on column T_APP_ROLE.ROLE_DESCRIPTION
  is '����';
comment on column T_APP_ROLE.ROLE_STATUS
  is '״̬ 1�����ã�0����(�����ֵ����)';
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
  is 'Ȩ�ޱ�';
-- Add comments to the columns 
comment on column T_APP_RIGHT.RIGHT_ID
  is '����';
comment on column T_APP_RIGHT.RIGHT_NAME
  is 'Ȩ������';
comment on column T_APP_RIGHT.RIGHT_TYPE
  is 'Ȩ������';
comment on column T_APP_RIGHT.RIGHT_STATUS
  is '״̬ 1�����ã�0����(�����ֵ����)';
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
  is 'ά�ȱ�';
-- Add comments to the columns 
comment on column T_APP_PROGRAM.PROGRAM_ID
  is '����';
comment on column T_APP_PROGRAM.PROGRAM_NAME
  is 'ά������';
comment on column T_APP_PROGRAM.DESCRIPTION
  is '����';
comment on column T_APP_PROGRAM.STATUS
  is '״̬';
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
  is 'ά����Ŀ��';
-- Add comments to the columns 
comment on column T_APP_PROGRAM_ITEM.PROGRAM_ITEM_ID
  is '����';
comment on column T_APP_PROGRAM_ITEM.PROGRAM_ITEM_NAME
  is 'ά����Ŀ����';
comment on column T_APP_PROGRAM_ITEM.PROGRAM_ITEM_VALUE
  is 'ά����Ŀֵ';
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
  is 'ά�ȶ����';
-- Add comments to the columns 
comment on column T_APP_DIMENSION.DIMENSION_ID
  is '����';
comment on column T_APP_DIMENSION.DIMENSION_NAME
  is 'ά������';
comment on column T_APP_DIMENSION.DIMENSION_CODE
  is 'ά�ȱ���';
comment on column T_APP_DIMENSION.DIMENSION_FIELD
  is 'ά���ֶ�';
comment on column T_APP_DIMENSION.DIMENSION_URL
  is 'ά������';
comment on column T_APP_DIMENSION.DIMENSION_TYPE
  is 'ά������';
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
  is '��ɫ��Ȩ�޹�����';
-- Add comments to the columns 
comment on column T_APP_ROLE_RIGHT.ROLE_RIGHT_ID
  is '����';
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
  is '��ɫ����Ŀ�˵�������';
-- Add comments to the columns 
comment on column T_APP_ROLE_CATALOG.ROLE_CATALOG_ID
  is '����';
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
  is '�û���ɫά�ȹ�����';
-- Add comments to the columns 
comment on column T_APP_USER_ROLE_PROGRAM.USER_ROLE_PROGRAM_ID
  is '����';
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
  is '����Ȩ���������';
-- Add comments to the columns 
comment on column T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_ID
  is '����';
comment on column T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_NAME
  is '����������';
comment on column T_APP_ATTRIBUTE_GROUP.ATTRIBUTE_GROUP_VALUE
  is '������ֵ';
comment on column T_APP_ATTRIBUTE_GROUP.STATUS
  is '״̬';
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
  is '����Ȩ�����Ա�';
-- Add comments to the columns 
comment on column T_APP_ATTRIBUTE.ATTRIBUTE_ID
  is '����';
comment on column T_APP_ATTRIBUTE.ATTRIBUTE_NAME
  is '��������';
comment on column T_APP_ATTRIBUTE.ATTRIBUTE_VALUE
  is '����ֵ';
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
  is '����Ȩ�����������ɫ������';
-- Add comments to the columns 
comment on column T_APP_ATTRIBUTE_GROUP_ROLE.ATTRIBUTE_GROUP_ROLE_ID
  is '����';
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
  is '���ű�';
-- Add comments to the columns 
comment on column T_APP_DEPT.DEPT_NAME
  is '��������';
comment on column T_APP_DEPT.DESCRIPTION
  is '����';
comment on column T_APP_DEPT.PARENT_ID
  is '��ID';  
comment on column T_APP_DEPT.DEPT_STATUS
  is '״̬ 1�����ã�0����(�����ֵ����)';
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
  is '������';         
-- Add comments to the columns 
comment on column T_APP_ATTACHMENT.attach_id
  is '����';
comment on column T_APP_ATTACHMENT.attach_name
  is '��������';
comment on column T_APP_ATTACHMENT.attach_size
  is '������С';
comment on column T_APP_ATTACHMENT.attach_path
  is '����·��';
comment on column T_APP_ATTACHMENT.attach_version
  is '�����汾';
comment on column T_APP_ATTACHMENT.attach_moudle
  is '��������ģ��';
comment on column T_APP_ATTACHMENT.attach_moudle
  is '�������';
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
  is 'ϵͳ��־';
-- add comments to the columns 
comment on column t_app_log.operate
  is '��������';
comment on column t_app_log.user_ip
  is '������ip';
comment on column t_app_log.log_module
  is 'ģ��';  
-- create/recreate primary, unique and foreign key constraints 
alter table t_app_log
  add constraint pk_app_log_id primary key (log_id);
  

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


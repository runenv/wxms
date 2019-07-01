-------------------------------ҵ���
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
  is 'APP������Ϣ��';
-- Add comments to the columns 
comment on column t_wx_bulletin.bulletin_id
  is '����';
comment on column t_wx_bulletin.title
  is '����';
comment on column t_wx_bulletin.title_wap
  is 'wap����';  
comment on column t_wx_bulletin.content
  is '����';
comment on column t_wx_bulletin.img_url
  is 'ͼƬ����';
comment on column t_wx_bulletin.html_url
  is 'H5ͼƬ����';
comment on column t_wx_bulletin.open_url
  is '��ת���ӵ�ַ,direct_flag=1ʱ����';
comment on column t_wx_bulletin.direct_flag
  is '�Ƿ���ת';
comment on column t_wx_bulletin.start_date
  is '��Ч��ʼ����';
comment on column t_wx_bulletin.end_date
  is '��Ч��ֹ����';
comment on column t_wx_bulletin.order_num
  is '�����';
comment on column t_wx_bulletin.act_status
  is '״̬,�����ֵ�ֵ';
comment on column t_wx_bulletin.class_id
  is '�����';
comment on column t_wx_bulletin.lottery_id
  is '��תҳ��,����ID';  
comment on column t_wx_bulletin.test_user
  is '�����û�';
comment on column t_wx_bulletin.target_user
  is 'Ŀ���û�';  
comment on column t_wx_bulletin.bulletin_status
  is '״̬(�����ֵ����)';     
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
  is 'APP����Ŀ���û���';
-- Add comments to the columns 
comment on column t_wx_bulletin_target.target_id
  is '����';
comment on column t_wx_bulletin_target.target_user
  is 'Ŀ���û�';
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
  is 'app����ƽ̨';
-- add comments to the columns 
comment on column t_wx_bulletin_platform.platform_id
  is '����';
comment on column t_wx_bulletin_platform.platform_value
  is 'ƽ̨����';  
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
  is 'app��������';
-- add comments to the columns 
comment on column t_wx_bulletin_channel.channel_id
  is '����';
comment on column t_wx_bulletin_channel.channel_value
  is '��������';  
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
  is 'APP������Ϣ��';
-- Add comments to the columns 
comment on column t_wx_dialog.dialog_id
  is '����';
comment on column t_wx_dialog.title
  is '����';
comment on column t_wx_dialog.content
  is '����';
comment on column t_wx_dialog.img_url
  is 'ͼƬ����';
comment on column t_wx_dialog.html_url
  is 'H5ͼƬ����';  
comment on column t_wx_dialog.open_url
  is '��ת���ӵ�ַ,direct_flag=1ʱ����';
comment on column t_wx_dialog.direct_flag
  is '�Ƿ���ת';
comment on column t_wx_dialog.start_date
  is '��Ч��ʼ����';
comment on column t_wx_dialog.end_date
  is '��Ч��ֹ����';
comment on column t_wx_dialog.order_num
  is '�����';
comment on column t_wx_dialog.act_status
  is '״̬,�����ֵ�ֵ';
comment on column t_wx_dialog.class_id
  is '�����';
comment on column t_wx_dialog.lottery_id
  is '��תҳ��,����ID';  
comment on column t_wx_dialog.test_user
  is '�����û�';
comment on column t_wx_dialog.target_user
  is 'Ŀ���û�';    
comment on column t_wx_dialog.dialog_status
  is '״̬(�����ֵ����)'; 
comment on column t_wx_dialog.notice_id
  is '���λID';   
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
  is 'APP����Ŀ���û���';
-- Add comments to the columns 
comment on column t_wx_dialog_target.target_id
  is '����';
comment on column t_wx_dialog_target.target_user
  is 'Ŀ���û�';
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
  is 'app����ƽ̨';
-- add comments to the columns 
comment on column t_wx_dialog_platform.platform_id
  is '����';
comment on column t_wx_dialog_platform.platform_value
  is 'ƽ̨����';  
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
  is 'app��������';
-- add comments to the columns 
comment on column t_wx_dialog_channel.channel_id
  is '����';
comment on column t_wx_dialog_channel.channel_value
  is '��������';  
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
  is 'APP�����汾��Ϣ��';
-- Add comments to the columns 
comment on column t_wx_dialog_version.version_id
  is '����';
comment on column t_wx_dialog_version.version_value
  is '�����ֵ�item_codeֵ';
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
  is 'APP��Ѷ��Ϣ��';
-- Add comments to the columns 
comment on column t_wx_notice.notice_id
  is '����';
comment on column t_wx_notice.title
  is '����';
comment on column t_wx_notice.content
  is '����';
comment on column t_wx_notice.img_url
  is 'ͼƬ����';
comment on column t_wx_notice.html_url
  is 'H5ͼƬ����';
comment on column t_wx_notice.open_url
  is '��ת���ӵ�ַ,direct_flag=1ʱ����';
comment on column t_wx_notice.direct_flag
  is '�Ƿ���ת';
comment on column t_wx_notice.start_date
  is '��Ч��ʼ����';
comment on column t_wx_notice.end_date
  is '��Ч��ֹ����';
comment on column t_wx_notice.order_num
  is '�����';
comment on column t_wx_notice.act_status
  is '״̬,�����ֵ�ֵ';
comment on column t_wx_notice.class_id
  is '�����';
comment on column t_wx_notice.lottery_id
  is '��תҳ��,����ID';
comment on column t_wx_notice.test_user
  is '�����û�'; 
comment on column t_wx_notice.target_user
  is 'Ŀ���û�';   
comment on column t_wx_notice.notice_status
  is '״̬(�����ֵ����)'; 
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
  is 'APP��ѶĿ���û�';
-- Add comments to the columns 
comment on column t_wx_notice_target.target_id
  is '����';
comment on column t_wx_notice_target.target_user
  is 'Ŀ���û�';
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
  is 'app��Ѷƽ̨';
-- add comments to the columns 
comment on column t_wx_notice_platform.platform_id
  is '����';
comment on column t_wx_notice_platform.platform_value
  is 'ƽ̨����';  
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
  is 'app��Ѷ����';
-- add comments to the columns 
comment on column t_wx_notice_channel.channel_id
  is '����';
comment on column t_wx_notice_channel.channel_value
  is '��������';  
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
  is 'APP���λ��Ϣ��';
-- Add comments to the columns 
comment on column t_wx_advert.advert_id
  is '����';
comment on column t_wx_advert.title
  is '����';
comment on column t_wx_advert.content
  is '����';
comment on column t_wx_advert.img_url
  is 'ͼƬ����';
comment on column t_wx_advert.html_url
  is 'H5ͼƬ����';
comment on column t_wx_advert.open_url
  is '��ת���ӵ�ַ,direct_flag=1ʱ����';
comment on column t_wx_advert.direct_flag
  is '�Ƿ���ת';
comment on column t_wx_advert.start_date
  is '��Ч��ʼ����';
comment on column t_wx_advert.end_date
  is '��Ч��ֹ����';
comment on column t_wx_advert.order_num
  is '�����';
comment on column t_wx_advert.act_status
  is '״̬,�����ֵ�ֵ';
comment on column t_wx_advert.class_id
  is '�����';
comment on column t_wx_advert.lottery_id
  is '��תҳ��,����ID';
comment on column t_wx_advert.test_user
  is '�����û�'; 
comment on column t_wx_advert.target_user
  is 'Ŀ���û�';  
comment on column t_wx_advert.advert_status
  is '״̬(�����ֵ����)'; 
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
  is 'APP���λĿ���û�';
-- Add comments to the columns 
comment on column t_wx_advert_target.target_id
  is '����';
comment on column t_wx_advert_target.target_user
  is 'Ŀ���û�';
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
  is 'app���λƽ̨';
-- add comments to the columns 
comment on column t_wx_advert_platform.platform_id
  is '����';
comment on column t_wx_advert_platform.platform_value
  is 'ƽ̨����';  
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
  is 'app���λ����';
-- add comments to the columns 
comment on column t_wx_advert_channel.channel_id
  is '����';
comment on column t_wx_advert_channel.channel_value
  is '��������';  
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
  is 'APP Push��Ϣ��';
-- Add comments to the columns 
comment on column t_wx_push.push_id
  is '����';
comment on column t_wx_push.title
  is '����';
comment on column t_wx_push.content
  is '����';
comment on column t_wx_push.img_url
  is 'ͼƬ����';
comment on column t_wx_push.html_url
  is 'H5ͼƬ����';  
comment on column t_wx_push.open_url
  is '��ת���ӵ�ַ,direct_flag=1ʱ����';
comment on column t_wx_push.direct_flag
  is '�Ƿ���ת';
comment on column t_wx_push.start_date
  is '��Ч��ʼ����';
comment on column t_wx_push.end_date
  is '��Ч��ֹ����';  
comment on column t_wx_push.push_date
  is '���һ�μ�ʱ����ʱ��';  
comment on column t_wx_push.order_num
  is '�����';
comment on column t_wx_push.act_status
  is '״̬,�����ֵ�ֵ';
comment on column t_wx_push.class_id
  is '�����';
comment on column t_wx_push.lottery_id
  is '��תҳ��,����ID';  
comment on column t_wx_push.push_type
  is '��������,�����ֵ�ֵ';  
comment on column t_wx_push.test_user
  is '�����û�'; 
comment on column t_wx_push.target_user
  is 'Ŀ���û�';   
comment on column t_wx_push.push_status
  is '״̬(�����ֵ����)'; 
comment on column t_wx_push.notice_id
  is '����λID'; 
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
  is 'APP push ��';
-- Add comments to the columns 
comment on column t_wx_push_target.target_id
  is '����';
comment on column t_wx_push_target.target_user
  is 'Ŀ���û�';
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
  is 'app Pushƽ̨';
-- add comments to the columns 
comment on column t_wx_push_platform.platform_id
  is '����';
comment on column t_wx_push_platform.platform_value
  is 'ƽ̨����';  
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
  is 'app Push����';
-- add comments to the columns 
comment on column t_wx_push_channel.channel_id
  is '����';
comment on column t_wx_push_channel.channel_value
  is '��������';  
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
  is 'APP Push�汾��Ϣ��';
-- Add comments to the columns 
comment on column t_wx_push_version.version_id
  is '����';
comment on column t_wx_push_version.version_value
  is '�����ֵ�item_codeֵ';
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
  is 'APP Push��ʱ���ͱ�';
-- Add comments to the columns 
comment on column t_wx_push_task.task_id
  is '����';
comment on column t_wx_push_task.task_date
  is '����ʱ��';
comment on column t_wx_push_task.task_flag
  is '�����ʶ';  
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
  is 'APP push �����豸��';
-- Add comments to the columns 
comment on column t_wx_push_register.register_id
  is '����';
comment on column t_wx_push_register.target_user
  is 'Ŀ���û�';
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
  is 'APP Push����ͳ�Ʊ�';
-- Add comments to the columns 
comment on column t_wx_push_statistic.statistic_id
  is '����';
comment on column t_wx_push_statistic.platform
  is 'Pushƽ̨';
comment on column t_wx_push_statistic.version
  is 'Push�汾';    
comment on column t_wx_push_statistic.push_count
  is 'Push���ʹ���';
comment on column t_wx_push_statistic.user_count
  is 'Push�û���½����';
comment on column t_wx_push_statistic.hit_count
  is 'Push�������'; 
comment on column t_wx_push_statistic.msg_id
  is '����JPush��msg_id';      
comment on column t_wx_push_statistic.push_id
  is 't_wx_push.push_id'; 
comment on column t_wx_push_statistic.update_flag
  is '���±�ʶ';   
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_wx_push_statistic
  add constraint t_wx_push_statistic primary key (statistic_id);
alter table t_wx_push_statistic
  add constraint fk_wx_push_statistic foreign key (push_id)
  references t_wx_push (push_id) on delete cascade; 



-------------------ҵ������
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

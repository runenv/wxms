/**��ʼ���û���Ϣ**/
insert into T_APP_USER (USER_ID, USER_NAME, USER_PASSWORD, USER_SEX, USER_EMAIL, USER_PHONE, USER_MOBILE, USER_FAX, 
       USER_ADDRESS, USER_ZIP, USER_AGE, USER_BIRTHDAY, USER_STATUS, DEPT_ID, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (1, 'admin', 'admin123', 1, 'admin@163.com', null, '13558216583', null, '����', '518000', 20, sysdate, 1, 1071, 1, 1, sysdate, sysdate);
commit;

insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (1, 'ϵͳ����Ա', 'ϵͳ����Ա', 1, 1, 1, sysdate, sysdate);
insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (2, '��Ʒ', '��Ʒ', 1, 1, 1, sysdate, sysdate);
insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (3, '��Ӫ', '��Ӫ', 1, 1, 1, sysdate, sysdate);
insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (4, '�г�', '�г�', 1, 1, 1, sysdate, sysdate);
commit;

insert into T_APP_USER_ROLE_PROGRAM (USER_ROLE_PROGRAM_ID, USER_ID, ROLE_ID, PROGRAM_ID, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (1, 1, 1, null, 1, 1, sysdate, sysdate);
commit;



/**��ʼ����Ŀ��Ϣ**/
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (10, 'ϵͳ����', 'ϵͳ����', null, 0, '1', null, 10, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (101, '�û��б�', '�û��б�', 'user/listUser', 10, '1', null, 101, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (102, '��ɫ�б�', '��ɫ�б�', 'role/listRole', 10, '1', null, 102, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (103, '�����б�', '�����б�', 'dept/listDept', 10, '1', null, 103, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (104, '��Ŀ�б�', '��Ŀ�б�', 'catalog/findCatalogList', 10, '1', null, 104, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (105, '�����ֵ��б�', '�����ֵ��б�', 'dict/findDictList', 10, '1', null, 105, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (11, '���λ����', '���λ����', null, 0, '1', null, 11, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (106, '���λ�б�', '���λ�б�', 'notice/listNotice', 11, '1', null, 106, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (107, '��ӹ��λ', '��ӹ��λ', 'notice/editNotice', 11, '1', null, 107, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (12, '������', '������', null, 0, '1', null, 12, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (108, '����б�', '����б�', 'bulletin/listBulletin', 12, '1', null, 108, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (109, '��ӹ��', '��ӹ��', 'bulletin/editBulletin', 12, '1', null, 109, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (13, '��������', '��������', null, 0, '1', null, 13, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (110, '�����б�', '�����б�', 'dialog/listDialog', 13, '1', null, 110, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (111, '��ӵ���', '��ӵ���', 'dialog/editDialog', 13, '1', null, 111, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (14, 'Push����', 'Push����', null, 0, '1', null, 14, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (112, 'Push�б�', 'Push�б�', 'push/listPush', 14, '1', null, 112, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (113, '���Push', '���Push', 'push/editPush', 14, '1', null, 113, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (114, 'Push����ͳ��', 'Push����ͳ��', 'push/statisticPush', 14, '1', null, 114, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (15, '��Ѷ����', '��Ѷ����', null, 0, '1', null, 15, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (115, '��Ѷ�б�', '��Ѷ�б�', 'advert/listAdvert', 15, '1', null, 115, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (116, '�����Ѷ', '�����Ѷ', 'advert/editAdvert', 15, '1', null, 116, 1, 1, sysdate, sysdate);
commit;


insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(1, 1, 10, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(2, 1, 101, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(3, 1, 102, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(4, 1, 103, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(5, 1, 104, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(6, 1, 105, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(7, 1, 11, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(8, 1, 106, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(9, 1, 107, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(10, 1, 12, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(11, 1, 108, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(12, 1, 109, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(13, 1, 13, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(14, 1, 110, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(15, 1, 111, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(16, 1, 14, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(17, 1, 112, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(18, 1, 113, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(19, 1, 114, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(20, 1, 15, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(21, 1, 115, 1, 1, sysdate, sysdate);
insert into t_app_role_catalog(role_catalog_id, role_id, catalog_id, create_user, update_user, create_date, update_date)
values(22, 1, 116, 1, 1, sysdate, sysdate);
commit;



/**��ʼ��������Ϣ**/
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1000, '�����', '�����', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1004, '������Դ����', '������Դ����', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1006, '��������', '��������', 1004, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1010, '���Բ�', '���Բ�', 1007, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1012, '��������', '��������', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1014, '��������', '��������', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1016, 'Ӧ����ά��', 'Ӧ����ά��', 1009, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1017, '������ά��', '������ά��', 1009, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1019, 'ҵ������', 'ҵ������', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1021, '����ҵ��', '����ҵ��', 1019, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1023, '����2��', '����2��', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1025, '����4��', '����4��', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1027, '����2��', '����2��', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1029, '����4��', '����4��', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1030, '����5��', '����5��', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1033, '���ݲ�', '���ݲ�', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1037, '�����Ż���', '�����Ż���', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1038, '�г�����', '�г�����', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1040, '������չ��', '������չ��', 1038, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1042, '��Ʒ�������', '��Ʒ�������', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1043, '��Ʒ��', '��Ʒ��', 1042, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1048, '�������ݲ�Ʒ��', '�������ݲ�Ʒ��', 1043, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1051, '��������', '��������', 1048, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1052, '��Ʋ�', '��Ʋ�', 1042, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1053, 'UI�����', 'UI�����', 1052, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1055, 'UI���', 'UI���', 1053, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1061, '����', '����', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1064, '������', '������', 1063, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1065, 'ƽ̨��', 'ƽ̨��', 1063, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1069, '������ά', '������ά', 1065, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1070, '��Ʊ��', '��Ʊ��', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1071, '��ƽ���ӹ�˾', '��ƽ���ӹ�˾', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1073, '��Ʒ��Ӫ��', '��Ʒ��Ӫ��', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1074, '�����з���', '�����з���', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1075, '�ֻ�Ͷעҵ��', '�ֻ�Ͷעҵ��', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1001, '��������', '��������', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1002, '��Ʋ�', '��Ʋ�', 1001, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1003, '���ɲ�', '���ɲ�', 1001, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1007, '��Ϣ��������', '��Ϣ��������', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1008, '�з���', '�з���', 1007, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1015, '������ά��', '������ά��', 1009, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1022, '����1��', '����1��', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1024, '����3��', '����3��', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1028, '����3��', '����3��', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1032, '��Ӫ��', '��Ӫ��', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1034, '�ͻ�����', '�ͻ�����', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1044, 'ƽ̨���ܲ�Ʒ��', 'ƽ̨���ܲ�Ʒ��', 1043, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1050, '���ݹ���', '���ݹ���', 1048, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1060, '�ܾ���', '�ܾ���', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1062, '����', '����', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1068, 'ƽ̨��ά', 'ƽ̨��ά', 1065, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1072, '�г���', '�г���', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1005, '���²�', '���²�', 1004, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1009, '��ά��', '��ά��', 1007, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1011, '����һ��', '����һ��', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1013, '��������', '��������', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1018, '���Թ�����', '���Թ�����', 1010, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1020, '����ҵ��', '����ҵ��', 1019, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1026, '����1��', '����1��', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1031, '��Ӫ����', '��Ӫ����', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1035, 'VIPά��', 'VIPά��', 1034, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1036, '�ճ��ͷ�', '�ճ��ͷ�', 1034, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1039, '�г����в�', '�г����в�', 1038, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1041, 'Ʒ�Ʋ߻���', 'Ʒ�Ʋ߻���', 1038, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1045, '���ֲ�Ʒ', '���ֲ�Ʒ', 1044, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1046, '������Ʒ', '������Ʒ', 1044, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1047, 'ƽ̨֧��', 'ƽ̨֧��', 1044, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1049, '����֧��', '����֧��', 1048, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1054, 'ǰ�˿���', 'ǰ�˿���', 1053, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1056, 'ƽ̨�ӹ�˾', 'ƽ̨�ӹ�˾', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1063, '������', '������', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1066, 'ƽ̨����', 'ƽ̨����', 1064, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1067, '���ؿ���', '���ؿ���', 1064, 1, 1, 1, sysdate, sysdate);
commit;



/**��ʼ�������ֵ�:sys 1.. DIALOG 2..  notice 3..  BULLETIN 4..  push 5..  ADVERT 6..**/
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (300, 'NOTICE_ACTION', 'NOTICE_ACTION', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (500, 'PUSH_TYPE', 'PUSH_TYPE', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (501, 'PUSH_PLATFORM', 'PUSH_PLATFORM', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (600, 'ADVERT_DIRECT', 'ADVERT_DIRECT', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (200, 'DIALOG_DIRECT', 'DIALOG_DIRECT', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (301, 'NOTICE_PLATFORM', 'NOTICE_PLATFORM', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (400, 'BULLETIN_DIRECT', 'BULLETIN_DIRECT', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (401, 'BULLETIN_PLATFORM', 'BULLETIN_PLATFORM', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (102, 'NOTICE_CLASS', 'NOTICE_CLASS', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (101, 'RELEASE_STATUS', 'RELEASE_STATUS', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (100, 'WXMS_STATUS', 'WXMS_STATUS', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (502, 'PUSH_DIRECT', 'PUSH_DIRECT', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (503, 'PUSH_VERSION', 'PUSH_VERSION', 1, 0, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY (dictionary_id, dictionary_name, description, dictionary_status, parent_id, create_user, update_user, create_date, update_date)
values (302, 'NOTICE_DIRECT', 'NOTICE_DIRECT', 1, 0, 1, 1, sysdate, sysdate);
commit;

insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (601, '�ͻ�����ҳ��ת', '1', 600, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (602, '�ҳ����ת', '0', 600, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (403, 'HTML5', 'html5', 401, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (404, 'WAP', 'wap', 401, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (502, '�Զ���', '0', 500, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (507, '�ͻ�����ҳ��ת', '1', 502, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (508, '�ҳ����ת', '0', 502, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (307, '�ȴ�', '1001', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (308, '��Ч��', '1002', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (309, '�ѹ���', '1003', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (312, 'δ��Ч', '1004', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (313, '�ѳ���', '1005', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (503, '��׿ֱͨ��', 'addZtc', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (504, 'ƻ��ֱͨ��', 'iosZtc', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (505, '��׿��Ʊ�ܼ�', 'addGj', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (506, 'ƻ����Ʊ�ܼ�', 'iosGj', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);

insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (509, 'Android4.4', 'Android4_4', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (510, 'IOS4.4', 'IOS4_4', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (511, 'Android4.5', 'Android4_5', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (512, 'IOS4.5', 'IOS4_5', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (701, '�ͻ�����ҳ��ת', '1', 200, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (702, '�ҳ����ת', '0', 200, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (310, '�ͻ�����ҳ��ת', '1', 302, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (311, '�ҳ����ת', '0', 302, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (232, '���λ', '504', 102, '�򿪹��λ����', 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (205, 'ע��ҳ', '500', 102, null, 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (200, '����ʾ��������', '-1', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (201, '��ͨ�', '0', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (202, '��ֵ�', '1', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (203, '���ʻ', '2', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (204, '��H5ҳ��', '3', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (214, '����3', '4', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (216, '���ǲ�', '8', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (218, '11ѡ5(���ֲ�)', '34', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (225, '����-�ȷ�', '92', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (227, '��������-ʤƽ��', '46', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (230, '��������-�÷�ʤ��', '37', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (401, '�ͻ�����ҳ��ת', '1', 400, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (402, '�ҳ����ת', '0', 400, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (301, '��׿ֱͨ��', 'addZtc', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (302, 'ƻ��ֱͨ��', 'iosZtc', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (303, '��׿��Ʊ�ܼ�', 'addGj', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (304, 'ƻ����Ʊ�ܼ�', 'iosGj', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (305, '�ͻ���HTML5', 'html5', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (306, 'wap', 'wap', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (102, '�ѷ���', '1', 101, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (103, 'δ����', '0', 101, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (501, '��ʱ', '1', 500, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (100, '��Ч', '1', 100, '��Ч', 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (101, '��Ч', '0', 100, '��Ч', 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (208, '��ֵҳ', '503', 102, null, 1, 202, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (211, '˫ɫ��', '11', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (220, '����ʱʱ��', '32', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (221, '��������', '14', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (226, '�ھ�����', '50', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (206, '����ҳ', '501', 102, null, 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (207, '����ҳ', '502', 102, null, 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (209, 'ʤ����', '1', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (210, '��ѡ9��', '5', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (212, '����3D', '12', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (213, '����͸', '13', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (215, '����5', '6', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (217, '��11ѡ5(ʮһ���˽�)', '31', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (219, '�㶫11ѡ5', '30', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (222, 'ʱʱ��(����)', '15', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (223, '�㶫����10��', '16', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (224, '����-����ʤƽ��', '89', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (228, '��������-�ȷ�', '47', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (229, '��������-ʤ��', '36', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (231, '��������-��С��', '39', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
commit;



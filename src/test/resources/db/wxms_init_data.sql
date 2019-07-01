/**初始化用户信息**/
insert into T_APP_USER (USER_ID, USER_NAME, USER_PASSWORD, USER_SEX, USER_EMAIL, USER_PHONE, USER_MOBILE, USER_FAX, 
       USER_ADDRESS, USER_ZIP, USER_AGE, USER_BIRTHDAY, USER_STATUS, DEPT_ID, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (1, 'admin', 'admin123', 1, 'admin@163.com', null, '13558216583', null, '深圳', '518000', 20, sysdate, 1, 1071, 1, 1, sysdate, sysdate);
commit;

insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (1, '系统管理员', '系统管理员', 1, 1, 1, sysdate, sysdate);
insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (2, '产品', '产品', 1, 1, 1, sysdate, sysdate);
insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (3, '运营', '运营', 1, 1, 1, sysdate, sysdate);
insert into T_APP_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (4, '市场', '市场', 1, 1, 1, sysdate, sysdate);
commit;

insert into T_APP_USER_ROLE_PROGRAM (USER_ROLE_PROGRAM_ID, USER_ID, ROLE_ID, PROGRAM_ID, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (1, 1, 1, null, 1, 1, sysdate, sysdate);
commit;



/**初始化栏目信息**/
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (10, '系统管理', '系统管理', null, 0, '1', null, 10, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (101, '用户列表', '用户列表', 'user/listUser', 10, '1', null, 101, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (102, '角色列表', '角色列表', 'role/listRole', 10, '1', null, 102, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (103, '部门列表', '部门列表', 'dept/listDept', 10, '1', null, 103, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (104, '栏目列表', '栏目列表', 'catalog/findCatalogList', 10, '1', null, 104, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (105, '数据字典列表', '数据字典列表', 'dict/findDictList', 10, '1', null, 105, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (11, '广告位管理', '广告位管理', null, 0, '1', null, 11, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (106, '广告位列表', '广告位列表', 'notice/listNotice', 11, '1', null, 106, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (107, '添加广告位', '添加广告位', 'notice/editNotice', 11, '1', null, 107, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (12, '广告管理', '广告管理', null, 0, '1', null, 12, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (108, '广告列表', '广告列表', 'bulletin/listBulletin', 12, '1', null, 108, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (109, '添加广告', '添加广告', 'bulletin/editBulletin', 12, '1', null, 109, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (13, '弹窗管理', '弹窗管理', null, 0, '1', null, 13, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (110, '弹窗列表', '弹窗列表', 'dialog/listDialog', 13, '1', null, 110, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (111, '添加弹窗', '添加弹窗', 'dialog/editDialog', 13, '1', null, 111, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (14, 'Push管理', 'Push管理', null, 0, '1', null, 14, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (112, 'Push列表', 'Push列表', 'push/listPush', 14, '1', null, 112, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (113, '添加Push', '添加Push', 'push/editPush', 14, '1', null, 113, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (114, 'Push数据统计', 'Push数据统计', 'push/statisticPush', 14, '1', null, 114, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (15, '资讯管理', '资讯管理', null, 0, '1', null, 15, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (115, '资讯列表', '资讯列表', 'advert/listAdvert', 15, '1', null, 115, 1, 1, sysdate, sysdate);
insert into T_APP_CATALOG (CATALOG_ID, CATALOG_NAME, CATALOG_VALUE, CATALOG_URL, PARENT_ID, CATALOG_FLAG, CATALOG_DESCRIPTION, ORDER_NUM, CREATE_USER, UPDATE_USER, CREATE_DATE, UPDATE_DATE)
values (116, '添加资讯', '添加资讯', 'advert/editAdvert', 15, '1', null, 116, 1, 1, sysdate, sysdate);
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



/**初始化部门信息**/
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1000, '管理办', '管理办', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1004, '人力资源中心', '人力资源中心', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1006, '行政事务部', '行政事务部', 1004, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1010, '测试部', '测试部', 1007, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1012, '开发二室', '开发二室', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1014, '开发四室', '开发四室', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1016, '应用运维室', '应用运维室', 1009, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1017, '数据运维室', '数据运维室', 1009, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1019, '业务中心', '业务中心', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1021, '竞技业务部', '竞技业务部', 1019, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1023, '竞技2组', '竞技2组', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1025, '竞技4组', '竞技4组', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1027, '数字2组', '数字2组', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1029, '数字4组', '数字4组', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1030, '数字5组', '数字5组', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1033, '内容部', '内容部', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1037, '数据优化部', '数据优化部', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1038, '市场中心', '市场中心', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1040, '渠道拓展部', '渠道拓展部', 1038, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1042, '产品设计中心', '产品设计中心', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1043, '产品部', '产品部', 1042, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1048, '数据内容产品组', '数据内容产品组', 1043, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1051, '搜体新闻', '搜体新闻', 1048, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1052, '设计部', '设计部', 1042, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1053, 'UI设计组', 'UI设计组', 1052, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1055, 'UI设计', 'UI设计', 1053, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1061, '商务部', '商务部', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1064, '开发组', '开发组', 1063, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1065, '平台组', '平台组', 1063, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1069, '基地运维', '基地运维', 1065, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1070, '出票部', '出票部', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1071, '地平线子公司', '地平线子公司', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1073, '产品运营部', '产品运营部', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1074, '技术研发部', '技术研发部', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1075, '手机投注业务部', '手机投注业务部', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1001, '财务中心', '财务中心', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1002, '会计部', '会计部', 1001, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1003, '出纳部', '出纳部', 1001, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1007, '信息技术中心', '信息技术中心', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1008, '研发部', '研发部', 1007, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1015, '基础运维室', '基础运维室', 1009, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1022, '竞技1组', '竞技1组', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1024, '竞技3组', '竞技3组', 1021, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1028, '数字3组', '数字3组', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1032, '运营部', '运营部', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1034, '客户服务部', '客户服务部', 1031, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1044, '平台功能产品组', '平台功能产品组', 1043, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1050, '内容功能', '内容功能', 1048, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1060, '总经办', '总经办', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1062, '财务部', '财务部', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1068, '平台运维', '平台运维', 1065, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1072, '市场部', '市场部', 1071, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1005, '人事部', '人事部', 1004, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1009, '运维部', '运维部', 1007, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1011, '开发一室', '开发一室', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1013, '开发三室', '开发三室', 1008, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1018, '测试管理室', '测试管理室', 1010, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1020, '数字业务部', '数字业务部', 1019, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1026, '数字1组', '数字1组', 1020, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1031, '运营中心', '运营中心', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1035, 'VIP维护', 'VIP维护', 1034, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1036, '日常客服', '日常客服', 1034, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1039, '市场调研部', '市场调研部', 1038, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1041, '品牌策划部', '品牌策划部', 1038, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1045, '数字产品', '数字产品', 1044, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1046, '竞技产品', '竞技产品', 1044, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1047, '平台支撑', '平台支撑', 1044, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1049, '数据支持', '数据支持', 1048, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1054, '前端开发', '前端开发', 1053, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1056, '平台子公司', '平台子公司', 0, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1063, '技术部', '技术部', 1056, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1066, '平台开发', '平台开发', 1064, 1, 1, 1, sysdate, sysdate);
insert into T_APP_DEPT (dept_id, dept_name, description, parent_id, dept_status, create_user, update_user, create_date, update_date)
values (1067, '基地开发', '基地开发', 1064, 1, 1, 1, sysdate, sysdate);
commit;



/**初始化数据字典:sys 1.. DIALOG 2..  notice 3..  BULLETIN 4..  push 5..  ADVERT 6..**/
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
values (601, '客户端内页跳转', '1', 600, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (602, '活动页面跳转', '0', 600, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (403, 'HTML5', 'html5', 401, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (404, 'WAP', 'wap', 401, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (502, '自定义', '0', 500, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (507, '客户端内页跳转', '1', 502, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (508, '活动页面跳转', '0', 502, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (307, '等待', '1001', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (308, '生效中', '1002', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (309, '已过期', '1003', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (312, '未生效', '1004', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (313, '已撤消', '1005', 300, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (503, '安卓直通车', 'addZtc', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (504, '苹果直通车', 'iosZtc', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (505, '安卓彩票管家', 'addGj', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (506, '苹果彩票管家', 'iosGj', 501, null, 1, 0, null, 1, 1, sysdate, sysdate);

insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (509, 'Android4.4', 'Android4_4', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (510, 'IOS4.4', 'IOS4_4', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (511, 'Android4.5', 'Android4_5', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (512, 'IOS4.5', 'IOS4_5', 503, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (701, '客户端内页跳转', '1', 200, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (702, '活动页面跳转', '0', 200, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (310, '客户端内页跳转', '1', 302, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (311, '活动页面跳转', '0', 302, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (232, '广告位', '504', 102, '打开广告位详情', 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (205, '注册页', '500', 102, null, 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (200, '不显示立即参与', '-1', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (201, '普通活动', '0', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (202, '充值活动', '1', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (203, '购彩活动', '2', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (204, '打开H5页面', '3', 102, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (214, '排列3', '4', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (216, '七星彩', '8', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (218, '11选5(多乐彩)', '34', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (225, '北单-比分', '92', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (227, '竞彩足球-胜平负', '46', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (230, '竞彩篮球-让分胜负', '37', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (401, '客户端内页跳转', '1', 400, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (402, '活动页面跳转', '0', 400, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (301, '安卓直通车', 'addZtc', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (302, '苹果直通车', 'iosZtc', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (303, '安卓彩票管家', 'addGj', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (304, '苹果彩票管家', 'iosGj', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (305, '客户端HTML5', 'html5', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (306, 'wap', 'wap', 301, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (102, '已发布', '1', 101, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (103, '未发布', '0', 101, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (501, '及时', '1', 500, null, 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (100, '有效', '1', 100, '有效', 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (101, '无效', '0', 100, '无效', 1, 0, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (208, '充值页', '503', 102, null, 1, 202, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (211, '双色球', '11', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (220, '江西时时彩', '32', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (221, '幸运赛车', '14', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (226, '冠军竞猜', '50', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (206, '反馈页', '501', 102, null, 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (207, '设置页', '502', 102, null, 1, 201, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (209, '胜负彩', '1', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (210, '任选9场', '5', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (212, '福彩3D', '12', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (213, '大乐透', '13', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (215, '排列5', '6', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (217, '老11选5(十一夺运金)', '31', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (219, '广东11选5', '30', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (222, '时时彩(重庆)', '15', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (223, '广东快乐10分', '16', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (224, '北单-让球胜平负', '89', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (228, '竞彩足球-比分', '47', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (229, '竞彩篮球-胜负', '36', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
insert into T_APP_DICTIONARY_ITEM (item_id, item_name, item_code, dictionary_id, description, item_status, parent_id, order_by, create_user, update_user, create_date, update_date)
values (231, '竞彩篮球-大小分', '39', 102, null, 1, 203, null, 1, 1, sysdate, sysdate);
commit;



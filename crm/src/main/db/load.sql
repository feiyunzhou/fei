INSERT INTO account_pl1 (val) VALUES ('有效');
INSERT INTO account_pl1 (val) VALUES('无效');
INSERT INTO account_pl1 (val) VALUES('终止');
INSERT INTO account_pl1 (val) VALUES('候选');


-- 医院分级;
INSERT INTO account_pl2 (val) VALUES('A');
INSERT INTO account_pl2 (val) VALUES('B');
INSERT INTO account_pl2 (val) VALUES('C');
INSERT INTO account_pl2 (val) VALUES('D');

-- 重点医院;
INSERT INTO account_pl3 (val) VALUES('是');
INSERT INTO account_pl3 (val) VALUES('否');

-- 市场分类;
INSERT INTO account_pl4 (val) VALUES('一级城市');
INSERT INTO account_pl4 (val) VALUES('二级城市');
INSERT INTO account_pl4 (val) VALUES('战略城市');

-- 客户中国区域;
INSERT INTO account_pl5 (val) VALUES('北中国');
INSERT INTO account_pl5 (val) VALUES('南中国');
INSERT INTO account_pl5 (val) VALUES('东中国');


-- 医院类型;
INSERT INTO account_pl6 (val) VALUES('麻药目标医院');
INSERT INTO account_pl6 (val) VALUES('慢痛目标医院');
INSERT INTO account_pl6 (val) VALUES('其他');

-- 性别;
INSERT INTO sex_pl (val) VALUES('男');
INSERT INTO sex_pl (val) VALUES('女');

-- 科室;
INSERT INTO contact_pl1 (val) VALUES('肿瘤科');
INSERT INTO contact_pl1 (val) VALUES('内科');
INSERT INTO contact_pl1 (val) VALUES('外科');
INSERT INTO contact_pl1 (val) VALUES('中医科');
INSERT INTO contact_pl1 (val) VALUES('化疗科');
INSERT INTO contact_pl1 (val) VALUES('关怀科');
INSERT INTO contact_pl1 (val) VALUES('牙科');
INSERT INTO contact_pl1 (val) VALUES('急症科');
INSERT INTO contact_pl1 (val) VALUES('骨科');
INSERT INTO contact_pl1 (val) VALUES('肝胆外科');
INSERT INTO contact_pl1 (val) VALUES('血液科');
INSERT INTO contact_pl1 (val) VALUES('风湿科');
INSERT INTO contact_pl1 (val) VALUES('呼吸科');

-- 职务;
INSERT INTO contact_pl2 (val) VALUES('主任');
INSERT INTO contact_pl2 (val) VALUES('主治医师');
INSERT INTO contact_pl2 (val) VALUES('科室主任');
INSERT INTO contact_pl2 (val) VALUES('采购');
INSERT INTO contact_pl2 (val) VALUES('科室主任');
INSERT INTO contact_pl2 (val) VALUES('院长');
INSERT INTO contact_pl2 (val) VALUES('副院长');
INSERT INTO contact_pl2 (val) VALUES('药剂科主任');

-- 职称;
INSERT INTO contact_pl3 (val) VALUES('住院医师');
INSERT INTO contact_pl3 (val) VALUES('主治医师');
INSERT INTO contact_pl3 (val) VALUES('副主任医师');
INSERT INTO contact_pl3 (val) VALUES('主任医师');
INSERT INTO contact_pl3 (val) VALUES('护士');
INSERT INTO contact_pl3 (val) VALUES('主管护师');
INSERT INTO contact_pl3 (val) VALUES('药师');
INSERT INTO contact_pl3 (val) VALUES('技师');


-- 主要产品;
INSERT INTO contact_pl4 (val) VALUES('奥施康定');
INSERT INTO contact_pl4 (val) VALUES('奇曼丁');
INSERT INTO contact_pl4 (val) VALUES('意施丁');
INSERT INTO contact_pl4 (val) VALUES('综合');
INSERT INTO contact_pl4 (val) VALUES('麻药');
INSERT INTO contact_pl4 (val) VALUES('慢痛');

-- 状态;
INSERT INTO contact_pl5 (val) VALUES('有效');
INSERT INTO contact_pl5 (val) VALUES('无效');

-- 医生分级
INSERT INTO contact_pl6 (val) VALUES('A');
INSERT INTO contact_pl6 (val) VALUES('B');
INSERT INTO contact_pl6 (val) VALUES('C');
INSERT INTO contact_pl6 (val) VALUES('D');

-- 状态;
INSERT INTO crmuser_pl1 (val) VALUES('有效');
INSERT INTO crmuser_pl1 (val) VALUES('无效');

-- 产品线;
INSERT INTO crmuser_pl2 (val) VALUES('麻药');
INSERT INTO crmuser_pl2 (val) VALUES('慢痛');
INSERT INTO crmuser_pl2 (val) VALUES('全部');


--   角色;
INSERT INTO role (val) VALUES('MCPC销售代表');
INSERT INTO role (val) VALUES('主管');
INSERT INTO role (val) VALUES('区域经理');
INSERT INTO role (val) VALUES('服务经理');
INSERT INTO role (val) VALUES('高级用户');

--  一级区域;
INSERT INTO region_level1_pl (val) VALUES('北中国');
INSERT INTO region_level1_pl (val) VALUES('南中国');
INSERT INTO region_level1_pl (val) VALUES('东中国');

--  二级区域;
INSERT INTO region_level2_pl (val) VALUES('华北');
INSERT INTO region_level2_pl (val) VALUES('西北');
INSERT INTO region_level2_pl (val) VALUES('东北');
INSERT INTO region_level2_pl (val) VALUES('西南');
INSERT INTO region_level2_pl (val) VALUES('华南');
INSERT INTO region_level2_pl (val) VALUES('华中');
INSERT INTO region_level2_pl (val) VALUES('华东');

--  拜访类型
INSERT INTO activity_types (val) VALUES('拜访');
INSERT INTO activity_types (val) VALUES('会议');

-- 拜访状态
INSERT INTO activity_status (val) VALUES('计划');
INSERT INTO activity_status (val) VALUES('完成');

-- ramdom update table
UPDATE activity SET contactId = FLOOR(78* RAND()+1);


INSERT INTO contactcrmuser (contactId,crmuserId) VALUES(FLOOR(78* RAND()+1),20);





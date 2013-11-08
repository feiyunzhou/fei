drop table if exists boolean_pl;
CREATE TABLE boolean_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO boolean_pl (val) VALUES('是');
INSERT INTO boolean_pl (val) VALUES('否');

-- 医院行政级别
drop table if exists account_administrativ_level_pl;
CREATE TABLE account_administrativ_level_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_administrativ_level_pl (val) VALUES('一级');
INSERT INTO account_administrativ_level_pl (val) VALUES('一级甲等');
INSERT INTO account_administrativ_level_pl (val) VALUES('一级乙等');
INSERT INTO account_administrativ_level_pl (val) VALUES('一级丙等');
INSERT INTO account_administrativ_level_pl (val) VALUES('二级');
INSERT INTO account_administrativ_level_pl (val) VALUES('二级甲等');
INSERT INTO account_administrativ_level_pl (val) VALUES('二级乙等');
INSERT INTO account_administrativ_level_pl (val) VALUES('二级丙等');
INSERT INTO account_administrativ_level_pl (val) VALUES('三级');
INSERT INTO account_administrativ_level_pl (val) VALUES('三级甲等');
INSERT INTO account_administrativ_level_pl (val) VALUES('三级乙等');
INSERT INTO account_administrativ_level_pl (val) VALUES('三级丙等');
INSERT INTO account_administrativ_level_pl (val) VALUES('未评级未评等');

-- 医院分级
drop table if exists account_grade_pl;
CREATE TABLE account_grade_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_grade_pl (val) VALUES('A');
INSERT INTO account_grade_pl (val) VALUES('B');
INSERT INTO account_grade_pl (val) VALUES('C');
INSERT INTO account_grade_pl (val) VALUES('D');


-- 医院状态
drop table if exists account_status_pl;
CREATE TABLE account_status_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_status_pl (val) VALUES ('无效');
INSERT INTO account_status_pl (val) VALUES('有效');


drop table if exists account_local_or_army_pl;
CREATE TABLE account_local_or_army_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_local_or_army_pl (val) VALUES ('地方医院');
INSERT INTO account_local_or_army_pl (val) VALUES('部队医院');

drop table if exists account_comprehensive_or_specialized_pl;
CREATE TABLE account_comprehensive_or_specialized_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_comprehensive_or_specialized_pl (val) VALUES ('综合医院');
INSERT INTO account_comprehensive_or_specialized_pl (val) VALUES('专科医院');


drop table if exists account_market_classification_pl;
CREATE TABLE account_market_classification_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_market_classification_pl (val) VALUES ('战略城市');
INSERT INTO account_market_classification_pl (val) VALUES('一级城市');
INSERT INTO account_market_classification_pl (val) VALUES('二级城市');
INSERT INTO account_market_classification_pl (val) VALUES('三级城市');
INSERT INTO account_market_classification_pl (val) VALUES('四级城市');

drop table if exists account_type_pl;
CREATE TABLE account_type_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO  account_type_pl (val) VALUES ('癌痛目标医院');
INSERT INTO  account_type_pl (val) VALUES('慢痛目标医院');
INSERT INTO  account_type_pl (val) VALUES('奥诺美目标医院');
INSERT INTO  account_type_pl (val) VALUES('非目标医院');

drop table if exists account_point_pl;
CREATE TABLE account_point_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_point_pl (val) VALUES ('癌痛重点医院');
INSERT INTO account_point_pl (val) VALUES('慢痛重点医院');
INSERT INTO account_point_pl (val) VALUES('奥诺美重点医院');
INSERT INTO account_point_pl (val) VALUES('非重点医院');


--activity related picklist
-- 类型
drop table if exists activity_event_type_pl;
CREATE TABLE activity_event_type_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO activity_event_type_pl (val) VALUES('拜访');
INSERT INTO activity_event_type_pl (val) VALUES('辅导');

-- 活动状态;
drop table if exists activity_status_pl;
CREATE TABLE activity_status_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO activity_status_pl (val) VALUES('计划');
INSERT INTO activity_status_pl (val) VALUES('完成');
INSERT INTO activity_status_pl (val) VALUES('取消');

drop table if exists activity_activity_types_pl;
CREATE TABLE activity_activity_types_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO activity_activity_types_pl (val) VALUES('专业化拜访');
INSERT INTO activity_activity_types_pl (val) VALUES('事务性拜访');

drop table if exists activity_visiting_purpose_pl;
CREATE TABLE activity_visiting_purpose_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  activity_type MEDIUMINT,
  parentId  MEDIUMINT,
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO activity_visiting_purpose_pl (val,activity_type,parentId) VALUES('传递产品知识',1,1);
INSERT INTO activity_visiting_purpose_pl (val,activity_type,parentId) VALUES('处方观念沟通',1,1);
INSERT INTO activity_visiting_purpose_pl (val,activity_type,parentId) VALUES('病例沟通',1,1);
INSERT INTO activity_visiting_purpose_pl (val,activity_type,parentId) VALUES('会议安排',2,2);
INSERT INTO activity_visiting_purpose_pl (val,activity_type,parentId) VALUES('会议跟进',2,2);
INSERT INTO activity_visiting_purpose_pl (val,activity_type,parentId) VALUES('交接工作',2,2);
INSERT INTO activity_visiting_purpose_pl (val,activity_type,parentId) VALUES('了解竞争',2,2);

-- 拜访目的1
drop table if exists sales_visiting_purpose_pl;
CREATE TABLE sales_visiting_purpose_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO sales_visiting_purpose_pl (val) VALUES('会议安排');
INSERT INTO sales_visiting_purpose_pl (val) VALUES('会议跟进');
INSERT INTO sales_visiting_purpose_pl (val) VALUES('交接工作');
INSERT INTO sales_visiting_purpose_pl (val) VALUES('了解竞争');

-- 拜访目的2
drop table if exists com_visiting_purpose_pl;
CREATE TABLE com_visiting_purpose_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO com_visiting_purpose_pl (val) VALUES('处方观念沟通');
INSERT INTO com_visiting_purpose_pl (val) VALUES('病例沟通');


drop table if exists activity_feature_product_pl;
CREATE TABLE activity_feature_product_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO activity_feature_product_pl (val) VALUES('美施康定');
INSERT INTO activity_feature_product_pl (val) VALUES('奥施康定');
INSERT INTO activity_feature_product_pl (val) VALUES('奇曼丁');
INSERT INTO activity_feature_product_pl (val) VALUES('若思本');
INSERT INTO activity_feature_product_pl (val) VALUES('奥诺美');

-- 性别;
drop table if exists sex_pl;
CREATE TABLE sex_pl
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO sex_pl (val) VALUES('男');
INSERT INTO sex_pl (val) VALUES('女');
-- 科室
drop table if exists contact_department_pl;
CREATE TABLE contact_department_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_department_pl (val) VALUES('ICU');
INSERT INTO contact_department_pl (val) VALUES('保健科');
INSERT INTO contact_department_pl (val) VALUES('采购科');
INSERT INTO contact_department_pl (val) VALUES('传染科');
INSERT INTO contact_department_pl (val) VALUES('创伤外科');
INSERT INTO contact_department_pl (val) VALUES('儿科');
INSERT INTO contact_department_pl (val) VALUES('耳鼻喉科');
INSERT INTO contact_department_pl (val) VALUES('方便门诊');
INSERT INTO contact_department_pl (val) VALUES('放化疗科');
INSERT INTO contact_department_pl (val) VALUES('放疗科');
INSERT INTO contact_department_pl (val) VALUES('放射科');
INSERT INTO contact_department_pl (val) VALUES('风湿科');
INSERT INTO contact_department_pl (val) VALUES('妇产科');
INSERT INTO contact_department_pl (val) VALUES('妇科');
INSERT INTO contact_department_pl (val) VALUES('肝胆外科');
INSERT INTO contact_department_pl (val) VALUES('感染科');
INSERT INTO contact_department_pl (val) VALUES('肛肠科');
INSERT INTO contact_department_pl (val) VALUES('姑息科');
INSERT INTO contact_department_pl (val) VALUES('骨科');
INSERT INTO contact_department_pl (val) VALUES('国际医疗科');
INSERT INTO contact_department_pl (val) VALUES('核医学科');
INSERT INTO contact_department_pl (val) VALUES('呼吸内科');
INSERT INTO contact_department_pl (val) VALUES('护理部');
INSERT INTO contact_department_pl (val) VALUES('急诊科');
INSERT INTO contact_department_pl (val) VALUES('介入科');
INSERT INTO contact_department_pl (val) VALUES('康复科');
INSERT INTO contact_department_pl (val) VALUES('口腔科');
INSERT INTO contact_department_pl (val) VALUES('老干科');
INSERT INTO contact_department_pl (val) VALUES('麻醉科');
INSERT INTO contact_department_pl (val) VALUES('泌尿外科');
INSERT INTO contact_department_pl (val) VALUES('脑外科');
INSERT INTO contact_department_pl (val) VALUES('内分泌科');
INSERT INTO contact_department_pl (val) VALUES('内科');
INSERT INTO contact_department_pl (val) VALUES('宁养院');
INSERT INTO contact_department_pl (val) VALUES('皮肤科');
INSERT INTO contact_department_pl (val) VALUES('普外科');
INSERT INTO contact_department_pl (val) VALUES('乳腺外科');
INSERT INTO contact_department_pl (val) VALUES('烧伤整形科');
INSERT INTO contact_department_pl (val) VALUES('神经科');
INSERT INTO contact_department_pl (val) VALUES('神经内科');
INSERT INTO contact_department_pl (val) VALUES('神经外科');
INSERT INTO contact_department_pl (val) VALUES('肾内科');
INSERT INTO contact_department_pl (val) VALUES('生物治疗科');
INSERT INTO contact_department_pl (val) VALUES('特需科');
INSERT INTO contact_department_pl (val) VALUES('疼痛科');
INSERT INTO contact_department_pl (val) VALUES('外科');
INSERT INTO contact_department_pl (val) VALUES('微创科');
INSERT INTO contact_department_pl (val) VALUES('胃肠外科');
INSERT INTO contact_department_pl (val) VALUES('消化科');
INSERT INTO contact_department_pl (val) VALUES('消化内科');
INSERT INTO contact_department_pl (val) VALUES('消化外科');
INSERT INTO contact_department_pl (val) VALUES('心内科');
INSERT INTO contact_department_pl (val) VALUES('心胸外科');
INSERT INTO contact_department_pl (val) VALUES('血管外科');
INSERT INTO contact_department_pl (val) VALUES('血透室');
INSERT INTO contact_department_pl (val) VALUES('血液科');
INSERT INTO contact_department_pl (val) VALUES('药剂科');
INSERT INTO contact_department_pl (val) VALUES('医保办');
INSERT INTO contact_department_pl (val) VALUES('医务科');
INSERT INTO contact_department_pl (val) VALUES('整形美容科');
INSERT INTO contact_department_pl (val) VALUES('质控科');
INSERT INTO contact_department_pl (val) VALUES('中西医结合科');
INSERT INTO contact_department_pl (val) VALUES('中医科');
INSERT INTO contact_department_pl (val) VALUES('肿瘤科');
INSERT INTO contact_department_pl (val) VALUES('肿瘤内科');
INSERT INTO contact_department_pl (val) VALUES('肿瘤外科');
INSERT INTO contact_department_pl (val) VALUES('综合科');
INSERT INTO contact_department_pl (val) VALUES('其他');

-- 行政职务;
drop table if exists contact_duty_pl;
CREATE TABLE contact_duty_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

INSERT INTO contact_duty_pl (val) VALUES('医生');
INSERT INTO contact_duty_pl (val) VALUES('副主任');
INSERT INTO contact_duty_pl (val) VALUES('主任');
INSERT INTO contact_duty_pl (val) VALUES('副院长');
INSERT INTO contact_duty_pl (val) VALUES('院长');
INSERT INTO contact_duty_pl (val) VALUES('院长助理');
INSERT INTO contact_duty_pl (val) VALUES('副书记');
INSERT INTO contact_duty_pl (val) VALUES('书记');
INSERT INTO contact_duty_pl (val) VALUES('护士');
INSERT INTO contact_duty_pl (val) VALUES('护士长');
INSERT INTO contact_duty_pl (val) VALUES('库管');
INSERT INTO contact_duty_pl (val) VALUES('采购');
INSERT INTO contact_duty_pl (val) VALUES('采购科科长');
INSERT INTO contact_duty_pl (val) VALUES('药师');
INSERT INTO contact_duty_pl (val) VALUES('药剂科副主任');
INSERT INTO contact_duty_pl (val) VALUES('药剂科主任');
INSERT INTO contact_duty_pl (val) VALUES('医务科副科长');
INSERT INTO contact_duty_pl (val) VALUES('医务科科长');
INSERT INTO contact_duty_pl (val) VALUES('医保办主任');
INSERT INTO contact_duty_pl (val) VALUES('科教科主任');
INSERT INTO contact_duty_pl (val) VALUES('信息科科长');
INSERT INTO contact_duty_pl (val) VALUES('其他');


-- 专业职务;
drop table if exists  contact_job_title_pl;
CREATE TABLE  contact_job_title_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_job_title_pl (val) VALUES('住院医师');
INSERT INTO contact_job_title_pl (val) VALUES('主治医师');
INSERT INTO contact_job_title_pl (val) VALUES('副主任医师');
INSERT INTO contact_job_title_pl (val) VALUES('主任医师');
INSERT INTO contact_job_title_pl (val) VALUES('护士');
INSERT INTO contact_job_title_pl (val) VALUES('主管护师');
INSERT INTO contact_job_title_pl (val) VALUES('副主任护士');
INSERT INTO contact_job_title_pl (val) VALUES('主任护师');
INSERT INTO contact_job_title_pl (val) VALUES('药师');
INSERT INTO contact_job_title_pl (val) VALUES('主管药师');
INSERT INTO contact_job_title_pl (val) VALUES('副主任药师');
INSERT INTO contact_job_title_pl (val) VALUES('主任药师');
INSERT INTO contact_job_title_pl (val) VALUES('技师');
INSERT INTO contact_job_title_pl (val) VALUES('主管技师');
INSERT INTO contact_job_title_pl (val) VALUES('副主任技师');
INSERT INTO contact_job_title_pl (val) VALUES('主任技师');
INSERT INTO contact_job_title_pl (val) VALUES('其他');

-- 状态;
drop table if exists contact_status_pl;
CREATE TABLE contact_status_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_status_pl (val) VALUES('无效');
INSERT INTO contact_status_pl (val) VALUES('有效');

-- 级别;
drop table if exists contact_grade_pl;
CREATE TABLE contact_grade_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_grade_pl (val) VALUES('A');
INSERT INTO contact_grade_pl (val) VALUES('B');
INSERT INTO contact_grade_pl (val) VALUES('C');
INSERT INTO contact_grade_pl (val) VALUES('D');

drop table if exists contact_market_classification_pl;
CREATE TABLE contact_market_classification_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_market_classification_pl (val) VALUES ('战略城市');
INSERT INTO contact_market_classification_pl (val) VALUES('一级城市');
INSERT INTO contact_market_classification_pl (val) VALUES('二级城市');
INSERT INTO contact_market_classification_pl (val) VALUES('三级城市');
INSERT INTO contact_market_classification_pl (val) VALUES('四级城市');


drop table if exists contact_generalization_target_pl;
CREATE TABLE contact_generalization_target_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_generalization_target_pl (val) VALUES ('美施康定');
INSERT INTO contact_generalization_target_pl (val) VALUES('奥施康定');
INSERT INTO contact_generalization_target_pl (val) VALUES('奇曼丁');
INSERT INTO contact_generalization_target_pl (val) VALUES('若思本');
INSERT INTO contact_generalization_target_pl (val) VALUES('奥诺美');


-- pick list for crmuser
-- 状态;
drop table if exists crmuser_pl1;
CREATE TABLE crmuser_pl1 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO `crmuser_pl1` (val) VALUES ('无效');
INSERT INTO `crmuser_pl1` (val) VALUES ('有效');

-- 产品线;
drop table if exists crmuser_pl2;
CREATE TABLE crmuser_pl2 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
-- 产品线;
INSERT INTO crmuser_pl2 (val) VALUES('麻药');
INSERT INTO crmuser_pl2 (val) VALUES('慢痛');
INSERT INTO crmuser_pl2 (val) VALUES('全部');


-- 角色;
drop table if exists role;
CREATE TABLE role 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO `role` (val) VALUES ('管理员');
INSERT INTO `role` (val) VALUES ('销售经理');
INSERT INTO `role` (val) VALUES ('销售代表');

-- 一级区域;
drop table if exists region_level1_pl;
CREATE TABLE region_level1_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

INSERT INTO region_level1_pl (val) VALUES('北中国');
INSERT INTO region_level1_pl (val) VALUES('南中国');
INSERT INTO region_level1_pl (val) VALUES('东中国');

-- 二级区域;
drop table if exists region_level2_pl;
CREATE TABLE region_level2_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO region_level2_pl (val) VALUES('华北');
INSERT INTO region_level2_pl (val) VALUES('西北');
INSERT INTO region_level2_pl (val) VALUES('东北');
INSERT INTO region_level2_pl (val) VALUES('西南');
INSERT INTO region_level2_pl (val) VALUES('华南');
INSERT INTO region_level2_pl (val) VALUES('华中');
INSERT INTO region_level2_pl (val) VALUES('华东');

-- END of pick list for crmuser

drop table if exists city;
CREATE TABLE city 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    provinceId VARCHAR(255) NOT NULL,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists province;
CREATE TABLE province 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;


--  拜访类型
INSERT INTO activity_types (val) VALUES('拜访');
INSERT INTO activity_types (val) VALUES('外部会议');
INSERT INTO activity_types (val) VALUES('内部会议');


DROP TABLE IF EXISTS `score1_pl`;
CREATE TABLE `score1_pl` (
  `id` int(11) NOT NULL DEFAULT '0',
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `score1_pl` VALUES (0,'0'),(1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6'),(7,'7'),(8,'8'),(9,'9'),(10,'10'),(11,'11'),(12,'12'),(13,'13'),(14,'14'),(15,'15');


DROP TABLE IF EXISTS `score2_pl`;
CREATE TABLE `score2_pl` (
  `id` int(11) NOT NULL DEFAULT '0',
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `score2_pl` VALUES (0,'0'),(1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6'),(7,'7'),(8,'8'),(9,'9'),(10,'10'),(11,'11'),(12,'12'),(13,'13'),(14,'14'),(15,'15'),(16,'16'),(17,'17'),(18,'18'),(19,'19'),(20,'20');


DROP TABLE IF EXISTS `crmuser_activited`;
CREATE TABLE `crmdb`.`crmuser_activited` (
  `id` MEDIUMINT(9) NOT NULL AUTO_INCREMENT,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB;
INSERT INTO `crmdb`.`crmuser_activited` (`id`, `val`) VALUES ('1', '已激活');
INSERT INTO `crmdb`.`crmuser_activited` (`id`, `val`) VALUES ('2', '未激活');


DROP TABLE IF EXISTS `area`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `province`;

DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(20) DEFAULT NULL,
   `externalId` varchar(20) DEFAULT NULL,
  `parentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `area_parentid_cons` FOREIGN KEY (`parentId`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE 
) ENGINE InnoDB;

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(20) DEFAULT NULL,
   `externalId` varchar(20) DEFAULT NULL,
  `parentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
   CONSTRAINT `city_parentid_cons` FOREIGN KEY (`parentId`) REFERENCES `province` (`id`) ON DELETE CASCADE ON UPDATE CASCADE 
) ENGINE InnoDB;

DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(20) DEFAULT NULL,
  `externalId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE InnoDB;


insert into crmuser (id,name) values(-1,"dummy");
insert into account (id,name) values(-1,"dummy");
insert into contact (id,name,accountId) values(-1,"dummy",-1);





-- ramdom update table for testing purpose
UPDATE activity SET contactId = FLOOR(78* RAND()+1);
INSERT INTO contactcrmuser (contactId,crmuserId) VALUES(FLOOR(78* RAND()+1),20);
UPDATE crmuser SET loginName = FLOOR(1000000* RAND()+1000);
UPDATE crmuser SET password = FLOOR(1000000* RAND()+1000);
UPDATE crmuser SET sessionKey = FLOOR(1000000* RAND()+1000);



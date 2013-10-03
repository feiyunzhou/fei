
USE crmdb;

drop table if exists account;
CREATE TABLE account 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT, 
    name VARCHAR(255) NOT NULL,
    bdm_code VARCHAR(255),
    administrativ_level VARCHAR(255),
    grade VARCHAR(255),
    hospital_type MEDIUMINT,
    local_or_army MEDIUMINT,
    comprehensive_or_specialized MEDIUMINT,
    key_type MEDIUMINT,
    status MEDIUMINT,
    duty_officer VARCHAR(255),
    num_of_doctors MEDIUMINT,
    num_of_assistant_doctors MEDIUMINT,
    num_of_staff MEDIUMINT, 
    num_of_treat_per_year MEDIUMINT,
    num_of_outpatient MEDIUMINT,
    total_num_of_sickbed MEDIUMINT,
    num_of_anesthesia_doctor MEDIUMINT,
    num_of_pain_doctor MEDIUMINT, 
    num_of_surgery_per_year MEDIUMINT,
    num_of_surgery_room MEDIUMINT,
    num_of_using_opiates_medicine MEDIUMINT,
    num_of_using_opiates_injection MEDIUMINT,
    date_of_establish DATETIME,
    registered_capital MEDIUMINT,
    tel VARCHAR(255),
    fax VARCHAR(255),
    market_classification MEDIUMINT,
    province VARCHAR(255),
    city VARCHAR(255),
    districts VARCHAR(255),
    address VARCHAR(255),
    owner VARCHAR(255),
    whenadded DATETIME,
    modifier VARCHAR(255),
    modify_datetime DATETIME,
    responsible_person VARCHAR(255),
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists boolean_pl;
CREATE TABLE boolean_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO boolean_pl (val) VALUES('是');
INSERT INTO boolean_pl (val) VALUES('否');

-- 医院类型
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
INSERT INTO account_status_pl (val) VALUES ('有效');
INSERT INTO account_status_pl (val) VALUES('无效');


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


drop table if exists accountcrmuser;
CREATE TABLE accountcrmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    accountId MEDIUMINT NOT NULL,
    crmuserId MEDIUMINT NOT NULL,
    UNIQUE KEY(accountId,crmuserId), 
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists externalMeeting;
CREATE TABLE externalMeeting 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    crmuserId MEDIUMINT NOT NULL,
    endtime BIGINT,
    starttime BIGINT NOT NULL DEFAULT 0,
    title VARCHAR(128),
    contactIds VARCHAR(512),
    status MEDIUMINT,
    activity_type MEDIUMINT,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
ALTER table externalMeeting ADD coachId MEDIUMINT;

drop table if exists internalMeeting;
CREATE TABLE internalMeeting 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    crmuserId MEDIUMINT NOT NULL,
    endtime BIGINT,
    starttime BIGINT NOT NULL DEFAULT 0,
    title VARCHAR(128),
    contactId MEDIUMINT,
    crmusermanagerId MEDIUMINT,
    status MEDIUMINT,
     activity_type MEDIUMINT,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists activity;
CREATE TABLE activity 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    crmuserId MEDIUMINT NOT NULL,
    event_type MEDIUMINT NOT NULL,
    endtime BIGINT,
    starttime BIGINT NOT NULL DEFAULT 0,
    title VARCHAR(128),
    participants VARCHAR(512),
    activity_type MEDIUMINT,
    contactId MEDIUMINT,
    status MEDIUMINT,
    visiting_purpose MEDIUMINT,
    feature_product MEDIUMINT,
    act_endtime DATETIME,
    owner VARCHAR(255),
    whenadded DATETIME,
    modifier VARCHAR(255),
    modify_datetime DATETIME,
    responsible_person VARCHAR(255),
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

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
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO activity_visiting_purpose_pl (val) VALUES('传递产品知识');
INSERT INTO activity_visiting_purpose_pl (val) VALUES('处方观念沟通');
INSERT INTO activity_visiting_purpose_pl (val) VALUES('病例沟通');
INSERT INTO activity_visiting_purpose_pl (val) VALUES('会议安排');
INSERT INTO activity_visiting_purpose_pl (val) VALUES('会议跟进');
INSERT INTO activity_visiting_purpose_pl (val) VALUES('交接工作');
INSERT INTO activity_visiting_purpose_pl (val) VALUES('了解竞争');

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


drop table if exists activitycrmuser;
CREATE TABLE activitycrmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    activityId MEDIUMINT NOT NULL,
    crmuserId MEDIUMINT NOT NULL,
    UNIQUE KEY(activityId,crmuserId), 
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists contact;
CREATE TABLE contact 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    accountId MEDIUMINT  NOT NULL,
    department VARCHAR(255),
    sex MEDIUMINT,
    native_place VARCHAR(255),
    office_tel VARCHAR(255),
    office_fax VARCHAR(255),
    cellphone VARCHAR(255),
    email VARCHAR(255),
    status VARCHAR(255),
    market_classification MEDIUMINT,
    grade MEDIUMINT,
    province VARCHAR(255),
    city VARCHAR(255),
    districts VARCHAR(255),
    duty MEDIUMINT,
    job_title MEDIUMINT,
    visiting_target MEDIUMINT,
    product_target  VARCHAR(255),
    owner VARCHAR(255),
    whenadded DATETIME,
    modifier VARCHAR(255),
    modify_datetime DATETIME,
    responsible_person VARCHAR(255),
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;


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
INSERT INTO contact_department_pl (val) VALUES('内科');
INSERT INTO contact_department_pl (val) VALUES('外科');
INSERT INTO contact_department_pl (val) VALUES('中医科');
INSERT INTO contact_department_pl (val) VALUES('化疗科');
INSERT INTO contact_department_pl (val) VALUES('关怀科');
INSERT INTO contact_department_pl (val) VALUES('牙科');
INSERT INTO contact_department_pl (val) VALUES('急症科');
INSERT INTO contact_department_pl (val) VALUES('骨科');
INSERT INTO contact_department_pl (val) VALUES('肝胆外科');
INSERT INTO contact_department_pl (val) VALUES('血液科');
INSERT INTO contact_department_pl (val) VALUES('风湿科');
INSERT INTO contact_department_pl (val) VALUES('呼吸科');



-- 行政职务;
drop table if exists contact_duty_pl;
CREATE TABLE contact_duty_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_duty_pl (val) VALUES('主任');
INSERT INTO contact_duty_pl (val) VALUES('主治医师');
INSERT INTO contact_duty_pl (val) VALUES('科室主任');
INSERT INTO contact_duty_pl (val) VALUES('采购');
INSERT INTO contact_duty_pl (val) VALUES('科室主任');
INSERT INTO contact_duty_pl (val) VALUES('院长');
INSERT INTO contact_duty_pl (val) VALUES('副院长');
INSERT INTO contact_duty_pl (val) VALUES('药剂科主任');

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
INSERT INTO contact_job_title_pl (val) VALUES('药师');
INSERT INTO contact_job_title_pl (val) VALUES('技师');

-- 状态;
drop table if exists contact_status_pl;
CREATE TABLE contact_status_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_status_pl (val) VALUES('有效');
INSERT INTO contact_status_pl (val) VALUES('无效');

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



drop table if exists contactcrmuser;
CREATE TABLE contactcrmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    contactId MEDIUMINT NOT NULL,
    crmuserId MEDIUMINT NOT NULL,
    UNIQUE KEY(contactId,crmuserId), 
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;


drop table if exists crmuser;
CREATE TABLE crmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255),
    division VARCHAR(255),
    cellPhone VARCHAR(255),
    email VARCHAR(255),
    employeeNumber VARCHAR(255),
    photo VARCHAR(255),
    jobTitle VARCHAR(255),
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
ALTER table crmuser ADD pl1 MEDIUMINT;
ALTER table crmuser ADD pl2 MEDIUMINT;
ALTER table crmuser ADD role MEDIUMINT;
ALTER table crmuser ADD pl4 MEDIUMINT;
ALTER table crmuser ADD pl5 MEDIUMINT;
ALTER table crmuser ADD loginName VARCHAR(255);
ALTER table crmuser ADD password VARCHAR(255);
ALTER table crmuser ADD sessionKey VARCHAR(255);
ALTER table crmuser ADD lastLoginTime BIGINT;
ALTER table crmuser ADD whenadded DATETIME;
ALTER table crmuser ADD reportto MEDIUMINT;


-- pick list for crmuser
-- 状态;
drop table if exists crmuser_pl1;
CREATE TABLE crmuser_pl1 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 产品线;
drop table if exists crmuser_pl2;
CREATE TABLE crmuser_pl2 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 角色;
drop table if exists role;
CREATE TABLE role 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 一级区域;
drop table if exists region_level1_pl;
CREATE TABLE region_level1_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 二级区域;
drop table if exists region_level2_pl;
CREATE TABLE region_level2_pl 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

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



drop table if exists dealerAccount;
CREATE TABLE dealerAccount 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    tele VARCHAR(255),
    status MEDIUMINT,
    pl1 MEDIUMINT,
    pl2 MEDIUMINT,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
ALTER table dealerAccount ADD whenadded DATETIME;


drop table if exists dealerContact;
CREATE TABLE dealerContact 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    sex MEDIUMINT,
    tel_work VARCHAR(255),
    dealerAccountId MEDIUMINT  NOT NULL,
    status MEDIUMINT,
    pl1 MEDIUMINT,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
ALTER table dealerContact ADD whenadded DATETIME;


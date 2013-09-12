
USE crmdb;

--drop table if exists account;
--CREATE TABLE account 
--(
--    id MEDIUMINT NOT NULL AUTO_INCREMENT,
--    name VARCHAR(255) NOT NULL,
--    address VARCHAR(255),
--    cityId MEDIUMINT NOT NULL,
--    classification VARCHAR(255),
--    isKeyAccount boolean,
--    level  VARCHAR(255),
--    photo VARCHAR(255),
--    status VARCHAR(128),
--    tele VARCHAR(255),
--    PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--ALTER table account ADD pl1 MEDIUMINT;
--ALTER table account ADD pl2 MEDIUMINT;
--ALTER table account ADD pl3 MEDIUMINT;
--ALTER table account ADD pl4 MEDIUMINT;
--ALTER table account ADD pl5 MEDIUMINT;
--ALTER table account ADD pl6 MEDIUMINT;
--ALTER table account ADD whenadded DATETIME;


drop table if exists account;
CREATE TABLE account 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    whenadded DATETIME,
    name VARCHAR(255) NOT NULL,
    alias VARCHAR(255),
    medicalType MEDIUMINT,
    headOfHospital VARCHAR(255),
    grade VARCHAR(255),
    isMedicalInsurance VARCHAR(128),
    profitMethod VARCHAR(255),
    isEducational VARCHAR(128),
    superiorHospital VARCHAR(255),
    tel VARCHAR(255),
    fax VARCHAR(255),
    website VARCHAR(255),
    province VARCHAR(255),
    city VARCHAR(255),
    area VARCHAR(255),
    address  VARCHAR(255),
    postcode  VARCHAR(255),
    strongPoint VARCHAR(255),
    medicalCollege VARCHAR(255),
    businessRevenue MEDIUMINT,
    numOfDoctor MEDIUMINT,
    numOfSickbed MEDIUMINT,
    numOfTreatment MEDIUMINT,
    numOfOperation MEDIUMINT,
    medicalTreatmentRevenue MEDIUMINT,
    outpatientRevenue MEDIUMINT,
    outpatientOperationRevenue MEDIUMINT,
    outpatientAssayRevenue MEDIUMINT,
    hospitalizationRevenue MEDIUMINT,
    hospitalizationOperationRevenue MEDIUMINT,
    establishYear MEDIUMINT,
    numOfOperationRoom MEDIUMINT,
    numOfsickroom4Operation MEDIUMINT,
    numOfOutpatientRoom4Operation MEDIUMINT,
    numOfChildbirth MEDIUMINT,
    numOfEmergencyOperation MEDIUMINT,
    numOfOutpatientOperation MEDIUMINT,
    dutyOfficer VARCHAR(255),
    status MEDIUMINT,
    description VARCHAR(255),
    externalModification VARCHAR(255),
    externalCreation VARCHAR(255),
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
drop table if exists account_medicalType_pl;
CREATE TABLE account_medicalType_pl 
(
 id MEDIUMINT NOT NULL  AUTO_INCREMENT,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO account_medicalType_pl (val) VALUES('麻药目标医院');
INSERT INTO account_medicalType_pl (val) VALUES('慢痛目标医院');
INSERT INTO account_medicalType_pl (val) VALUES('其他');

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
INSERT INTO account_status_pl (val) VALUES('终止');
INSERT INTO account_status_pl (val) VALUES('候选');

--
----picklist for account
----状态;
--drop table if exists account_pl1;
--CREATE TABLE account_pl1 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
----医院分级;
--drop table if exists account_pl2;
--CREATE TABLE account_pl2 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
--
----重点医院;
--drop table if exists account_pl3;
--CREATE TABLE account_pl3 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
----市场分类;
--drop table if exists account_pl4;
--CREATE TABLE account_pl4 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
----客户区域;
--drop table if exists account_pl5;
--CREATE TABLE account_pl5 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
----医院类型;
--drop table if exists account_pl6;
--CREATE TABLE account_pl6 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
---- end of piklist for account;

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
    crmuserId MEDIUMINT NOT NULL,
    endtime BIGINT,
    starttime BIGINT NOT NULL DEFAULT 0,
    title VARCHAR(128),
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

ALTER table activity ADD activity_type MEDIUMINT;
ALTER table activity ADD contactId MEDIUMINT;
ALTER table activity ADD status MEDIUMINT;
ALTER table activity ADD whenadded DATETIME;

-- 活动状态;
drop table if exists activity_status;
CREATE TABLE activity_status 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists activity_types;
CREATE TABLE activity_types 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;


--drop table if exists contact;
--CREATE TABLE contact 
--(
--    id MEDIUMINT NOT NULL AUTO_INCREMENT,
--    name VARCHAR(255) NOT NULL,
--    branch VARCHAR(255),
--    department VARCHAR(255),
--    duty VARCHAR(255),
--    gender VARCHAR(255),
--    mobilephone VARCHAR(255),
--    tel_work VARCHAR(255),
--    title VARCHAR(255),
--    accountId MEDIUMINT  NOT NULL,
--    PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--ALTER TABLE contact ADD sex MEDIUMINT;
--ALTER table contact ADD pl1 MEDIUMINT;
--ALTER table contact ADD pl2 MEDIUMINT;
--ALTER table contact ADD pl3 MEDIUMINT;
--ALTER table contact ADD pl4 MEDIUMINT;
--ALTER table contact ADD pl5 MEDIUMINT;
--ALTER table contact ADD pl6 MEDIUMINT;
--ALTER table contact ADD visiting_target MEDIUMINT;
--ALTER table contact ADD whenadded DATETIME;

-- picklist for contact;
-- 性别;
--drop table if exists sex_pl;
--CREATE TABLE sex_pl
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
---- 科室;
--drop table if exists contact_pl1;
--CREATE TABLE contact_pl1 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
---- 职务;
--drop table if exists contact_pl2;
--CREATE TABLE contact_pl2 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
---- 职称;
--drop table if exists contact_pl3;
--CREATE TABLE contact_pl3 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
---- 主要产品;
--drop table if exists contact_pl4;
--CREATE TABLE contact_pl4 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
---- 状态;
--drop table if exists contact_pl5;
--CREATE TABLE contact_pl5 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
--
---- 医生级别
--drop table if exists contact_pl6;
--CREATE TABLE contact_pl6 
--(
--  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
--  val VARCHAR(255),
--  PRIMARY KEY USING BTREE (id)
--) ENGINE InnoDB;
-- end of picklist for contact;


drop table if exists contact;
CREATE TABLE contact 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    whenadded DATETIME,
    name VARCHAR(255) NOT NULL,
    accountId MEDIUMINT  NOT NULL,
    department VARCHAR(255),
    sex MEDIUMINT,
    birthYear MEDIUMINT,
    birthMonth MEDIUMINT,
    status VARCHAR(255),
    tel VARCHAR(255),
    fax VARCHAR(255),
    mobile VARCHAR(255),
    email VARCHAR(255),
    validAddress VARCHAR(255),
    otherTel VARCHAR(255),
    administrativePosition MEDIUMINT,
    technicalPosition MEDIUMINT,
    medicalAssociationPosition VARCHAR(255),
    highestDegree VARCHAR(255),
    tutorQualification VARCHAR(255),
    academicSpecialties VARCHAR(255),
    operationSpecialties VARCHAR(255),
    numOfOperationPerYear MEDIUMINT,
    numOfsickbed MEDIUMINT,
    languageLevelOfEnglish VARCHAR(255),
    JNIfaulty VARCHAR(255),
    researchDirection VARCHAR(255),
    instuctorLevel VARCHAR(255),
    kol VARCHAR(255),
    grade MEDIUMINT,
    factorOfGrade MEDIUMINT,
    managerId MEDIUMINT,
    visiting_target MEDIUMINT,
    description VARCHAR(255),
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
drop table if exists contact_pl1;
CREATE TABLE contact_pl1 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
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

-- 行政职务;
drop table if exists contact_pl2;
CREATE TABLE contact_pl2 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_pl2 (val) VALUES('主任');
INSERT INTO contact_pl2 (val) VALUES('主治医师');
INSERT INTO contact_pl2 (val) VALUES('科室主任');
INSERT INTO contact_pl2 (val) VALUES('采购');
INSERT INTO contact_pl2 (val) VALUES('科室主任');
INSERT INTO contact_pl2 (val) VALUES('院长');
INSERT INTO contact_pl2 (val) VALUES('副院长');
INSERT INTO contact_pl2 (val) VALUES('药剂科主任');

-- 专业职务;
drop table if exists contact_pl3;
CREATE TABLE contact_pl3 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_pl3 (val) VALUES('住院医师');
INSERT INTO contact_pl3 (val) VALUES('主治医师');
INSERT INTO contact_pl3 (val) VALUES('副主任医师');
INSERT INTO contact_pl3 (val) VALUES('主任医师');
INSERT INTO contact_pl3 (val) VALUES('护士');
INSERT INTO contact_pl3 (val) VALUES('主管护师');
INSERT INTO contact_pl3 (val) VALUES('药师');
INSERT INTO contact_pl3 (val) VALUES('技师');

-- 状态;
drop table if exists contact_pl5;
CREATE TABLE contact_pl5 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_pl5 (val) VALUES('有效');
INSERT INTO contact_pl5 (val) VALUES('无效');

-- 级别;
drop table if exists contact_pl6;
CREATE TABLE contact_pl6 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO contact_pl6 (val) VALUES('A');
INSERT INTO contact_pl6 (val) VALUES('B');
INSERT INTO contact_pl6 (val) VALUES('C');
INSERT INTO contact_pl6 (val) VALUES('D');




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


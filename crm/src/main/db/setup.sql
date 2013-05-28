
USE crmdb;

drop table if exists account;
CREATE TABLE account 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    cityId MEDIUMINT NOT NULL,
    classification VARCHAR(255),
    isKeyAccount boolean,
    level  VARCHAR(255),
    photo VARCHAR(255),
    status VARCHAR(128),
    tele VARCHAR(255),
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
ALTER table account ADD pl1 MEDIUMINT;
ALTER table account ADD pl2 MEDIUMINT;
ALTER table account ADD pl3 MEDIUMINT;
ALTER table account ADD pl4 MEDIUMINT;
ALTER table account ADD pl5 MEDIUMINT;
ALTER table account ADD pl6 MEDIUMINT;

--picklist for account
--状态;
drop table if exists account_pl1;
CREATE TABLE account_pl1 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

--医院分级;
drop table if exists account_pl2;
CREATE TABLE account_pl2 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;


--重点医院;
drop table if exists account_pl3;
CREATE TABLE account_pl3 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

--市场分类;
drop table if exists account_pl4;
CREATE TABLE account_pl4 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

--客户区域;
drop table if exists account_pl5;
CREATE TABLE account_pl5 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

--医院类型;
drop table if exists account_pl6;
CREATE TABLE account_pl6 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- end of piklist for account;

drop table if exists accountcrmuser;
CREATE TABLE accountcrmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    accountId MEDIUMINT NOT NULL,
    crmuserId MEDIUMINT NOT NULL,
    UNIQUE KEY(accountId,crmuserId), 
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

drop table if exists activity_types;
CREATE TABLE activity_types 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;


drop table if exists contact;
CREATE TABLE contact 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    branch VARCHAR(255),
    department VARCHAR(255),
    duty VARCHAR(255),
    gender VARCHAR(255),
    mobilephone VARCHAR(255),
    tel_work VARCHAR(255),
    title VARCHAR(255),
    accountId MEDIUMINT  NOT NULL,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
ALTER TABLE contact ADD sex MEDIUMINT;
ALTER table contact ADD pl1 MEDIUMINT;
ALTER table contact ADD pl2 MEDIUMINT;
ALTER table contact ADD pl3 MEDIUMINT;
ALTER table contact ADD pl4 MEDIUMINT;
ALTER table contact ADD pl5 MEDIUMINT;
ALTER table contact ADD pl6 MEDIUMINT;
ALTER table contact ADD visiting_target MEDIUMINT;

-- picklist for contact;
-- 性别;
drop table if exists sex_pl;
CREATE TABLE sex_pl
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 科室;
drop table if exists contact_pl1;
CREATE TABLE contact_pl1 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 职务;
drop table if exists contact_pl2;
CREATE TABLE contact_pl2 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 职称;
drop table if exists contact_pl3;
CREATE TABLE contact_pl3 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 主要产品;
drop table if exists contact_pl4;
CREATE TABLE contact_pl4 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 状态;
drop table if exists contact_pl5;
CREATE TABLE contact_pl5 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

-- 医生级别
drop table if exists contact_pl6;
CREATE TABLE contact_pl6 
(
  id MEDIUMINT NOT NULL  AUTO_INCREMENT,
  val VARCHAR(255),
  PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
-- end of picklist for contact;

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
    cityId MEDIUMINT  NOT NULL,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
ALTER table crmuser ADD pl1 MEDIUMINT;
ALTER table crmuser ADD pl2 MEDIUMINT;
ALTER table crmuser ADD role MEDIUMINT;
ALTER table crmuser ADD pl4 MEDIUMINT;
ALTER table crmuser ADD pl5 MEDIUMINT;

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



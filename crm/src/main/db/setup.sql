USE crmdb;

drop table if exists accountcrmuser;
drop table if exists activitycrmuser;
drop table if exists contactcrmuser;
drop table if exists activity;
drop table if exists contact;
drop table if exists crmuser;
drop table if exists account;

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
    province int(11),
    city int(11),
    districts VARCHAR(255),
    address VARCHAR(255),
    owner VARCHAR(255),
    whenadded DATETIME,
    modifier VARCHAR(255),
    modify_datetime DATETIME,
    responsible_person VARCHAR(255),
    UNIQUE INDEX `account_ix_01` (`bdm_code` ASC),
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists crmuser;
CREATE TABLE crmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    code varchar(255),
    reportto MEDIUMINT,
    role MEDIUMINT,
    pl1 MEDIUMINT,
    pl2 MEDIUMINT,
    pl4 MEDIUMINT, 
    pl5 MEDIUMINT,
    city VARCHAR(255),
    department VARCHAR(255),
    whenadded DATETIME,
    modifier VARCHAR(255),
    modify_datetime date,
    owner VARCHAR(255),
    level MEDIUMINT,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;

drop table if exists accountcrmuser;
CREATE TABLE accountcrmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    accountId MEDIUMINT NOT NULL,
    crmuserId MEDIUMINT NOT NULL,
    UNIQUE KEY(accountId,crmuserId), 
    PRIMARY KEY USING BTREE (id),
    CONSTRAINT `account_crmuser_cons` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `account_crmuser_cons2` FOREIGN KEY (`crmuserId`) REFERENCES `crmuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
    province int(11),
    city int(11),
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
    PRIMARY KEY USING BTREE (id),
    CONSTRAINT `account_id_cons` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE 
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
    coacheeId int(32),
    status MEDIUMINT,
    visiting_purpose MEDIUMINT,
    feature_product MEDIUMINT,
    act_endtime DATETIME,
    owner VARCHAR(255),
    whenadded DATETIME,
    modifier VARCHAR(255),
    modify_datetime DATETIME,
    responsible_person VARCHAR(255),
    coach int(32),
    location VARCHAR(255),
    total_score MEDIUMINT,
    planing MEDIUMINT,
    openling MEDIUMINT,
    enquery_listening MEDIUMINT,
    deliverable MEDIUMINT,
    objection_handing MEDIUMINT,
    summary MEDIUMINT,
    PRIMARY KEY USING BTREE (id),
    CONSTRAINT `crmuserId_activity_cons` FOREIGN KEY (`crmuserId`) REFERENCES `crmuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `contactId_activity_cons` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE InnoDB;

drop table if exists activitycrmuser;
CREATE TABLE activitycrmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    activityId MEDIUMINT NOT NULL,
    crmuserId MEDIUMINT NOT NULL,
    UNIQUE KEY(activityId,crmuserId), 
    PRIMARY KEY USING BTREE (id),
    CONSTRAINT `crm_activity_cons` FOREIGN KEY (`crmuserId`) REFERENCES `crmuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT `crm_activity_cons2` FOREIGN KEY (`activityId`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE 
) ENGINE InnoDB;


drop table if exists contactcrmuser;
CREATE TABLE contactcrmuser 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    contactId MEDIUMINT NOT NULL,
    crmuserId MEDIUMINT NOT NULL,
    UNIQUE KEY(contactId,crmuserId), 
    PRIMARY KEY USING BTREE (id),
     CONSTRAINT `crm_contact_cons1` FOREIGN KEY (`crmuserId`) REFERENCES `crmuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT `crm_contact_cons2` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE InnoDB;



drop table if exists user_position;
create table user_position (
    id mediumint(9) auto_increment,
    userId int(32),
    positionId int(32),
    primary key (id),
    unique index user_position_unique (userId,positionId)
)ENGINE InnoDB;

DROP TABLE IF EXISTS `userInfo`;
CREATE TABLE `userInfo` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `division` varchar(255) DEFAULT NULL,
  `cellPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employeeNumber` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `jobTitle` varchar(255) DEFAULT NULL,
  `pl1` mediumint(9) DEFAULT NULL,
  `pl2` mediumint(9) DEFAULT NULL,
  `role` mediumint(9) DEFAULT NULL,
  `pl4` mediumint(9) DEFAULT NULL,
  `pl5` mediumint(9) DEFAULT NULL,
  `sex` mediumint(9) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sessionKey` varchar(255) DEFAULT NULL,
  `lastLoginTime` bigint(20) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  `parcel` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modify_datetime` datetime DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `isActivited` mediumint(9) DEFAULT NULL,
  `ts` bigint(20) DEFAULT NULL,
  `positionId` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
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






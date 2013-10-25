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
ALTER table account modify province int(11);
ALTER table account modify city int(11);

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
    coach VARCHAR(255),
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
    PRIMARY KEY USING BTREE (id),
    CONSTRAINT `account_id_cons` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE 
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
    pl1 MEDIUMINT,
    pl2 MEDIUMINT,
    role MEDIUMINT,
    pl4 MEDIUMINT,
    pl5 MEDIUMINT,
    sex MEDIUMINT,
    loginName VARCHAR(255),
    password VARCHAR(255),
    sessionKey VARCHAR(255),
    lastLoginTime BIGINT,
    whenadded DATETIME,
    reportto MEDIUMINT,
    parcel VARCHAR(255),
    modifier VARCHAR(255),
    modify_datetime date,
    owner VARCHAR(255),
    postId VARCHAR(255),
    city VARCHAR(255),
    isActivited BOOL,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;


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



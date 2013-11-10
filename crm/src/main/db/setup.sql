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
insert into crmuser (id,name,code,reportto,role) values
(-1,'无','BJ',0,-1),
(1,'管理员','BJ231011001',-1,1),
(2,'北区地区经理01','BJ131011001',1,2),
(3,'北区代表001','BJ131001001',2,3);



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

DROP TABLE IF EXISTS userInfo;
CREATE TABLE userInfo (
  id mediumint(9) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  department varchar(255) DEFAULT NULL,
  division varchar(255) DEFAULT NULL,
  cellPhone varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  employeeNumber varchar(255) DEFAULT NULL,
  photo varchar(255) DEFAULT NULL,
  jobTitle varchar(255) DEFAULT NULL,
  pl1 mediumint(9) DEFAULT NULL,
  pl2 mediumint(9) DEFAULT NULL,
  role mediumint(9) DEFAULT NULL,
  pl4 mediumint(9) DEFAULT NULL,
  pl5 mediumint(9) DEFAULT NULL,
  sex mediumint(9) DEFAULT NULL,
  loginName varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  sessionKey varchar(255) DEFAULT NULL,
  lastLoginTime bigint(20) DEFAULT NULL,
  whenadded datetime DEFAULT NULL,
  parcel varchar(255) DEFAULT NULL,
  modifier varchar(255) DEFAULT NULL,
  modify_datetime datetime DEFAULT NULL,
  owner varchar(255) DEFAULT NULL,
  province int(11),
  city int(11),
  isActivited mediumint(9) DEFAULT NULL,
  ts bigint(20) DEFAULT NULL,
  positionId mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE InnoDB;

INSERT INTO `userInfo` (id,name,loginname,password,role,isActivited,ts,positionId,pl1) VALUES 
(-1,'无','dummy','827ccb0eea8a706c4c34a16891f84e7b',1,2,1386766666,-1,1),
(1,'Admin Nam','admin','827ccb0eea8a706c4c34a16891f84e7b',1,2,1386766666,1,1),
(2,'Sales Manager','salesman','827ccb0eea8a706c4c34a16891f84e7b',2,2,1386766666,2,1),
(3,'Sales','sales','827ccb0eea8a706c4c34a16891f84e7b',3,2,1386766666,3,1);


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


DROP TABLE IF EXISTS `data_exchange_teample`;
DROP TABLE IF EXISTS `data_exchange_operation`;
DROP TABLE IF EXISTS data_exchange_status;
DROP TABLE IF EXISTS `record_type`;

DROP TABLE IF EXISTS `record_type`;
CREATE TABLE `record_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `val` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `val` (`val`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `record_type` VALUES (1,0,'Account'),(2,0,'Contact'),(3,0,'Call'),(4,0,'Coaching'),(5,0,'AccuntTeam'),(6,0,'ContactTeam');

DROP TABLE IF EXISTS data_exchange_status;
CREATE TABLE data_exchange_status (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  val varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `val` (`val`)
) ENGINE=InnoDB;
INSERT INTO `data_exchange_status` VALUES (1,0,'Pending'),(2,0,'Abort'),(3,0,'Completed'),(4,0,'Cancelled');

DROP TABLE IF EXISTS `data_exchange_operation`;
CREATE TABLE `data_exchange_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `val` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `val` (`val`)
) ENGINE=InnoDB;
INSERT INTO `data_exchange_operation` VALUES (1,0,'Import'),(2,0,'Export');


DROP TABLE IF EXISTS `data_exchange_teample`;
CREATE TABLE `data_exchange_teample` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `operation_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  `template` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK8AE35445D557144C` (`type_id`),
  KEY `FK8AE35445199BCB6C` (`operation_id`),
  CONSTRAINT `FK8AE35445199BCB6C` FOREIGN KEY (`operation_id`) REFERENCES `data_exchange_operation` (`id`),
  CONSTRAINT `FK8AE35445D557144C` FOREIGN KEY (`type_id`) REFERENCES `record_type` (`id`)
) ENGINE=InnoDB;

INSERT INTO `data_exchange_teample` VALUES (1,1,'Account Full Import Template 1.0',1,1,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n<Configuration>\r\n    <BatchSize>1000</BatchSize>\r\n    <EntityName>Account</EntityName>\r\n    <ExternalId>BdmCode</ExternalId>\r\n    <FileName>account.csv</FileName>\r\n    <Database>crm_mysql</Database>\r\n    <Fields>\r\n        <ColumnName>记录行ID</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>BdmCode</FieldName>\r\n    </Fields>\r\n    \r\n    <Fields>\r\n        <ColumnName>年诊疗人数</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfTreatPerYear</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>成立时间</ColumnName>\r\n        <DataType>Date</DataType>\r\n        <FieldName>DateOfEstablish</FieldName>\r\n        <Format>m/d/yy</Format>\r\n    </Fields>\r\n<!--  <Format>yyyy-MM-dd</Format> -->    \r\n\r\n    <Fields>\r\n        <ColumnName>客户名称</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>Name</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>医院行政级别</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>AdministrativLevel</FieldName>\r\n        <LookupEntityName>AccountAdministrativLevelPl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>医院分级</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>Grade</FieldName>\r\n        <LookupEntityName>AccountGradePl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>医院类型</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>HospitalType</FieldName>\r\n        <LookupEntityName>AccountTypePl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>    \r\n\r\n    <Fields>\r\n        <ColumnName>地方军队医院</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>LocalOrArmy</FieldName>\r\n        <LookupEntityName>AccountLocalOrArmyPl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>    \r\n\r\n    <Fields>\r\n        <ColumnName>综合专科医院</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>ComprehensiveOrSpecialized</FieldName>\r\n        <LookupEntityName>AccountComprehensiveOrSpecializedPl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>    \r\n\r\n    <Fields>\r\n        <ColumnName>重点医院</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>KeyType</FieldName>\r\n        <LookupEntityName>AccountPointPl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>    \r\n\r\n    <Fields>\r\n        <ColumnName>状态</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>Status</FieldName>\r\n        <LookupEntityName>AccountStatusPl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>    \r\n\r\n    <Fields>\r\n        <ColumnName>法人</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>DutyOfficer</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>医生数量</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfDoctors</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>助理医师人数</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfAssistantDoctors</FieldName>\r\n    </Fields>\r\n    \r\n    <Fields>\r\n        <ColumnName>员工总数</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfStaff</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>门诊人数</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfOutpatient</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>总病床数</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>TotalNumOfSickbed</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>麻醉科医生数</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfAnesthesiaDoctor</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>疼痛学组医生数</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfPainDoctor</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>手术量年</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfSurgeryPerYear</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>手术间</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfSurgeryRoom</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>阿片类用药量</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfUsingOpiatesMedicine</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>阿片类注射剂量</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>NumOfUsingOpiatesInjection</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>注册资金</ColumnName>\r\n        <DataType>Integer</DataType>\r\n        <FieldName>RegisteredCapital</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>主要电话号码</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>Tel</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>主要传真号码</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>Fax</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>市场分类</ColumnName>\r\n        <DataType>Lookup</DataType>\r\n        <FieldName>MarketClassification</FieldName>\r\n        <LookupEntityName>AccountMarketClassificationPl</LookupEntityName>\r\n        <LookupFieldName>Val</LookupFieldName>\r\n        <TargetFieldName>Id</TargetFieldName>\r\n    </Fields>    \r\n\r\n    <Fields>\r\n        <ColumnName>医院省份</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>Province</FieldName>\r\n    </Fields>\r\n\r\n    <Fields>\r\n        <ColumnName>医院区县</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>Districts</FieldName>\r\n    </Fields>\r\n    \r\n    <Fields>\r\n        <ColumnName>医院详细地址</ColumnName>\r\n        <DataType>String</DataType>\r\n        <FieldName>Address</FieldName>\r\n    </Fields>\r\n</Configuration>');






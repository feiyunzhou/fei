drop table if exists importMetaInfo;
CREATE TABLE importMetaInfo 
(
    id MEDIUMINT NOT NULL AUTO_INCREMENT, 
    name VARCHAR(255) NOT NULL,
    entity_name VARCHAR(255),
    importfilename VARCHAR(255),
    logfilename VARCHAR(255),
    whenadded DATETIME,
    modifier VARCHAR(255),
    modify_datetime DATETIME,
    num_of_total_record MEDIUMINT,
    num_of_imported MEDIUMINT,
    num_of_failed MEDIUMINT, 
    status MEDIUMINT,
    result MEDIUMINT,
    PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;



DROP TABLE IF EXISTS `importstatus`;
CREATE TABLE `crmdb`.`importstatus` (
  `id` INT NOT NULL,
  `val` VARCHAR(45),
  PRIMARY KEY (`id`)
) ENGINE InnoDB;
INSERT INTO importstatus (id,val) VALUES(0,'运行');
INSERT INTO importstatus (id,val) VALUES(1,'已完成');

CREATE TABLE `crmdb`.`importresult` (
  `id` INT NOT NULL,
  `val` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE InnoDB;
INSERT INTO importresult (id,val) VALUES(0,'成功');
INSERT INTO importresult (id,val) VALUES(1,'失败');


ALTER table user_position add externalId varchar(128);
ALTER table user_position add whenadded DATETIME;
ALTER table user_position add modifier VARCHAR(255);
ALTER table user_position add modify_datetime DATETIME;


ALTER TABLE user_position drop KEY user_position_unique;  
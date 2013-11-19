use crmdb;
ALTER TABLE `crmdb`.`account` 
CHANGE COLUMN `administrativ_level` `administrativ_level` MEDIUMINT(9) NULL DEFAULT NULL ,
CHANGE COLUMN `grade` `grade` MEDIUMINT(9) NULL DEFAULT NULL ;

ALTER TABLE `crmdb`.`contact` 
CHANGE COLUMN `department` `department` MEDIUMINT(9) NULL ;

ALTER TABLE `crmdb`.`crmuser` 
CHANGE COLUMN `city` `city` MEDIUMINT(9) NULL DEFAULT NULL ;

ALTER TABLE `crmdb`.`accountcrmuser` 
ADD COLUMN `externalId` VARCHAR(45) NULL AFTER `crmuserId`;
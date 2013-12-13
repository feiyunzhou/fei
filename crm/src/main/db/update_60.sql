ALTER TABLE `crmdb`.`activity` 
CHANGE COLUMN `endtime` `endtime` DATETIME NULL ,
CHANGE COLUMN `starttime` `starttime` DATETIME NOT NULL ;
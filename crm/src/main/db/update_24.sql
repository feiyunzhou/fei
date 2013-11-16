use crmdb;
ALTER TABLE `crmdb`.`userinfo` 
ADD COLUMN `num_of_signIn` MEDIUMINT(9) NULL AFTER `office_tel`;

ALTER TABLE `crmdb`.`user_position` 
ADD COLUMN `status` INT NULL DEFAULT NULL AFTER `positionId`,
ADD COLUMN `createtime` DATETIME NULL DEFAULT NULL AFTER `status`,
ADD COLUMN `isPrimary` INT NULL DEFAULT NULL AFTER `createtime`;
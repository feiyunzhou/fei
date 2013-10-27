
ALTER TABLE `crmdb`.`activity` 
CHANGE COLUMN `coach` `coach` MEDIUMINT(9) NULL DEFAULT NULL ;
ALTER TABLE `crmdb`.`activity` 
ADD COLUMN `coacheeId` MEDIUMINT(9) NULL AFTER `contactId`;

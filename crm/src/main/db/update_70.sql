ALTER TABLE `crmdb`.`alertattachment` 
CHANGE COLUMN `id` `id` MEDIUMINT(9) NOT NULL AUTO_INCREMENT ,
ADD COLUMN `modifier` VARCHAR(255) NULL AFTER `modify_datetime`;
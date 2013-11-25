ALTER TABLE `crmdb`.`role` 
ADD COLUMN `name` VARCHAR(45) NULL AFTER `val`;
UPDATE `crmdb`.`role` SET `name`='全部' WHERE `id`='1';
UPDATE `crmdb`.`role` SET `name`='只读' WHERE `id`='2';
UPDATE `crmdb`.`role` SET `name`='只读' WHERE `id`='3';

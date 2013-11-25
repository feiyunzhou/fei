UPDATE `crmdb`.`activity_status_pl` SET `val`='未执行' WHERE `id`='3';

ALTER TABLE `crmdb`.`activity` 
ADD COLUMN `activity_daypart` MEDIUMINT(9) NULL AFTER `whether_coach`;

CREATE TABLE `crmdb`.`activity_daypart_pl` (
  `id` MEDIUMINT(9) NOT NULL AUTO_INCREMENT,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `crmdb`.`activity_daypart_pl` (`id`, `val`) VALUES ('1', '上午');
INSERT INTO `crmdb`.`activity_daypart_pl` (`id`, `val`) VALUES ('2', '下午');

-- ALTER TABLE `crmdb`.`activitycrmuser` 
-- DROP FOREIGN KEY `crm_activity_cons2`;

DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='2';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='3';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='4';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='6';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='7';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='8';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='10';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='11';
DELETE FROM `crmdb`.`account_administrativ_level_pl` WHERE `id`='12';

UPDATE `crmdb`.`account_administrativ_level_pl` SET `val`='未评级' WHERE `id`= '13';

drop table `crmdb`.`account_grade_pl`;

ALTER TABLE `crmdb`.`account` 
DROP COLUMN `grade`;


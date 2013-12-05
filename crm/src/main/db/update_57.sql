ALTER TABLE `crmdb`.`activity` 
ADD COLUMN `activity_coachType` MEDIUMINT(9) NULL AFTER `activity_daypart`;

DROP TABLE IF EXISTS `crmdb`.`activity_coachtype_pl`;
CREATE TABLE `crmdb`.`activity_coachtype_pl` (
  `id` MEDIUMINT(9) NOT NULL,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
  
INSERT INTO `crmdb`.`activity_coachtype_pl` (`id`, `val`) VALUES ('1', '拜访辅导');
INSERT INTO `crmdb`.`activity_coachtype_pl` (`id`, `val`) VALUES ('2', '科室会辅导');
DELETE FROM `crmdb`.`activity_event_type_pl` WHERE `id`='3';
UPDATE `crmdb`.`activity_event_type_pl` SET `val`='辅导' WHERE `id`='2';
use crmdb;
UPDATE `crmdb`.`activity_whethercoach_pl` SET `val`='协访半天' WHERE `id`='2';
UPDATE `crmdb`.`activity_whethercoach_pl` SET `val`='协访一天' WHERE `id`='3';
UPDATE `crmdb`.`crmuser_activited` SET `id`='0', `val`='未激活' WHERE `id`='1';
UPDATE `crmdb`.`crmuser_activited` SET `id`='1', `val`='已激活' WHERE `id`='2';
DELETE FROM `crmdb`.`activity_whethercoach_pl` WHERE `id`='1';
UPDATE `crmdb`.`activity_whethercoach_pl` SET `id`='1' WHERE `id`='2';
UPDATE `crmdb`.`activity_whethercoach_pl` SET `id`='2' WHERE `id`='3';
UPDATE `crmdb`.`userinfo` SET `email`='tigerzhou@rexen.com.cn', `sex`='1', `isActivited`='1' WHERE `id`='1';
UPDATE `crmdb`.`userinfo` SET `email`='alexsong@rexen.com.cn', `sex`='1', `isActivited`='1' WHERE `id`='2';
UPDATE `crmdb`.`userinfo` SET `email`='feiyunzhou@rexen.com.cn', `sex`='1', `isActivited`='1' WHERE `id`='3';
UPDATE `crmdb`.`userinfo` SET `sex`='1', `isActivited`='1' WHERE `id`='-1';
UPDATE `crmdb`.`userinfo` SET `city`='1' WHERE `id`='1';
UPDATE `crmdb`.`userinfo` SET `city`='1' WHERE `id`='2';
UPDATE `crmdb`.`userinfo` SET `city`='1' WHERE `id`='3';
UPDATE `crmdb`.`userinfo` SET `province`='1' WHERE `id`='1';
UPDATE `crmdb`.`userinfo` SET `province`='1' WHERE `id`='2';
UPDATE `crmdb`.`userinfo` SET `province`='1' WHERE `id`='3';

ALTER TABLE `crmdb`.`activity` 
ADD COLUMN `coachTime` MEDIUMINT(9) NULL AFTER `whetherCoach`;

ALTER TABLE `crmdb`.`contact` 
ADD COLUMN `num_of_monthlySurgery` MEDIUMINT(9) NULL AFTER `contactCode`,
ADD COLUMN `num_of_monthlyAnalgesia` MEDIUMINT(9) NULL AFTER `num_of_monthlySurgery`;



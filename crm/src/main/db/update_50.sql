DELETE FROM `crmdb`.`activity_coachtime_pl` WHERE `id`='1';

DROP TABLE `crmdb`.`activity_whethercoach_pl`

UPDATE `crmdb`.`city` SET `val`='菏泽' WHERE `id`='151';
UPDATE `crmdb`.`city` SET `val`='重庆' WHERE `id`='235';
DELETE FROM `crmdb`.`city` WHERE `id`='236';
INSERT INTO `crmdb`.`city` (`id`, `val`, `parentId`) VALUES ('345', '未知地域', '31');
INSERT INTO `crmdb`.`city` (`id`, `val`, `parentId`) VALUES ('346', '未知地域', '27');
INSERT INTO `crmdb`.`city` (`id`, `val`, `parentId`) VALUES ('347', '三河（虚拟）', '1');

UPDATE `crmdb`.`region_level2_pl` SET `val`='奥诺美北区' WHERE `id`='1';
UPDATE `crmdb`.`region_level2_pl` SET `val`='奥诺美东北区' WHERE `id`='2';
UPDATE `crmdb`.`region_level2_pl` SET `val`='奥诺美东一区' WHERE `id`='3';
UPDATE `crmdb`.`region_level2_pl` SET `val`='奥诺美东二区' WHERE `id`='4';
UPDATE `crmdb`.`region_level2_pl` SET `val`='奥诺美西区' WHERE `id`='5';
UPDATE `crmdb`.`region_level2_pl` SET `val`='奥诺美南区' WHERE `id`='6';
DELETE FROM `crmdb`.`region_level2_pl` WHERE `id`='7';

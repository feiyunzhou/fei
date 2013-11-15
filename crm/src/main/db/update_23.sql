use crmdb;
DROP TABLE IF EXISTS `activity_coachtime_pl`;
CREATE TABLE `crmdb`.`activity_coachtime_pl` (
  `id` INT NOT NULL,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
  
INSERT INTO `crmdb`.`activity_coachtime_pl` (`id`, `val`) VALUES ('1', '无');
INSERT INTO `crmdb`.`activity_coachtime_pl` (`id`, `val`) VALUES ('2', '半天');
INSERT INTO `crmdb`.`activity_coachtime_pl` (`id`, `val`) VALUES ('3', '全天');
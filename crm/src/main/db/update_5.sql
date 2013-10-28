use crmdb;
ALTER TABLE `crmdb`.`crmuser` 
CHANGE COLUMN `isActivited` `isActivited` MEDIUMINT(9) NULL DEFAULT NULL ;

DROP TABLE IF EXISTS `crmuser_activited`;
CREATE TABLE `crmdb`.`crmuser_activited` (
  `id` MEDIUMINT(9) NOT NULL AUTO_INCREMENT,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
  
INSERT INTO `crmdb`.`crmuser_activited` (`id`, `val`) VALUES ('1', '已激活');
INSERT INTO `crmdb`.`crmuser_activited` (`id`, `val`) VALUES ('2', '未激活');

ALTER TABLE `crmdb`.`account`
ADD UNIQUE INDEX `account_ix_01` (`bdm_code` ASC);
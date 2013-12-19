ALTER TABLE `crmdb`.`alert` 
ADD COLUMN `attachment` MEDIUMINT(9) NULL AFTER `alertRegion`;

CREATE TABLE `crmdb`.`alertattachment` (
  `id` MEDIUMINT(9) NOT NULL,
  `fileName` VARCHAR(255) NULL,
  `whenadded` DATETIME NULL,
  `owner` VARCHAR(255) NULL,
  `alertId` MEDIUMINT(9) NULL,
  `modify_datetime` DATETIME NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
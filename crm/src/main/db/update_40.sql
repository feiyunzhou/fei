drop table if exists crmuser_level_pl;
CREATE TABLE `crmdb`.`crmuser_level_pl` (
  `id` INT NOT NULL,
  `val` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  INSERT INTO `crmdb`.`crmuser_level_pl` (`id`, `val`) VALUES ('11', '11');
INSERT INTO `crmdb`.`crmuser_level_pl` (`id`, `val`) VALUES ('21', '21');
INSERT INTO `crmdb`.`crmuser_level_pl` (`id`, `val`) VALUES ('31', '31');
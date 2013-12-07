DROP TABLE IF EXISTS `crmdb`.`data_audit`;
CREATE TABLE `crmdb`.`data_audit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `entity_name` VARCHAR(45) NOT NULL,
  `record_id` INT NOT NULL,
  `modify_time` DATE NOT NULL,
  `modifier` VARCHAR(45) NOT NULL,
  `column_name` VARCHAR(45) NOT NULL,
  `old_value` VARCHAR(45) ,
  `new_value` VARCHAR(45) ,
  PRIMARY KEY (`id`));
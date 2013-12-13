CREATE TABLE `crmdb`.`accountcrmuser_relation_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `position_id` INT NOT NULL,
  `account_id` INT NOT NULL,
  `modify_time` DATE NOT NULL,
  `modifier` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `crmdb`.`userposition_relation_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `position_id` INT NOT NULL,
  `userinfo_id` INT NOT NULL,
  `modify_time` DATE NOT NULL,
  `modifier` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

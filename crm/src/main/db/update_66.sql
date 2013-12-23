ALTER TABLE `crmdb`.`userposition_relation_history` 
CHANGE COLUMN `userinfo_id` `user_id` INT(11) NOT NULL ,
CHANGE COLUMN `modify_time` `modify_time` DATETIME NOT NULL ;


ALTER TABLE `crmdb`.`accountcrmuser_relation_history` 
CHANGE COLUMN `modify_time` `modify_time` DATETIME NOT NULL ;


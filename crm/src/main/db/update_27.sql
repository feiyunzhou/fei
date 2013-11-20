ALTER TABLE `crmdb`.`product` 
ADD COLUMN `productLine` VARCHAR(45) NULL AFTER `modfied_by_user_id`,
ADD INDEX `productLine_idx` (`productLine` ASC);
ALTER TABLE `crmdb`.`product` 
ADD CONSTRAINT `productLine`
  FOREIGN KEY (`productLine`)
  REFERENCES `crmdb`.`crmuser_pl2` (`val`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

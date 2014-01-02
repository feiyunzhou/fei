ALTER TABLE `crmdb`.`contact` 
ADD COLUMN `initializationdepartment` VARCHAR(128) NULL AFTER `externalId`;
UPDATE `crmdb`.`contact_grade_pl` SET `val`='VIP' WHERE `id`='4';
			`
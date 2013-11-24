ALTER TABLE `crmdb`.`userinfo` 
DROP COLUMN `ts`,
DROP COLUMN `isActivited`,
ADD UNIQUE INDEX `loginName_UNIQUE` (`loginName` ASC);


alter table importMetaInfo add num_of_updated int;


ALTER table accountcrmuser add externalId varchar(128);
ALTER table accountcrmuser add whenadded DATETIME;
ALTER table accountcrmuser add modifier VARCHAR(255);
ALTER table accountcrmuser add modify_datetime DATETIME;
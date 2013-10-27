
ALTER table crmuser ADD ts BIGINT;
UPDATE `crmdb`.`crmuser` SET `email`='brenda.yuan@rexen.com.cn', `isActivited`='1', `ts`='1382611111318' WHERE `id`='101';
UPDATE `crmdb`.`crmuser` SET `isActivited`='1', `ts`='1382615122318' WHERE `id`='99';
UPDATE `crmdb`.`crmuser` SET `isActivited`='1', `ts`='1382613388598' WHERE `id`='100';


DROP TABLE IF EXISTS `score1_pl`;
CREATE TABLE `score1_pl` (
  `id` int(11) NOT NULL DEFAULT '0',
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `score1_pl` VALUES (0,'0'),(1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6'),(7,'7'),(8,'8'),(9,'9'),(10,'10'),(11,'11'),(12,'12'),(13,'13'),(14,'14'),(15,'15');


DROP TABLE IF EXISTS `score2_pl`;
CREATE TABLE `score2_pl` (
  `id` int(11) NOT NULL DEFAULT '0',
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `score2_pl` VALUES (0,'0'),(1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6'),(7,'7'),(8,'8'),(9,'9'),(10,'10'),(11,'11'),(12,'12'),(13,'13'),(14,'14'),(15,'15'),(16,'16'),(17,'17'),(18,'18'),(19,'19'),(20,'20');
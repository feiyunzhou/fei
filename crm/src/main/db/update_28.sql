DROP TABLE IF EXISTS `alert`;
CREATE TABLE `crmdb`.`alert` (
  `id` MEDIUMINT(9) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `publishDate` BIGINT(20) NULL,
  `expired` BIGINT(20) NULL,
  `priority` MEDIUMINT(9) NULL,
  `towhom` MEDIUMINT(9) NULL,
  `description` LONGTEXT NULL,
  `owner` VARCHAR(255) NULL,
  `whenadded` DATETIME NULL,
  `modifier` VARCHAR(255) NULL,
  `modify_datetime` DATETIME NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
  
DROP TABLE IF EXISTS `alert_priority_pl`;
 CREATE TABLE `crmdb`.`alert_priority_pl` (
 `id` MEDIUMINT(9) NOT NULL,
 `val` VARCHAR(255) NULL,
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `alert_publicobject_pl`;
CREATE TABLE `crmdb`.`alert_publicobject_pl` (
  `id` MEDIUMINT(9) NOT NULL,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
  
INSERT INTO `crmdb`.`alert_priority_pl` (`id`, `val`) VALUES ('1', '高级');
INSERT INTO `crmdb`.`alert_priority_pl` (`id`, `val`) VALUES ('2', '中级');
INSERT INTO `crmdb`.`alert_priority_pl` (`id`, `val`) VALUES ('3', '低级');

INSERT INTO `crmdb`.`alert_publicobject_pl` (`id`, `val`) VALUES ('1', '管理员');
INSERT INTO `crmdb`.`alert_publicobject_pl` (`id`, `val`) VALUES ('2', '主管');
INSERT INTO `crmdb`.`alert_publicobject_pl` (`id`, `val`) VALUES ('3', '代表');

create view crmdb.activity_alert as
SELECT id,
case when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))=0 THEN '今天'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))=1 THEN '昨天'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))=2 THEN '前天'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))>=3 and to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))<7  THEN '三天以前'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))>=7 THEN '七天以前'
ELSE '错误' END as time,
title
FROM crmdb.activity act where event_type=1 and status=1 order by time


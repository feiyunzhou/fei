



DROP TABLE IF EXISTS `userInfo`;
CREATE TABLE `userInfo` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `division` varchar(255) DEFAULT NULL,
  `cellPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employeeNumber` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `jobTitle` varchar(255) DEFAULT NULL,
  `pl1` mediumint(9) DEFAULT NULL,
  `pl2` mediumint(9) DEFAULT NULL,
  `role` mediumint(9) DEFAULT NULL,
  `pl4` mediumint(9) DEFAULT NULL,
  `pl5` mediumint(9) DEFAULT NULL,
  `sex` mediumint(9) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sessionKey` varchar(255) DEFAULT NULL,
  `lastLoginTime` bigint(20) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  `reportto` mediumint(9) DEFAULT NULL,
  `parcel` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modify_datetime` datetime DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `postId` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `isActivited` mediumint(9) DEFAULT NULL,
  `ts` bigint(20) DEFAULT NULL,
  `positionId` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

INSERT INTO `userInfo` VALUES (-1,'dummy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'827ccb0eea8a706c4c34a16891f84e7b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,-1),(99,'Admin Name',NULL,'医疗设备部',NULL,'admin@qq.com',NULL,'','职员',1,1,1,1,1,1,'admin','827ccb0eea8a706c4c34a16891f84e7b','53856',NULL,NULL,-1,NULL,'Admin Name','2013-10-20 23:28:00',NULL,NULL,'1',1,1382615122318,99),(100,'sales rep',NULL,NULL,NULL,'sales@qq.com',NULL,NULL,NULL,1,1,3,1,1,1,'sales','827ccb0eea8a706c4c34a16891f84e7b',NULL,NULL,'2013-10-14 18:58:00',101,NULL,'李99','2013-10-19 19:02:00','李99',NULL,NULL,1,1382613388598,100),(101,'sales manager1',NULL,NULL,NULL,'2632783@qq.com',NULL,NULL,NULL,1,1,2,1,1,1,'salesman','827ccb0eea8a706c4c34a16891f84e7b',NULL,NULL,'2013-10-14 18:58:00',-1,NULL,'Admin Name','2013-10-26 12:20:00','李99',NULL,NULL,1,1382611111318,101);

ALTER table crmuser ADD level MEDIUMINT;
ALTER table crmuser ADD position_code varchar(255);
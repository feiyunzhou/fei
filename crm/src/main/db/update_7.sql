



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

INSERT INTO `userInfo` (id,name,loginname,password,role,isActivited,ts,positionId) VALUES 
(-1,'无','dummy','827ccb0eea8a706c4c34a16891f84e7b',1,2,1386766666,-1),
(1,'Admin Nam','admin','827ccb0eea8a706c4c34a16891f84e7b',1,2,1386766666,1),
(2,'Sales Manager','salesman','827ccb0eea8a706c4c34a16891f84e7b',2,2,1386766666,2),
(3,'Sales','sales','827ccb0eea8a706c4c34a16891f84e7b',3,2,1386766666,3);

insert into crmuser (id,name,code,reportto,role) values
(-1,'无','BJ',0,-1),
(1,'管理员','BJ231011001',-1,1),
(2,'北区地区经理01','BJ131011001',1,2),
(3,'北区代表001','BJ131001001',2,3);



ALTER table crmuser ADD level MEDIUMINT;
ALTER table crmuser ADD position_code varchar(255);





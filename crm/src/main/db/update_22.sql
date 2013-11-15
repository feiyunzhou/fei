use crmdb;
UPDATE `crmdb`.`crmuser_pl2` SET `id`='3', `val`='奥诺美' WHERE `id`='3';
INSERT INTO `crmdb`.`crmuser_pl2` (`id`, `val`) VALUES ('4', '全部');
UPDATE `crmdb`.`activity_visiting_purpose_pl` SET `activity_type`='1', `parentId`='1' WHERE `id`='3';

ALTER TABLE `crmdb`.`activity` 
ADD COLUMN `accountId` MEDIUMINT(9) NULL AFTER `coachTime`,
ADD COLUMN `department` MEDIUMINT(9) NULL AFTER `accountId`;
ADD COLUMN `whether_coach` VARCHAR(255) NULL AFTER `department`;

CREATE TABLE `crmdb`.`activity_score3_pl` (
  `id` INT NOT NULL,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
CREATE TABLE `crmdb`.`activity_score4_pl` (
  `id` INT NOT NULL,
  `val` VARCHAR(25) NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `crmdb`.`activity_score5_pl` (
`id` INT NOT NULL,
`val` VARCHAR(255) NULL,
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
  
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('0', '0');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('1', '1');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('2', '2');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('3', '3');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('4', '4');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('5', '5');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('6', '6');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('7', '7');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('8', '8');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('9', '9');
INSERT INTO `crmdb`.`activity_score3_pl` (`id`, `val`) VALUES ('10', '10'); 
  
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('0', '0');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('1', '1');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('2', '2');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('3', '3');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('4', '4');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('5', '5');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('6', '6');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('7', '7');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('8', '8');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('9', '9');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('10', '10');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('11', '11');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('12', '12');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('13', '13');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('14', '14');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('15', '15');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('16', '16');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('17', '17');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('18', '18');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('19', '19');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('20', '20');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('21', '21');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('22', '22');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('23', '23');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('24', '24');
INSERT INTO `crmdb`.`activity_score4_pl` (`id`, `val`) VALUES ('25', '25');  

INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('0', '0');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('1', '1');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('2', '2');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('3', '3');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('4', '4');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('5', '5');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('6', '6');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('7', '7');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('8', '8');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('9', '9');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('10', '10');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('11', '11');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('12', '12');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('13', '13');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('14', '14');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('15', '15');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('16', '16');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('17', '17');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('18', '18');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('19', '19');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('20', '20');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('21', '21');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('22', '22');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('23', '23');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('24', '24');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('25', '25');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('26', '26');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('27', '27');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('28', '28');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('29', '29');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('30', '30');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('31', '31');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('32', '32');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('33', '33');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('34', '34');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('35', '35');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('36', '36');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('37', '37');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('38', '38');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('39', '39');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('40', '40');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('41', '41');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('42', '42');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('43', '43');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('44', '44');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('45', '45');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('46', '46');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('47', '47');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('48', '48');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('49', '49');
INSERT INTO `crmdb`.`activity_score5_pl` (`id`, `val`) VALUES ('50', '50');
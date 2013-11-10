use crmdb;
ALTER TABLE `crmdb`.`contact` 
ADD COLUMN `contactCode` VARCHAR(255) NULL AFTER `responsible_person`,
ADD UNIQUE INDEX `contactCode_UNIQUE` (`contactCode` ASC);

INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('14', '保健科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('15', '采购科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('16', '传染科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('17', '创伤外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('18', '儿科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('19', '耳鼻喉科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('20', '方便门诊');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('21', '放化疗科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('22', '放疗科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('23', '放射科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('24', '妇产科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('25', '妇科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('26', '感染科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('27', '肛肠科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('28', '姑息科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('29', '国际医疗科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('30', '核医学科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('31', '呼吸内科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('32', '护理科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('33', '急诊科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('34', '介入科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('35', '康复科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('36', '口腔科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('37', '老干科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('38', '麻醉科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('39', '泌尿外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('40', '脑外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('41', '内分泌科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('42', '宁养科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('43', '皮肤科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('44', '普外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('45', '其他');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('46', '乳腺外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('47', '烧伤整形科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('48', '神经科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('49', '神经内科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('50', '神经外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('51', '肾内科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('52', '生物治疗科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('53', '特需科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('54', '疼痛科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('55', '微创科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('56', '胃肠外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('57', '消化科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('58', '消化内科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('59', '消化外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('60', '心内科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('61', '心胸外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('62', '血管外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('63', '血透科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('64', '药剂科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('65', '医保办');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('66', '医务科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('67', '整形美容科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('68', '质控科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('69', '中西医结合科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('70', '肿瘤科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('71', '肿瘤内科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('72', '肿瘤外科');
INSERT INTO `crmdb`.`contact_department_pl` (`id`, `val`) VALUES ('73', '综合科');


INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('9', '医生');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('10', '副主任');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('11', '院长助理');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('12', '书记');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('13', '副书记');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('14', '护士');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('15', '护士长');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('16', '库管');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('17', '采购科科长');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('18', '药师');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('19', '药剂科副主任');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('20', '医务科科长');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('21', '医务科副科长');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('22', '医保办主任');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('23', '科教科主任');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('24', '信息卡科长');
INSERT INTO `crmdb`.`contact_duty_pl` (`id`, `val`) VALUES ('25', '其他');

UPDATE `crmdb`.`contact_job_title_pl` SET `val`='主管护士' WHERE `id`='6';
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('9', '副主任护士');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('10', '主任护师');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('11', '主管药师');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('12', '副主任药师');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('13', '主任药师');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('14', '主管技师');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('15', '副主任技师');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('16', '主任技师');
INSERT INTO `crmdb`.`contact_job_title_pl` (`id`, `val`) VALUES ('17', '其他');


ALTER TABLE `crmdb`.`userinfo` 
ADD COLUMN `office_tel` VARCHAR(255) NULL AFTER `positionId`;

DROP TABLE IF EXISTS `activity_whethercoach_pl`;
CREATE TABLE `crmdb`.`activity_whethercoach_pl` (
  `id` MEDIUMINT(9) NOT NULL,
  `val` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `crmdb`.`activity_whethercoach_pl` (`id`, `val`) VALUES ('1', '否');
INSERT INTO `crmdb`.`activity_whethercoach_pl` (`id`, `val`) VALUES ('2', '协防半天');
INSERT INTO `crmdb`.`activity_whethercoach_pl` (`id`, `val`) VALUES ('3', '协防一天');

UPDATE `crmdb`.`activity_event_type_pl` SET `val`='拜访辅导' WHERE `id`='2';
INSERT INTO `crmdb`.`activity_event_type_pl` (`id`, `val`) VALUES ('3', '科室会辅导');

ALTER TABLE `crmdb`.`activity` 
ADD COLUMN `whetherCoach` MEDIUMINT(9) NULL AFTER `summary`;
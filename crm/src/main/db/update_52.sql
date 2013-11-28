UPDATE `crmdb`.`alert_publicobject_pl` SET `val`='系统管理员' WHERE `id`='1';
UPDATE `crmdb`.`alert_publicobject_pl` SET `val`='地区经理' WHERE `id`='2';
UPDATE `crmdb`.`alert_publicobject_pl` SET `val`='销售代表' WHERE `id`='3';

DELETE FROM `crmdb`.`contact_grade_pl` WHERE `id`='4';
UPDATE `crmdb`.`contact_grade_pl` SET `id`='4' WHERE `id`='5';
UPDATE `crmdb`.`contact_grade_pl` SET `id`='5' WHERE `id`='6';


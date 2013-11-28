DELETE FROM `crmdb`.`crmuser_pl2` WHERE `id`='4';
ALTER TABLE `crmdb`.`product`
CHANGE COLUMN `description` `specification` VARCHAR(512) NULL DEFAULT NULL ,
CHANGE COLUMN `productLine` `level` INT(11) NULL DEFAULT NULL ;
INSERT INTO `crmdb`.`alert_publicobject_pl` (`id`, `val`) VALUES ('4', '所有人');

drop view if exists crmdb.call_export;
create view crmdb.call_export as
SELECT act.id as id,act.title as Activity_title,acp.val as Activity_whetherCoach,afpp.val as Activity_feature_product,at.val as Activity_types,act.whenadded as Activity_addTime,act.modify_datetime as Activity_modifyTime,from_unixtime(act.starttime/1000) as Activity_start,from_unixtime(act.endtime/1000) as Activity_end,
u.name as Position_name,rlp1.val as Region_level_1,rlp2.val as Region_level_2,u.code as position_code,r.val as position_role,
c.name as Contact_name,cgp.val as Contact_grade,cdp.val as Contact_department,sp.val as Contact_sex,c.email as Contact_email,cdp2.val as Contact_duty,cjtp.val as Contact_job_title,
a.name as Account_name , a.bdm_code as Account_BDMCode,atl.val as Account_type,aalp.val as Account_administrative_level,aloap.val as local_or_army,acosp.val as comprehensive_or_specialized,app.val as isKey,asp.val as Account_status
FROM crmdb.activity act
left join crmdb.activity_coachtime_pl acp on
act.whetherCoach=acp.id
left join crmdb.activity_feature_product_pl afpp on
act.feature_product=afpp.id
left join crmdb.activity_types at on
act.activity_type=at.id
left join crmdb.crmuser u on
act.crmuserId = u.id
left join crmdb.contact c on
act.contactId = c.id
left join crmdb.contact_grade_pl cgp on
c.grade=cgp.id
left join crmdb.contact_department_pl cdp on
c.department=cdp.id
left join crmdb.sex_pl sp on
c.sex=sp.id
left join crmdb.contact_duty_pl cdp2 on
c.duty=cdp2.id
left join crmdb.contact_job_title_pl cjtp on
c.job_title=cjtp.id
left join crmdb.region_level1_pl rlp1 on
u.pl5=rlp1.id
left join crmdb.region_level2_pl rlp2 on
u.pl4=rlp2.id
left join crmdb.role r on
u.role=r.id
left join crmdb.account a on
c.accountId= a.id
left join crmdb.account_type_pl atl on
a.hospital_type = atl.id
left join crmdb.account_administrativ_level_pl aalp on
a.administrativ_level=aalp.id
left join crmdb.account_local_or_army_pl aloap on
a.local_or_army=aloap.id
left join crmdb.account_comprehensive_or_specialized_pl acosp on
a.comprehensive_or_specialized=acosp.id
left join crmdb.account_point_pl app on
a.key_type=app.id
left join crmdb.account_status_pl asp on
a.status=asp.id order by id
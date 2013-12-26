drop view if exists activity_alert;
create view activity_alert as 

select `act`.`id` AS `id`,
(case when ((to_days(curdate()) - to_days(date_format(`act`.`starttime`,'%Y-%m-%d'))) = 0) then '今天'
when ((to_days(curdate()) - to_days(date_format(`act`.`starttime`,'%Y-%m-%d'))) = 1) then '昨天' 
when ((to_days(curdate()) - to_days(date_format(`act`.`starttime`,'%Y-%m-%d'))) = 2) then '前天' 
when (((to_days(curdate()) - to_days(date_format(`act`.`starttime`,'%Y-%m-%d'))) >= 3) and ((to_days(curdate()) - to_days(date_format(`act`.`starttime`,'%Y-%m-%d'))) < 7)) then '三天以前' 
when ((to_days(curdate()) - to_days(date_format(`act`.`starttime`,'%Y-%m-%d'))) >= 7) then '七天以前'
 else '错误' end) AS `time`,
`act`.`title` AS `title`,`act`.`owner` AS `name`,`act`.`starttime` AS `starttime` ,act.endtime as endtime,
c.name as contact,avpp.val as purpose,act.whenadded as addtime,pl.val as product,acp.val as coachtype
from `activity` `act`
left join contact c on
c.id=act.contactId
left join activity_visiting_purpose_pl avpp on
act.visiting_purpose=avpp.id
left join productline pl on
pl.id=act.feature_product
left join activity_coachtype_pl acp on
acp.id=act.activity_coachType
 where ((`act`.`status` = 1) and ((to_days(curdate()) - to_days(date_format(`act`.`starttime`,'%Y-%m-%d'))) >= 0)) order by `starttime` desc;

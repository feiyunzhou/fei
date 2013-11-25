<<<<<<< HEAD
use crmdb;


set session old_alter_table =on;  
ALTER IGNORE TABLE user_position ADD UNIQUE (positionId);
set session old_alter_table =off; 
=======
drop view  if exists activity_alert;
create view activity_alert as
SELECT id,act.owner as name,
case when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))=0 THEN '今天'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))=1 THEN '昨天'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))=2 THEN '前天'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))>=3 and to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))<7  THEN '三天以前'
when to_days(curdate())-to_days(DATE_FORMAT(from_unixtime(act.starttime/1000),'%Y-%m-%d'))>=7 THEN '七天以前'
ELSE '错误' END as time,
title
FROM crmdb.activity act where event_type=1 and status=1 order by time;

drop view if exists crmdb.user_position_account;
create view  crmdb.user_position_account as
select ac.id as id,u.name as userName,u.loginName as loginName,u.email as email,sp.val as sex,u.whenadded as addTime,
c.name as positionName,c.code as positionCode,(select name from crmdb.crmuser crm where crm.id=c.reportto) as reportTo,(select name from crmdb.crmuser c1 where c1.id =(select c2.reportto from crmdb.crmuser c2 where id=c.reportto)) as reporttto,r.val as role,rlp4.val as regoin1,rlp5.val as regoin2,ct.val as city,
a.name as accountName,a.bdm_code as BDMCode,aalp.val as adminLevel,asp.val as status
from crmdb.userinfo u
left join crmdb.sex_pl sp
on u.sex=sp.id
left join crmdb.user_position up
on u.id=up.userId
left join crmdb.crmuser c
on up.positionId =c.id
left join crmdb.role r
on c.role=r.id
left join crmdb.region_level1_pl rlp4
on c.pl4=rlp4.id
left join crmdb.region_level1_pl rlp5
on c.pl5=rlp5.id
left join crmdb.city ct
on c.city=ct.id
left join crmdb.accountcrmuser ac
on c.id=ac.crmuserId
left join crmdb.account a
on ac.accountId=a.id
left join crmdb.account_status_pl asp
on a.status =asp.id
left join crmdb.account_administrativ_level_pl aalp on
a.administrativ_level=aalp.id
where up.status=1 order by id
>>>>>>> update activity time

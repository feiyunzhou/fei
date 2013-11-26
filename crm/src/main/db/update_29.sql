use crmdb;
drop view if exists crmdb.user_position_account;
create view user_position_account as
select ac.id as id,u.name as userName,u.loginName as loginName,u.email as email,sp.val as sex,u.whenadded as addTime,
c.name as positionName,c.code as positionCode,(select name from crmuser crm where crm.id=c.reportto) as reportTo,(select name from crmuser c1 where c1.id =(select c2.reportto from crmuser c2 where id=c.reportto)) as reporttto,r.val as role,rlp4.val as regoin1,rlp5.val as regoin2,ct.val as city,
a.name as accountName,a.bdm_code as BDMCode,aalp.val as adminLevel,asp.val as status
from userinfo u
left join sex_pl sp
on u.sex=sp.id
left join user_position up
on u.id=up.userId
left join crmuser c
on up.positionId =c.id
left join role r
on c.role=r.id
left join region_level1_pl rlp4
on c.pl4=rlp4.id
left join region_level1_pl rlp5
on c.pl5=rlp5.id
left join city ct
on c.city=ct.id
left join accountcrmuser ac
on c.id=ac.crmuserId
left join account a
on ac.accountId=a.id
left join account_status_pl asp
on a.status =asp.id
left join account_administrativ_level_pl aalp on
a.administrativ_level=aalp.id
where up.status=1 order by id;

ALTER TABLE `crmdb`.`userinfo` 
DROP COLUMN `positionId`;

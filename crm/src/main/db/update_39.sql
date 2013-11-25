create view parent_user_info as select * from userinfo;
create view parent_user_position as select * from user_position;
create view parent_position as select * from crmuser;

drop view user_position_query;

create view user_position_query as 
select user_position.userid user_id, 
       user_position.positionid position_id,
	   region_level1_pl.val area,
	   region_level2_pl.val  region,
	   province.val  province,
	   crmuser_pl2.val  productLine,
       crmuser.level position_level,
	   crmuser.name position_name,
       crmuser.code position_code,
       userinfo.employeenumber delegatePosition,
       userinfo.name delegateName,
	   parent_position.id manager_position_id,
       parent_position.code managerPosition,
       parent_position.name manager_position_name,
	   parent_position.level manager_position_level,
       parent_user_info.employeenumber manager_employee_number,
       parent_user_info.name regionManager,
	   account.name accountName,
	   account.bdm_code BDMcode
from user_position
left join crmuser on user_position.positionid = crmuser.id
left join userinfo on user_position.userid = userinfo.id
left join parent_position on crmuser.reportto = parent_position.id
left join parent_user_position on parent_position.id = parent_user_position.positionid
left join parent_user_info on parent_user_position.userid = parent_user_info.id
inner join accountcrmuser on user_position.positionid = accountcrmuser.crmuserid
inner join account on accountcrmuser.accountid = account.id
left join region_level1_pl on crmuser.pl4 = region_level1_pl.id
left join region_level2_pl on crmuser.pl5 = region_level2_pl.id
left join province on account.province = province.id
left join crmuser_pl2 on crmuser_pl2.id = crmuser.pl2

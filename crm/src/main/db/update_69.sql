drop view if exists user_position_account;
create view user_position_account as
select `ac`.`id` AS `id`,`u`.`name` AS `userName`,`u`.`loginName` AS `loginName`,`u`.`email` AS `email`,`sp`.`val` AS `sex`,`u`.`whenadded` AS `addTime`,`c`.`name` AS `positionName`,`c`.`code` AS `positionCode`,
(select `crm`.`name` from `crmuser` `crm` where (`crm`.`id` = `c`.`reportto`)) AS `reportTo`,
(select u.name from userinfo u left join user_position iup on iup.userId=u.id left join crmuser ic on ic.id=iup.positionId where ic.name=(select `crm`.`name` from `crmuser` `crm` where (`crm`.`id` = `c`.`reportto`))) as DSMName,
(select `c1`.`name` from `crmuser` `c1` where (`c1`.`id` = (select `c2`.`reportto` from `crmuser` `c2` where (`c2`.`id` = `c`.`reportto`)))) AS `reporttto`,
(select u.name from userinfo u left join user_position iup on iup.userId=u.id left join crmuser ic on ic.id=iup.positionId where ic.name=(select `c1`.`name` from `crmuser` `c1` where (`c1`.`id` = (select `c2`.`reportto` from `crmuser` `c2` where (`c2`.`id` = `c`.`reportto`))))) as RSMName,
`r`.`val` AS `role`,`rlp4`.`val` AS `regoin1`,`rlp5`.`val` AS `regoin2`,`ct`.`val` AS `city`,`a`.`name` AS `accountName`,`a`.`bdm_code` AS `BDMCode`,`aalp`.`val` AS `adminLevel`,`asp`.`val` AS `status` from (((((((((((`userinfo` `u`
left join `sex_pl` `sp` on((`u`.`sex` = `sp`.`id`))) 
left join `user_position` `up` on((`u`.`id` = `up`.`userId`))) 
left join `crmuser` `c` on((`up`.`positionId` = `c`.`id`))) 
left join `role` `r` on((`c`.`role` = `r`.`id`))) 
left join `region_level1_pl` `rlp4` on((`c`.`pl4` = `rlp4`.`id`)))
left join `region_level1_pl` `rlp5` on((`c`.`pl5` = `rlp5`.`id`))) 
left join `city` `ct` on((`c`.`city` = `ct`.`id`)))
 left join `accountcrmuser` `ac` on((`c`.`id` = `ac`.`crmuserId`))) 
left join `account` `a` on((`ac`.`accountId` = `a`.`id`))) 
left join `account_status_pl` `asp` on((`a`.`status` = `asp`.`id`))) 
left join `account_administrativ_level_pl` `aalp` on((`a`.`administrativ_level` = `aalp`.`id`))) 
where up.status = 1 and c.role>2 and ac.id>0 order by `ac`.`id`;

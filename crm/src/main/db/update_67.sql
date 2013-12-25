drop VIEW if exists user_position_query; 
USE `crmdb`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `crm`@`localhost` 
    SQL SECURITY DEFINER
VIEW `user_position_query` AS
    select 
        `accountcrmuser`.`id` AS `id`,
        `user_position`.`userId` AS `user_id`,
        `user_position`.`positionId` AS `position_id`,
        `region_level1_pl`.`val` AS `area`,
        `region_level2_pl`.`val` AS `region`,
        `province`.`val` AS `province`,
        `productline`.`val` AS `productLine`,
        `crmuser`.`level` AS `position_level`,
        `crmuser`.`name` AS `position_name`,
        `crmuser`.`code` AS `position_code`,
        `userinfo`.`employeeNumber` AS `delegatePosition`,
        `userinfo`.`name` AS `delegateName`,
        `parent_position`.`id` AS `manager_position_id`,
        `parent_position`.`code` AS `managerPosition`,
        `parent_position`.`name` AS `manager_position_name`,
        `parent_position`.`level` AS `manager_position_level`,
        `parent_user_info`.`employeeNumber` AS `manager_employee_number`,
        `parent_user_info`.`name` AS `regionManager`,
        `account`.`name` AS `accountName`,
        `account`.`bdm_code` AS `BDMcode`
    from
        (((((((((((`user_position`
        left join `crmuser` ON ((`user_position`.`positionId` = `crmuser`.`id`)))
        left join `userinfo` ON ((`user_position`.`userId` = `userinfo`.`id`)))
        left join `parent_position` ON ((`crmuser`.`reportto` = `parent_position`.`id`)))
        left join `parent_user_position` ON ((`parent_position`.`id` = `parent_user_position`.`positionId`)))
        left join `parent_user_info` ON ((`parent_user_position`.`userId` = `parent_user_info`.`id`)))
        join `accountcrmuser` ON ((`user_position`.`positionId` = `accountcrmuser`.`crmuserId`)))
        join `account` ON ((`accountcrmuser`.`accountId` = `account`.`id`)))
        left join `region_level1_pl` ON ((`crmuser`.`pl4` = `region_level1_pl`.`id`)))
        left join `region_level2_pl` ON ((`crmuser`.`pl5` = `region_level2_pl`.`id`)))
        left join `province` ON ((`account`.`province` = `province`.`id`)))
        left join `productline` ON ((`productline`.`id` = `crmuser`.`pl2`)));

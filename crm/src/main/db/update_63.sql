drop view if exists crmdb.activity_alert;
create view crmdb.activity_alert as
select
id,
case
when to_days(curdate())-to_days(DATE_FORMAT(act.starttime,'%Y-%m-%d'))=0 THEN '今天'
when to_days(curdate())-to_days(DATE_FORMAT(act.starttime,'%Y-%m-%d'))=1 THEN '昨天'
when to_days(curdate())-to_days(DATE_FORMAT(act.starttime,'%Y-%m-%d'))=2 THEN '前天'
when to_days(curdate())-to_days(DATE_FORMAT(act.starttime,'%Y-%m-%d'))>=3 and to_days(curdate())-to_days(DATE_FORMAT(act.starttime,'%Y-%m-%d'))<7  THEN '三天以前'
when to_days(curdate())-to_days(DATE_FORMAT(act.starttime,'%Y-%m-%d'))>=7 THEN '七天以前'
ELSE '错误' END as time,
title,
act.owner as name,
act.starttime as times
from activity act where status=1 and to_days(curdate())-to_days(DATE_FORMAT(act.starttime,'%Y-%m-%d'))>=0 order by times
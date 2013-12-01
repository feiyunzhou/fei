use crmdb;

drop table if exists activity_coachtime_pl;
CREATE TABLE activity_coachtime_pl 
(
 id MEDIUMINT NOT NULL,
 val VARCHAR(255),
 PRIMARY KEY USING BTREE (id)
) ENGINE InnoDB;
INSERT INTO `crmdb`.`activity_coachtime_pl` (`id`, `val`) VALUES ('1', '半天');
INSERT INTO `crmdb`.`activity_coachtime_pl` (`id`, `val`) VALUES ('2', '全天');


ALTER table accountcrmuser add owner VARCHAR(255);
ALTER table user_position add owner VARCHAR(255);
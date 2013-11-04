
drop table if exists user_position;
create table user_position (
id int(64) auto_increment,
userId int(64),
positionId int(64),
primary key (id),
unique index user_position_unique (userId,positionId)
)ENGINE InnoDB;
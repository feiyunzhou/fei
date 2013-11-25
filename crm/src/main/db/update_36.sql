
use crmdb;


set session old_alter_table =on;  
ALTER IGNORE TABLE user_position ADD UNIQUE (positionId);
set session old_alter_table =off; 

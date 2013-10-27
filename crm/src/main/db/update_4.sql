use crmdb;

drop trigger if exists activity_score_insert;
DELIMITER |

CREATE TRIGGER activity_score_insert BEFORE INSERT ON activity
  FOR EACH ROW BEGIN
    SET NEW.total_score = (NEW.planing + NEW.openling + NEW.enquery_listening + NEW.deliverable + NEW.objection_handing + NEW.summary);
  END;
|

drop trigger if exists activity_score_update;
CREATE TRIGGER activity_score_update BEFORE UPDATE ON activity
  FOR EACH ROW BEGIN
    SET NEW.total_score = (NEW.planing + NEW.openling + NEW.enquery_listening + NEW.deliverable + NEW.objection_handing + NEW.summary);
  END;
|

DELIMITER ;
use crmdb;

-- ALTER table activity_visiting_purpose_pl change activity_type parentId mediumint(9);
ALTER table activity_visiting_purpose_pl add parentId mediumint(9);
update activity_visiting_purpose_pl set parentId = activity_type;
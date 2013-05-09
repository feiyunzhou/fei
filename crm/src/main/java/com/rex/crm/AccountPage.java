package com.rex.crm;

import java.util.List;
import java.util.Map;

import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.Entity;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class AccountPage extends TemplatePage
{
	/**
	 * Constructor
	 */
	public AccountPage()
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        String userId = "20";
        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
		add(new PageableTablePanel("datalist",entity,mapList));
		
	}
}
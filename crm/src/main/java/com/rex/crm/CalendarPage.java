package com.rex.crm;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.protocol.http.WebApplication;

import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.CalendarPanel;
import com.rex.crm.common.Entity;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class CalendarPage extends TemplatePage
{
	/**
	 * Constructor
	 */
	public CalendarPage()
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        
        final String userId = ((SignIn2Session)getSession()).getUserId();
        //List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
		//add(new TableDataPanel("datalist",entity,mapList));
        add(new CalendarPanel("datalist"));
        
		
	}

	 
	

    
}
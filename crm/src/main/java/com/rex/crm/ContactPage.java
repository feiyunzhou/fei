package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.Entity;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class ContactPage extends TemplatePage
{
	/**
	 * Constructor
	 */
	public ContactPage()
	{
//		Map<String, Entity> entities = Configuration.getEntityTable();
//        Entity entity = entities.get("contact");
//        setPageTitle(entity.getDisplay());
//        List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
//		add(new PageableTablePanel("datalist",entity,mapList));
	    
	    initPage(null);
		
	}
	
    public ContactPage(Map<String,Boolean> map){
        initPage(map);
    }
    
    private void initPage(Map<String,Boolean> filter){
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("contact");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        final String userId = ((SignIn2Session)getSession()).getUserId();
        List mapList = null;
        if(filter == null){
            mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
        }else{
            
            List<String> ft = Lists.newArrayList();
            for (String k : filter.keySet()) {
                if(filter.get(k)) ft.add(k);
            }
            mapList = DAOImpl.queryEntityWithFilter(entity.getSql(), entity.getFilterField(), ft, userId);
                    
                    
        }
        add(new PageableTablePanel("datalist",entity,mapList));

        
        //for the side bar
        List<Pair<String, Map<String, Object>>> types = DAOImpl.queryFilters(entity.getSql(), entity.getFilterField(), entity.getFieldByName(entity.getFilterField()).getPicklist(), userId);
        add(new FilterPanel("filterPanel",types ,filter,ContactPage.class));
       
    }
}
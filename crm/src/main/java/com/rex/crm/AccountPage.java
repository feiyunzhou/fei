package com.rex.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.common.Entity;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class AccountPage extends TemplatePage
{
    
    public AccountPage(Map<String,Boolean> map){
        initPage(map);
    }
    
    private void initPage(Map<String,Boolean> filter){
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        String userId = "20";
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
        add(new FilterPanel("filterPanel",types ,filter,AccountPage.class));
       
    }
    
	/**
	 * Constructor
	 */
	public AccountPage()
	{
       initPage(null);
		
	}
}
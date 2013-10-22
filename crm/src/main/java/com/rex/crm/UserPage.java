package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class UserPage extends TemplatePage
{
    private String search_target = "";
	/**
	 * Constructor
	 */
    public UserPage(){
        initPage(null,null);
    }
    
    public UserPage(final Map<String,Boolean> filter,List tdata){
        initPage(filter,tdata);
    }
	public void initPage(final Map<String,Boolean> filter,List tdata)
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("crmuser");
        setPageTitle(entity.getDisplay());
        
        Form form = new Form("form"){

            @Override
            protected void onSubmit() {
                String sql = entity.getSql();
                
                
                search_target = (search_target==null || search_target.equalsIgnoreCase("*"))? "":search_target;
               
                //sql =  sql + " AND name like '%"+search_target+"%'";
                List<Field> searchableFields = entity.getSearchableFields();
                String joint = " like '%"+search_target+"%'";
                String likequery = "";
                for(Field sf:searchableFields){
                    likequery = likequery + " OR "+ sf.getName() + joint;
                }
                
                sql =  sql + " where name like '%"+search_target+"%' " + likequery;
                System.out.println(sql);
                List datalist = DAOImpl.queryEntityRelationList(sql, "dummy");
                setResponsePage(new UserPage(filter,datalist));
                
            }
            
        };
        add(form);
        
        TextField search_input = new TextField("search_input", new PropertyModel(this,"search_target"));
        form.add(search_input);
        
        
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        if( tdata == null || tdata.size() == 0){
          tdata = DAOImpl.queryEntityRelationList(entity.getSql(), "dummy");
          
          if(filter == null){
              
             
              tdata = DAOImpl.queryEntityRelationList(entity.getSql(), "dummy");
              
          }else{
              List<String> ft = Lists.newArrayList();
              for (String k : filter.keySet()) {
                  if(filter.get(k)) ft.add(k);
              }
              
            
              tdata =DAOImpl.queryEntityWithFilter(entity.getSql(), entity.getFilterField(), ft, "dummy"); 
          }
          
          
        }
		add(new PageableTablePanel("datalist",entity,tdata));
		
		 //for the side bar
        List<Pair<String, Map<String, Object>>> types = null;
        
        types = DAOImpl.queryFilters(entity.getSql(), entity.getFilterField(), entity.getFieldByName(entity.getFilterField()).getPicklist(), "dummy");
        add(new FilterPanel("filterPanel",types ,filter,UserPage.class));
    
	}
}
package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.rex.crm.account.AccountListPanel;
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
public class ContactPage extends TemplatePage
{
    
    private String search_target = "";
	/**
	 * Constructor
	 */
	public ContactPage()
	{
	    initPage(null,null);
		
	}
	
	public ContactPage(Map<String,Boolean> filter,List tdata)
    {

        initPage(filter,tdata);
        
    }
	
    public ContactPage(Map<String,Boolean> map){
        initPage(map,null);
    }
    
    private void initPage(final Map<String,Boolean> filter,List tdata){
        Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("contact");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        Form form = new Form("form"){

            @Override
            protected void onSubmit() {
                String sql = entity.getSql();
                switch(roleId){
                 case 1:
                     sql = entity.getSqlAdmin();
                    break;
                 case 2:
                     sql = entity.getSqlManager();
                    break;
                 case 3:
                     sql = entity.getSql();
                    break;
                }
                
                search_target = (search_target==null || search_target.equalsIgnoreCase("*"))? "":search_target;
               
                List<Field> searchableFields = entity.getSearchableFields();
                String joint = " like '%"+search_target+"%'";
                String likequery = "";
                for(Field sf:searchableFields){
                    likequery = likequery + " OR "+ sf.getName() + joint;
                }
                
                sql =  sql + " where name like '%"+search_target+"%' " + likequery;
                
                System.out.println("TEST:"+sql);
                
                 List datalist = null; 
                
                switch(roleId){
                case 2:
                    datalist = DAOImpl.queryEntityRelationList(sql, userId,userId);
                    break;
                default:
                    datalist = DAOImpl.queryEntityRelationList(sql, userId);
                }
                setResponsePage(new ContactPage(filter,datalist));
                
            }
            
        };
        add(form);
        
        TextField search_input = new TextField("search_input", new PropertyModel(this,"search_target"));
        form.add(search_input);
        
        String sql = entity.getSql();
        switch(roleId){
         case 1:
             sql = entity.getSqlAdmin();
            break;
         case 2:
             sql = entity.getSqlManager();
            break;
         case 3:
             sql = entity.getSql();
            break;
        }
        if (tdata == null || tdata.size() == 0) {
            
            
            
            if (filter == null) {
               
                switch(roleId){
                case 2:
                    tdata = DAOImpl.queryEntityRelationList(sql, userId,userId);
                    break;
                default:
                    tdata = DAOImpl.queryEntityRelationList(sql, userId);
                }
              
              
            } else {

                List<String> ft = Lists.newArrayList();
                for (String k : filter.keySet()) {
                    if (filter.get(k))
                        ft.add(k);
                }
                switch(roleId){
                case 2:
                    tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, userId,userId);
                    break;
                default:
                    tdata =DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, userId);
                }

            }
        }
        add(new PageableTablePanel("datalist",entity,tdata));

        
        //for the side bar
        //List<Pair<String, Map<String, Object>>> types = DAOImpl.queryFilters(sql, entity.getFilterField(), entity.getFieldByName(entity.getFilterField()).getPicklist(), userId);
        
        List<Pair<String, Map<String, Object>>> types = null;
        switch(roleId){
        case 2:
            types =  DAOImpl.queryFilters(sql, entity.getFilterField(), entity.getFieldByName(entity.getFilterField()).getPicklist(), userId,userId);
            break;
        default:
            types = DAOImpl.queryFilters(sql, entity.getFilterField(), entity.getFieldByName(entity.getFilterField()).getPicklist(), userId);
        }
        
        add(new FilterPanel("filterPanel",types ,filter,ContactPage.class));
       
    }
}
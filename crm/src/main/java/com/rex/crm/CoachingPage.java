package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.rex.crm.beans.Choice;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.advancedSearch.AdvancedSearchPage;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class CoachingPage extends TemplatePage
{
    private String search_target = "";
    public CoachingPage(){
       init(null,null);
    }
    public CoachingPage(final Map<String,Boolean> filter,List tdata){
        init(filter,tdata);
    }

    public CoachingPage(List tdata){
        init(null,tdata);
    }
	public void init(final Map<String,Boolean> filter,List tdata)
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("coaching");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        final String posId = ((SignIn2Session)getSession()).getPositionId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        
        Form form = new Form("form"){

            @Override
            protected void onSubmit() {

                String sql = entity.getSql();
                switch(roleId){
                 case UserRole.USER_ROLE_ADMINISTRATOR:
                     sql = entity.getSqlAdmin();
                    break;
                 case UserRole.USER_ROLE_MANAGER:
                     sql = entity.getSqlManager();
                    break;
                 case UserRole.USER_ROLE_SALES:
                     sql = entity.getSql();
                    break;
                }
                   
                search_target = (search_target==null || search_target.equalsIgnoreCase("*"))? "":search_target;
               
                //sql =  sql + " where title like '%"+search_target+"%'";
                List<Field> searchableFields = entity.getSearchableFields();
                String joint = " like '%"+search_target+"%'";
                String likequery = "";
                for(Field sf:searchableFields){
                    likequery = likequery + " OR "+ sf.getName() + joint;
                } 
                sql =  sql + " where title like '%"+search_target+"%' " + likequery;
                List datalist = null; 
                switch(roleId){
                  case UserRole.USER_ROLE_ADMINISTRATOR:
                  datalist = DAOImpl.queryEntityRelationList(sql);
                  break;
                case UserRole.USER_ROLE_MANAGER:
                    datalist = DAOImpl.queryEntityRelationList(sql,posId,posId);
                    break;
                case UserRole.USER_ROLE_SALES:
                    datalist = DAOImpl.queryEntityRelationList(sql,posId);
                }
                setResponsePage(new CoachingPage(filter,datalist));
                
            }
            
        };
        add(form);
        form.add(new Link("ad_search_link"){
            @Override
            public void onClick() {
                setResponsePage(new AdvancedSearchPage(entity.getName(),null));
                
            }
        });
        TextField search_input = new TextField("search_input", new PropertyModel(this,"search_target"));
        form.add(search_input);
        String sql = entity.getSql();
        switch(roleId){
         case UserRole.USER_ROLE_ADMINISTRATOR:
             sql = entity.getSqlAdmin();
            break;
         case UserRole.USER_ROLE_MANAGER:
             sql = entity.getSqlManager();
            break;
         case UserRole.USER_ROLE_SALES:
             sql = entity.getSql();
            break;
        }
        
        if(tdata == null || tdata.size() == 0){
          
            if(filter == null){
                
                switch(roleId){
                  case UserRole.USER_ROLE_ADMINISTRATOR:
                  tdata = DAOImpl.queryEntityRelationList(sql);
                  break;
                case UserRole.USER_ROLE_MANAGER:
                    tdata = DAOImpl.queryEntityRelationList(sql,posId,posId);
                    break;
                case UserRole.USER_ROLE_SALES:
                    tdata = DAOImpl.queryEntityRelationList(sql, posId);
                }
              
                
            }else{
                List<String> ft = Lists.newArrayList();
                for (String k : filter.keySet()) {
                    if(filter.get(k)) ft.add(k);
                }
                
                switch(roleId){
                  case UserRole.USER_ROLE_ADMINISTRATOR:
                  tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft);
                  break;
                case UserRole.USER_ROLE_MANAGER:
                    tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft,posId,posId);
                    break;
                case UserRole.USER_ROLE_SALES:
                    tdata =DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, posId);
                }
                
                
            }
            
            
        }
		add(new PageableTablePanel("datalist",entity,tdata,null));
		
		
		 List<Choice> choices = DAOImpl.queryPickList(entity.getFieldByName(entity.getFilterField()).getPicklist());
       
        add(new FilterPanel("filterPanel",choices ,filter,CoachingPage.class,entity));
	
	}
}
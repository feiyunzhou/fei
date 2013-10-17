package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

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
    private String search_target = "";
    
    /**
     * Constructor
     */
    public AccountPage()
    {
       initPage(null,null);
        
    }
    
    public AccountPage(Map<String,Boolean> map,List tdata){
        initPage(map, tdata);
    }
    
    private void initPage(final Map<String,Boolean> filter,List tdata){
        Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("account");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        Form form = new Form("form"){

            @Override
            protected void onSubmit() {
                String sql = entity.getSql();
                if(roleId == 1){
                    sql = entity.getSqlAdmin();
                }
                
                search_target = (search_target==null || search_target.equalsIgnoreCase("*"))? "":search_target;
               
                sql =  sql + " where name like '%"+search_target+"%'";
                System.out.println(sql);
                List datalist = DAOImpl.queryEntityRelationList(sql, userId);
                setResponsePage(new AccountPage(filter,datalist));
                
            }
            
        };
        add(form);
        
        TextField search_input = new TextField("search_input", new PropertyModel(this,"search_target"));
        form.add(search_input);
        
        
        if(tdata == null || tdata.size() == 0){
        if(filter == null){
            String sql = entity.getSql();
            //if the user is admin we use admin sql to query database
            if(roleId == 1){
                sql = entity.getSqlAdmin();
            }
            tdata = DAOImpl.queryEntityRelationList(sql, userId);
        }else{
            List<String> ft = Lists.newArrayList();
            for (String k : filter.keySet()) {
                if(filter.get(k)) ft.add(k);
            }
            tdata = DAOImpl.queryEntityWithFilter(entity.getSql(), entity.getFilterField(), ft, userId);
        }
        }
        add(new PageableTablePanel("datalist",entity,tdata));
        
        //for the side bar
        List<Pair<String, Map<String, Object>>> types = DAOImpl.queryFilters(entity.getSql(), entity.getFilterField(), entity.getFieldByName(entity.getFilterField()).getPicklist(), userId);
        add(new FilterPanel("filterPanel",types ,filter,AccountPage.class));
       
    }
    

}
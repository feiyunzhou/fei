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

import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class DataManagement extends TemplatePage
{
    private String search_target = "";
    public DataManagement(){
       init(null,null);
    }
    public DataManagement(final Map<String,Boolean> filter,List tdata){
        init(filter,tdata);
    }

	public void init(final Map<String,Boolean> filter,List tdata)
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("data_exchange_teample");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        
//        Form form = new Form("form"){
//
//            @Override
//            protected void onSubmit() {
//
//                String sql = entity.getSql();
//                   
//                search_target = (search_target==null || search_target.equalsIgnoreCase("*"))? "":search_target;
//               
//                //sql =  sql + " where title like '%"+search_target+"%'";
//                List<Field> searchableFields = entity.getSearchableFields();
//                String joint = " like '%"+search_target+"%'";
//                String likequery = "";
//                for(Field sf:searchableFields){
//                    likequery = likequery + " OR "+ sf.getName() + joint;
//                } 
//                sql =  sql + " where title like '%"+search_target+"%' " + likequery;
//                List datalist = null; 
//                  datalist = DAOImpl.queryEntityRelationList(sql);
//                setResponsePage(new DataManagement(filter,datalist));
//            }
//            
//        };
//        add(form);
        
//        TextField search_input = new TextField("search_input", new PropertyModel(this,"search_target"));
//        form.add(search_input);
        String sql = entity.getSql();
        if(tdata == null || tdata.size() == 0){
          
            if(filter == null){
                    tdata = DAOImpl.queryEntityRelationList(sql);
            }
            else{
                List<String> ft = Lists.newArrayList();
                for (String k : filter.keySet()) {
                    if(filter.get(k)) ft.add(k);
                }
                
                  tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft);
            }
            
        }
		add(new PageableTablePanel("datalist",entity,tdata,null));
		
		
//		 //for the side bar
//        List<Pair<String, Map<String, Object>>> types = null;
//          types =  DAOImpl.queryFilters(sql, entity.getFilterField(), entity.getFieldByName(entity.getFilterField()).getPicklist());
//        add(new FilterPanel("filterPanel",types ,filter,DataManagement.class));
	
	}
}
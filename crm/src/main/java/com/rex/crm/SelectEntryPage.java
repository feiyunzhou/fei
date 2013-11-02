package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.beans.Contact;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.NewDataFormPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

/**
 * 
 * 
 * @author Feiyun
 */
public class SelectEntryPage extends WebPage {
    private static final Logger logger = Logger.getLogger(SelectEntryPage.class);

    private String search_target;

    
    /**
     * Constructor
     * 
     * @param parameters
     *            Page parameters (ignored since this is the home page)
     */
    public SelectEntryPage() {
        String entityName = getRequest().getRequestParameters().getParameterValue("en").toString();
        String excludeEntityName = getRequest().getRequestParameters().getParameterValue("excludeName").toString();
        final String excludeId = getRequest().getRequestParameters().getParameterValue("eid").toString();
        initPage(null,entityName,excludeEntityName,excludeId);
    }

    public SelectEntryPage(List<Map> maplist,String entityName,String excludeEntityName,String excludeId) {
        initPage(maplist,entityName,excludeEntityName,excludeId);
    }

    public void initPage(List<Map> list,final String entityName,final String excludeEntityName,final String excludeId) {
        final String posId = ((SignIn2Session) getSession()).getPositionId();
        
        final int roleId = ((SignIn2Session) getSession()).getRoleId();
        Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get(entityName);
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                List<Map> maplist = null;
                if(entityName.equalsIgnoreCase("account")){
                    
                    String sql = assembleSearchingSQL(roleId, entity);
                    System.out.println(sql);
            
                    switch (roleId) {
                    case 2:
                        maplist = DAOImpl.queryEntityRelationList(sql, posId, posId, posId);
                        break;
                    case 3:
                        maplist = DAOImpl.queryEntityRelationList(sql, posId, posId);
                        break;
                    case 1:
                        maplist = DAOImpl.queryEntityRelationList(sql);
                    }
                    
                    
                }else if(entityName.equalsIgnoreCase("contact")){
                    
                    String sql = assembleSearchingSQL(roleId, entity);
                    
                    switch (roleId) {
                    case 2:
                        maplist = DAOImpl.queryEntityRelationList(sql, posId,posId);
                        break;
                    case 3:
                        maplist = DAOImpl.queryEntityRelationList(sql, posId);
                        break;
                    case 1:
                        maplist = DAOImpl.queryEntityRelationList(sql);
                    }
                    
                }else if(excludeEntityName.equalsIgnoreCase("coaching")){
                  maplist = DAOImpl.searchCoachee(search_target,excludeId,posId);
                }else if(entityName.equalsIgnoreCase("crmuser")){
                    //maplist = DAOImpl.searchCRMUser(search_target);
                    maplist = DAOImpl.searchManager(search_target,excludeId);
                    Map dummy = Maps.newHashMap();
                    dummy.put("id",-1);
                    dummy.put("name", "无");
                    maplist.add(dummy);
                    
                }
                //this.setResponsePage(cls, parameters)
                
                setResponsePage(new SelectEntryPage(maplist,entityName,excludeEntityName,excludeId));

            }
        };
        form.add(new TextField<String>("search_input", new PropertyModel<String>(this, "search_target")));
        add(form);
       
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);

        if (list != null) {
            for (Map map : list) {
                int uid = ((Number) map.get("id")).intValue();
                String name = (String) map.get("name");
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);

                item.add(new AttributeAppender("data-id",new Model(uid)));
                item.add(new AttributeAppender("data-name",new Model(name)));
                item.add(new AttributeAppender("data-ename",entityName));
                
                //item.add(new AttributeAppender("data-cname", new Model(cname)));
                Label cap = new Label("name_span", new Model(name));
                item.add(cap);
                
                
            }
        }
    }

    private String assembleSearchingSQL(final int roleId, final Entity entity) {
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
        
          sql =  sql + " where name like '%"+search_target+"%' " + likequery ;
        return sql;
    }
}

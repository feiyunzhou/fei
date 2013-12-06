package com.rex.crm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.common.collect.Maps;
import com.rex.crm.common.CreateDataPage;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.userlog.LogInOut;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class SelectActivityContactPage extends WebPage {
	private static final Logger logger = Logger.getLogger(SelectActivityContactPage.class);

    private static String search_target;
    final String posId = ((SignIn2Session) getSession()).getPositionId();
	final String userId = ((SignIn2Session) getSession()).getUserId();
	final int roleId = ((SignIn2Session) getSession()).getRoleId();
	final Map<String, Entity> entities = Configuration.getEntityTable();
	Entity entity = null;
	public SelectActivityContactPage(){
		String relationTableName = getRequest().getRequestParameters().getParameterValue("en").toString();
        String tragetEntity = getRequest().getRequestParameters().getParameterValue("excludeName").toString();
        final String excludeId = getRequest().getRequestParameters().getParameterValue("eid").toString();
        String target = getRequest().getRequestParameters().getParameterValue("target").toString();
        entity = entities.get(relationTableName);
        
        List<Map> maplist = selectConatct(tragetEntity,roleId,posId,entity,entities );
        initPage(maplist,relationTableName,tragetEntity,excludeId,target);
       // setResponsePage(new SelectActivityContactPage(,"contact",tragetEntity,excludeId,target));
	}
	public SelectActivityContactPage(List<Map> list,final String relationTableName,final String tragetEntity,final String excludeId,final String target){
		initPage(list,relationTableName,tragetEntity,excludeId,target);
	}
	public void initPage(List<Map> list,final String relationTableName,final String tragetEntity,final String excludeId,final String target) {
		final Entity entity = entities.get(relationTableName); 
		Form form = new Form("form") {
            @Override
            protected void onSubmit() {
            	List<Map> maplist = selectConatct(tragetEntity,roleId,posId,entity,entities );
            	setResponsePage(new SelectActivityContactPage(maplist,relationTableName,tragetEntity,excludeId,target));
            }
		};
		form.add(new TextField<String>("search_input", new PropertyModel<String>(this, "search_target")));
        add(form);
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);

        if (list != null) {
            List<Field> searchableFields = entity.getSearchableFields();
            for (Map map : list) {
                final int uid = ((Number) map.get("id")).intValue();
                String name = (String) map.get("name");
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);

                item.add(new AttributeAppender("data-id",new Model(uid)));
                item.add(new AttributeAppender("data-name",new Model(name)));
                item.add(new AttributeAppender("data-ename",relationTableName));
                
                //item.add(new AttributeAppender("data-cname", new Model(cname)));
                Label cap = new Label("name_span", new Model(name));
                Link link = new Link("createJump"){
                    @Override
                    public void onClick() {
                    	System.out.println("fsfsfs");
                    	Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(uid));
                    	Entity entity = entities.get("contact");
                    	List<Field> paramFields = entity.getParamFields();
                    	Map<String,Object> params = Maps.newHashMap();
                        for(Field f:paramFields){
                            params.put("contact"+"."+f.getName(), map.get(f.getName()));
                        }
                    	setResponsePage(new CreateDataPage("activity",params));
                    }
                    
                };
                link.add(cap);
                //link.add(new AttributeAppender("html",new Model(name),""));
                item.add(link);
                
                
                RepeatingView column_repeater = new RepeatingView("column_repeater");
                item.add(column_repeater);
                for(Field f:searchableFields){
                    Object obj = map.get(f.getName());
                    if(!f.getName().equalsIgnoreCase("name") && obj != null){
                        AbstractItem column_item = new AbstractItem(column_repeater.newChildId());
                        column_repeater.add(column_item); 
                     
                        String celldata =String.valueOf(obj);
                        column_item.add(new Label("celldata","<strong>"+f.getDisplay()+": </strong>"+celldata).setEscapeModelStrings(false));
                    }
                } 
                
               

                Object obj =  map.get("num_of_visiting");               
                if (obj != null) {
                    AbstractItem column_item = new AbstractItem(column_repeater.newChildId());
                    column_repeater.add(column_item);
                    String num_of_visiting = String.valueOf(obj);
                    column_item.add(new Label("celldata", num_of_visiting + "（拜访次数）"));
                }
                    
               
                
            }
        }
	}
    
    public static List<Map> selectConatct(final String tragetEntity,int roleId,String posId,Entity entity,Map<String, Entity> entities ){
    	List<Map> maplist = null;
        if (tragetEntity.equalsIgnoreCase("activity")) {

            String sql = assembleSearchingSQL(roleId, entity);

            switch (roleId) {
            case 2:{
                     maplist = DAOImpl.queryEntityRelationList(sql, posId, posId);
                     
                     break;
                  }
            case 3:{
                     maplist = DAOImpl.queryEntityRelationList(sql, posId);
                     
                     Entity activityEnt = entities.get("activity");
                     String actSQL = activityEnt.getSql();
                     actSQL  = "select contactName,count(contactName) as ct from ("+ actSQL + ") as bact where status=2 group by contactName";
                     logger.debug("number_of_act:"+actSQL);
                     
                     List<Map> num_of_act_per_contact = DAOImpl.queryEntityRelationList(actSQL, posId);

                     Map<String,Map> activity_contact_map = Maps.newHashMap();
                     for(Map map:num_of_act_per_contact){
                         activity_contact_map.put(String.valueOf(map.get("contactName")),map);
                     }
                     
                     
                    // Map<String,Map> contact_map = Maps.newHashMap();
                     for(Map map:maplist){
                        String contactId = String.valueOf(map.get("id"));
                        if(activity_contact_map.containsKey(contactId)){
                            map.put("num_of_visiting", activity_contact_map.get(contactId).get("ct"));
                        }
                     }
                     
                     //sorting
                     //Collections.s`
                     Collections.sort(maplist,new Comparator<Map>(){

                                @Override
                                public int compare(Map o1, Map o2) {
                                    Object obj1 = o1.get("num_of_visiting");
                                    Object obj2 = o2.get("num_of_visiting");
                                    if(obj1 == null || obj2 == null) return 0;
                                    
                                     long  v1 =  (long)o1.get("num_of_visiting");
                                     long  v2 =  (long)o2.get("num_of_visiting");
                                     
                                     return (int)(v2-v1);
                                   
                                }
                         
                     });
                     
                     
                     break;
                  }
            case 1:
                maplist = DAOImpl.queryEntityRelationList(sql);
            }
        }
        return maplist;
    }
    private static String assembleSearchingSQL(final int roleId, final Entity entity) {
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

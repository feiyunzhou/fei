package com.rex.crm.common;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivityPage;
import com.rex.crm.ContactPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.TemplatePage;
import com.rex.crm.UserPage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class EntityDetailPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(EntityDetailPage.class);
    private static final long serialVersionUID = -2613412283023068638L;

    private static int NUM_OF_COLUMN  = 3;
    
    public EntityDetailPage(final String entityName, final String id){
        this.setPageTitle("详细信息");
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get(entityName);
        
        
        long lid = Long.parseLong(id);
       // Map map = DAOImpl.getEntityData(entity.getName(), entity.getFieldNames(), lid);
        Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(lid));
        if(entity.getName().equals("activity")){
        	add(new Label("name",String.valueOf(map.get("title"))));
        }else{
        	add(new Label("name",String.valueOf(map.get("name"))));
        }
        
        add(new EntityDetailPanel("detailed",entity,map,id,3,entityName));
        

        //set relations data
         List<Relation> relations = Configuration.getRelationsByName(entityName);
         
         RepeatingView relationRepeater = new RepeatingView("relationRepeater");
         add(relationRepeater);
         
         for(Relation r:relations){
           AbstractItem item = new AbstractItem(relationRepeater.newChildId());
           relationRepeater.add(item);
           logger.debug(r.getSql());
           logger.debug("parms:"+id);
           List list = DAOImpl.queryEntityRelationList(r.getSql(), id);
           item.add(new RelationDataPanel("relationPanel",r,entityName,list,String.valueOf(lid)));
           
         }
         if(entityName.equalsIgnoreCase("contact") || entityName.equalsIgnoreCase("account")){
             add(new TeamManPanel("teamPanel",entityName,String.valueOf(lid),0));
             WebMarkupContainer con2 = new WebMarkupContainer("teamPanel2");
             add(con2); 
             con2.setVisible(false);
         }else if(entityName.equalsIgnoreCase("crmuser")){
             add(new TeamManPanel("teamPanel",entityName,String.valueOf(lid),0));
             add(new TeamManPanel("teamPanel2",entityName,String.valueOf(lid),1));
         }
         else{
             WebMarkupContainer con = new WebMarkupContainer("teamPanel");
             add(con); 
             con.setVisible(false);
             WebMarkupContainer con2 = new WebMarkupContainer("teamPanel2");
             add(con2);
             con2.setVisible(false);
         }

         add(new AbstractAjaxBehavior(){

            @Override
            public void onRequest() {
            }

            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);
                response.render(OnDomReadyHeaderItem.forScript("$(\"#navitem-"+entityName+"\").addClass(\"active\");"));
            }  
             
         });
         
         
         ICRUDActionListener actionListener = new DefaultCRUDActionListener(){

            @Override
            public void delete() {
                if(entityName.equals("account")){
                    DAOImpl.deleteRecord(id, entityName);
                    setResponsePage(new AccountPage());
                }else if(entityName.equals("contact")){
                    DAOImpl.deleteRecord(id, entityName);
                    setResponsePage(new ContactPage());
                }else if(entityName.equals("activity")) {
                    DAOImpl.deleteRecord(id, entityName);
                    setResponsePage(new ActivityPage());
                }else if(entityName.equalsIgnoreCase("crmuser")){
                    if(DAOImpl.deleteRecord(id, entityName)>0){
                       DAOImpl.updateCrmUserReport(id, "-1");
                    }
                    setResponsePage(new UserPage());
                }
            }

            @Override
            public void update() {
                setResponsePage(new EditDataPage(entityName,id));
            }
                
         };
         

         add(new CRUDPanel("operationBar",entity.getName(),id, CRMUtility.getPermissionForEntity(roleId, entity.getName()),actionListener));
         
    }

}

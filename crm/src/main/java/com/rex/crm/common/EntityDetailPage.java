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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.rex.crm.SignIn2Session;
import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class EntityDetailPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(EntityDetailPage.class);
    private static final long serialVersionUID = -2613412283023068638L;

    private static int NUM_OF_COLUMN  = 3;
    
    public EntityDetailPage(final String entityName, String id){
        this.setPageTitle("详细信息");
       
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get(entityName);
        
        
        long lid = Long.parseLong(id);
       // Map map = DAOImpl.getEntityData(entity.getName(), entity.getFieldNames(), lid);
        logger.debug("KKKKKKKK:"+entity.getSql_ent()+ "  PPPP:"+lid);
        Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(lid));
        add(new Label("name",String.valueOf(map.get("name"))));
        add(new EntityDetailPanel("detailed",entity,map,id,3));
        

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
           item.add(new RelationDataPanel("relationPanel",r,list,String.valueOf(lid)));
           
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
         
         final int roleId = ((SignIn2Session)getSession()).getRoleId();
         add(new CRUDPanel("operationBar",entity.getName(),id, CRMUtility.getPermissionForEntity(roleId, entity.getName())));
         
        
    }

}

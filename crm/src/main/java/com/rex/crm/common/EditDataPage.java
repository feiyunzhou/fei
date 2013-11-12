package com.rex.crm.common;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

public class EditDataPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(EntityDetailPage.class);
    private static final long serialVersionUID = -2613412283023068638L;

    private static int NUM_OF_COLUMN  = 3;
    
    public EditDataPage(final String entityName, String id){
        this.setPageTitle("编辑");
       
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get(entityName);
        List<Field> currentFields = entity.getFields();
        long lid = Long.parseLong(id);
       // Map map = DAOImpl.getEntityData(entity.getName(), entity.getFieldNames(), lid);
        Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(lid));
       
        add(new EditDataFormPanel("detailed",entity,map,id));
        
        //set relations data
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
         
        
    }

}

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

import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
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
        //id = "25";b
         logger.debug("BBDBSDBDBDBDBDBD:"+id);
        long lid = Long.parseLong(id);
        Map map = DAOImpl.getEntityData(entity.getName(), entity.getFieldNames(), lid);
//        String value = CRMUtility.formatValue(currentField.getFormatter(),String.valueOf(map.get(currentField.getName())));
        add(new Label("name",String.valueOf(map.get("name"))));
        add(new EditDataFormPanel("detailed",entity,map,id, map));
        
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

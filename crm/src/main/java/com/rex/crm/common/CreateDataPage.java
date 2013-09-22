package com.rex.crm.common;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.apache.wicket.util.string.StringValue;

import com.google.common.collect.Maps;
import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class CreateDataPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(CreateDataPage.class);
    private static final long serialVersionUID = -2613412283023068638L;

    private static int NUM_OF_COLUMN  = 2;
    
    
    public CreateDataPage(){
        
        StringValue entityName = getRequest().getRequestParameters().getParameterValue("entityName");
        String name = entityName.toString();
        Set<String> names = getRequest().getRequestParameters().getParameterNames();
        Map<String,String> map = Maps.newHashMap();
        for(String nm:names){
            StringValue sv = getRequest().getRequestParameters().getParameterValue(nm);
            map.put(nm, sv.toString());
        }
        
        initPage(name,map);
    }
   
    public CreateDataPage(String entityName){
       
        initPage(entityName,null);
        
    }
    
    private void initPage(String entityName,Map<String,String> map){
        //this.getRequest().
        this.setPageTitle("创建");
       //this.getPageParameters().get
        Map<String, Entity> entities = Configuration.getEntityTable();
        //if (entityName == null) entityName="contact";
        final Entity entity = entities.get(entityName);
        
        add(new NewDataFormPanel("formPanel",entity,map));


         add(new AbstractAjaxBehavior(){

            @Override
            public void onRequest() {
            }

            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);
                response.render(OnDomReadyHeaderItem.forScript("$(\"#navitem-"+entity.getName()+"\").addClass(\"active\");"));
            }  
             
         });
    }

}

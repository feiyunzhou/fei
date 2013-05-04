package com.rex.crm.common;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

public class EntityDetailPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(EntityDetailPage.class);
    private static final long serialVersionUID = -2613412283023068638L;

    private static int NUM_OF_COLUMN  = 3;
    
    public EntityDetailPage(final String entityName, String id){
        this.setPageTitle("详细信息");
        //TODO Get permission info of user from database.
        add(new CRUDPanel("operationBar",EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.EDIT)));
        
        //TODO Get the table definition from database or configuration   
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get(entityName);
        String primaryKeyName = entity.getPrimaryKeyName();
        List<Field> fields = entity.getFields();
        List<String> fn = entity.getFieldNames();
        
        long lid = Long.parseLong(id);
        Map map = DAOImpl.getEntityData(entity.getName(), fn , lid);
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);
        
       int numOfField = 0;
       List<Field> visibleFields = new ArrayList();
       for(Field f:fields){
           if(!f.isVisible()) continue;
           numOfField++;
           visibleFields.add(f);
       }
        
        logger.debug("numOfField:"+numOfField);
       //set the detailed info
        int num_of_row = (numOfField/NUM_OF_COLUMN) + 1;
        logger.debug("num_of_row:"+num_of_row);
        
        for(int i=0;i<num_of_row;i++){
            AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
            dataRowRepeater.add(item);
            RepeatingView columnRepeater = new RepeatingView("columnRepeater");
            item.add(columnRepeater);
            
            for(int j=0;j<NUM_OF_COLUMN;j++){
              if((i*NUM_OF_COLUMN+j)>=visibleFields.size()) break;
              Field currentField = visibleFields.get(i*NUM_OF_COLUMN+j);
              AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(String.valueOf(map.get(primaryKeyName))));
              
              if(currentField.getPicklist()!=null){
                  
                  String value = DAOImpl.queryPickListById(currentField.getPicklist(), String.valueOf(map.get(currentField.getName())));
                  columnitem.add(new Label("celldata",currentField.getDisplay()+": "+value));
              }else{
                  
                  columnitem.add(new Label("celldata",currentField.getDisplay()+": "+String.valueOf(map.get(currentField.getName()))));
              }
              
              
              columnRepeater.add(columnitem);
            }
      
        }//end of  set the detailed info 
        
        
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
           item.add(new RelationDataPanel("relationPanel",r,list));
           
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
         
        
    }

}

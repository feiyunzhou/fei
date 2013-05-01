package com.rex.crm.common;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

public class EntityDetailPage extends TemplatePage {
    private static final long serialVersionUID = -2613412283023068638L;

    private static int NUM_OF_COLUMN  = 1;
    
    public EntityDetailPage(String entityName, String id){
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
       for(Field f:fields){
           if(!f.isVisible()) continue;
           numOfField++;
       }
        
        
       //set the detailed info
        int num_of_row = (numOfField/NUM_OF_COLUMN) + 1;
        
        for(int i=0;i<num_of_row;i++){
            AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
            dataRowRepeater.add(item);
            RepeatingView columnRepeater = new RepeatingView("columnRepeater");
            item.add(columnRepeater);
            
            for(int j=0;j<NUM_OF_COLUMN;j++){
              if((i*NUM_OF_COLUMN+j)>=fields.size()) break;
              Field currentField = fields.get(i*NUM_OF_COLUMN+j);
              if(!currentField.isVisible()) continue;
              AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(String.valueOf(map.get(primaryKeyName))));
              columnitem.add(new Label("celldata",currentField.getDisplay()+": "+String.valueOf(map.get(currentField.getName()))));
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
           
           List list = DAOImpl.queryEntityRelationList(r.getSql(), id);
           item.add(new RelationDataPanel("relationPanel",r,list));
           
         }
         
        
    }

}

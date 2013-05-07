package com.rex.crm.common;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.rex.crm.account.AccountDetailPage;
import com.rex.crm.beans.Account;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class TableDataPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(TableDataPanel.class);

    public TableDataPanel(String id,Entity entity, List mapList) {
        super(id);
  
        //TODO Get the table definition from database or configuration
        String primaryKeyName = entity.getPrimaryKeyName();
        List<Field> fields = entity.getFields();
        List<String> fn = entity.getFieldNames();
        final String entityName = entity.getName();
        
        //set column name
        RepeatingView columnNameRepeater = new RepeatingView("columnNameRepeater");
        add(columnNameRepeater);
        for(Field f:entity.getFields()){
            if(!f.isVisible()) continue;
            AbstractItem item = new AbstractItem(columnNameRepeater.newChildId());
            columnNameRepeater.add(item);
            item.add(new Label("columnName",f.getDisplay()));
        }
        //end of set column name
        
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);

        for (int i = 0; i < mapList.size(); i++)
        {
            Map map = (Map)mapList.get(i);
            AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
            dataRowRepeater.add(item);
            RepeatingView columnRepeater = new RepeatingView("columnRepeater");
            item.add(columnRepeater);
            final String rowId =  String.valueOf(map.get(primaryKeyName));
            for(Field f:fields){
                if(!f.isVisible()) continue;
                //AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(String.valueOf(map.get(primaryKeyName))));
                AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(){

                    @Override
                    public Serializable getObject() {
                        Param p = new Param();
                        p.setId(rowId);
                        p.setEntityName(entityName);
                        return p;
                    }
                    
                });
                if(f.isDetailLink()){
                    String value = CRMUtility.formatValue(f.getFormatter(),String.valueOf(map.get(f.getName())));
                    columnitem.add(new DetailLinkFragment("celldata","detailFragment",this,value));
                }else{
                   if(f.getPicklist()!=null){
                      //get option from picklist 
                      String value = CRMUtility.formatValue(f.getFormatter(),DAOImpl.queryPickListById(f.getPicklist(), String.valueOf(map.get(f.getName()))));
                      columnitem.add(new Label("celldata",value));
                       
                   }else{
                       String value = CRMUtility.formatValue(f.getFormatter(),String.valueOf(map.get(f.getName())));
                      columnitem.add(new Label("celldata",value));
                   }
                }
                //columnitem.add(new Label("celldata",String.valueOf(fn.get(j).getClass().cast(map.get(fn.get(j))))));
                columnRepeater.add(columnitem);
            }
        }
        
        //TODO Get permission info of user from database.
        add(new CRUDPanel("operationBar",EnumSet.of(CRUDPanel.Permissions.ADD)));
        
        
        add(new NewDataFormPanel("formPanel",entity.getFields()));

    }


    public TableDataPanel(String id, IModel<?> model) {
        super(id, model);
    }
    
    
    private class DetailLinkFragment extends Fragment
    {
        /**
         * Construct.
         * 
         * @param id
         *            The component Id
         * @param markupId
         *            The id in the markup
         * @param markupProvider
         *            The markup provider
         */
        public DetailLinkFragment(String id, String markupId, MarkupContainer markupProvider,String caption)
        {
            super(id, markupId, markupProvider);
            add(new Link("detailclick")
            {
                
                @Override
                public void onClick()
                {
                    //System.out.println(getParent().getId());
                   // System.out.println("this link is clicked!"+this.getParent().getParent().getDefaultModelObject());
                    //Account selected = (Account)getParent().getDefaultModelObject();
                    Param p = (Param)getParent().getParent().getDefaultModelObject();
                    logger.debug(p+ " id:"+p.getId() + " name:"+p.getEntityName());
                    setResponsePage(new EntityDetailPage(p.getEntityName(),p.getId()));
                    
                    //setResponsePage(new AccountDetailPage(id));
                }
            }.add(new Label("caption", new Model<String>(caption))));
        }
    }
    

}

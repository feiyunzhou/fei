package com.rex.crm.common;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

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
import com.rex.crm.util.Configuration;

public class TableDataPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;

    public TableDataPanel(String id,String tableName) {
        super(id);
  
        //TODO Get the table definition from database or configuration   
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get(tableName);
        String primaryKeyName = entity.getPrimaryKeyName();
        List<Field> fields = entity.getFields();
        List<String> fn = entity.getFieldNames();
 
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
        
        List mapList = DAOImpl.getTableData(entity.getName(), fn , 0, 1000);
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);

        for (int i = 0; i < mapList.size(); i++)
        {
            Map map = (Map)mapList.get(i);
            AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
            dataRowRepeater.add(item);
            RepeatingView columnRepeater = new RepeatingView("columnRepeater");
            item.add(columnRepeater);
            for(Field f:fields){
                if(!f.isVisible()) continue;
                AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(String.valueOf(map.get(primaryKeyName))));
                if(f.isDetailLink()){
                    columnitem.add(new DetailLinkFragment("celldata","detailFragment",this,String.valueOf(map.get(f.getName()))));
                }else{
                   columnitem.add(new Label("celldata",String.valueOf(map.get(f.getName()))));
                }
                //columnitem.add(new Label("celldata",String.valueOf(fn.get(j).getClass().cast(map.get(fn.get(j))))));
                columnRepeater.add(columnitem);
            }
        }
        
        //TODO Get permission info of user from database.
        add(new CRUDPanel("operationBar",EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.ADD,CRUDPanel.Permissions.EDIT)));

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
                    String id = (String)getParent().getParent().getDefaultModelObject();
                    setResponsePage(new EntityDetailPage("account",id));
                    
                    //setResponsePage(new AccountDetailPage(id));
                }
            }.add(new Label("caption", new Model<String>(caption))));
        }
    }
    

}

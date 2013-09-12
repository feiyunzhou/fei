package com.rex.crm.common;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.account.AccountDetailPage;
import com.rex.crm.beans.Account;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class ContactTeamManPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(ContactTeamManPanel.class);
    private String etId;
    private String currentEntityName;

    public ContactTeamManPanel(String id,String en,final String entityId) {
        super(id);
        etId = entityId;
        currentEntityName = en;
        //team sql
        String teamSql = "select * from (select a.*,b.id as rid from crmuser as a inner join contactcrmuser as b on a.id=b.crmuserId where b.contactId=?) as atable";
        List mapList = DAOImpl.queryEntityRelationList(teamSql, entityId);
        
        Entity entity = Configuration.getEntityByName("crmuser");
        String primaryKeyName = entity.getPrimaryKeyName();
        List<Field> fields = entity.getFields();
        List<String> fn = entity.getFieldNames();
        final String entityName = entity.getName();
        
        //add button submission
        
        add(new AjaxLink<Void>("add_users_link"){

            @Override
            public void onClick(AjaxRequestTarget target) {
              String script = "window.open(\"../mount/searchCRMUser?cid="+entityId+"\",\"Ratting\",\"width=850,height=470,left=150,top=200,0,status=0,\");";
               target.appendJavaScript(script);
            }
            
        });
        
        
        
        //set column name
        RepeatingView columnNameRepeater = new RepeatingView("columnNameRepeater");
        add(columnNameRepeater);
        for(Field f:entity.getFields()){
            if (!f.isVisible()|| f.getPriority() >1)
                continue;
            AbstractItem item = new AbstractItem(columnNameRepeater.newChildId());
            columnNameRepeater.add(item);
            item.add(new Label("columnName", f.getDisplay()));
        }
        //end of set column name
        
        //Add extral field
        
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);

        for (int i = 0; i < mapList.size(); i++)
        {
            Map map = (Map)mapList.get(i);
            AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
            dataRowRepeater.add(item);
            RepeatingView columnRepeater = new RepeatingView("columnRepeater");
            item.add(columnRepeater);
            final String rowId =  String.valueOf(map.get("rid"));            
            
            for (Field f : fields) {
                if (!f.isVisible() || f.getPriority() >1)
                    continue;
                
                AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model() {
                    @Override
                    public Serializable getObject() {
                        Param p = new Param();
                        p.setId(rowId);
                        p.setEntityName(entityName);
                        //p.setExtraId(extraId);
                        return p;
                    }

                });
                
                if (f.isDetailLink()) {
                    String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                    //columnitem.add(new DetailLinkFragment("celldata", "detailFragment", this.getParent().getParent(), value));
//                    columnitem.add(new ButtonFragment("celldata","buttonFragment",this,"删除"));
                    columnitem.add(new DetailLinkFragment("celldata","detailFragment",this,value));
                    
                    
     
                } else {
                    if (f.getPicklist() != null) {
                        // get option from picklist
                        String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryPickListByIdCached(f.getPicklist(), String.valueOf(map.get(f.getName()))));
                        columnitem.add(new Label("celldata", value));
                    } else if(f.getRelationTable() != null){
                        String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName()))));
                        columnitem.add(new Label("celldata", value));
                    }else {
                        String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                        columnitem.add(new Label("celldata", value));
                    }
                }
                columnRepeater.add(columnitem);
            }
            
            //add extra field in the last column
            AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(rowId));
            columnitem.add(new ExtraFieldFragment("celldata","extraFieldFragment",this,"删除"));
            columnRepeater.add(columnitem);
            
        }
        
        //TODO Get permission info of user from database.
        add(new CRUDPanel("operationBar",entityName,null,EnumSet.of(CRUDPanel.Permissions.ADD)));
        
        
        add(new NewDataFormPanel("formPanel",entity,null));

    }


    public ContactTeamManPanel(String id, IModel<?> model) {
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
    
    
    private class ExtraFieldFragment extends Fragment
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
        public ExtraFieldFragment(String id, String markupId, MarkupContainer markupProvider,String caption)
        {
            super(id, markupId, markupProvider);
            add(new Link("remove_team_member_click")
            {
                
                @Override
                public void onClick()
                {
                    //System.out.println(getParent().getId());
                   // System.out.println("this link is clicked!"+this.getParent().getParent().getDefaultModelObject());
                    //Account selected = (Account)getParent().getDefaultModelObject();
                    String id  = (String)getParent().getParent().getDefaultModelObject();
                   
                    //remove the team member from the contact
                    DAOImpl.removeContactFromTeam(id);
                    
                    setResponsePage(new EntityDetailPage(currentEntityName,etId));
                    
                }
            }.add(new Label("cap", caption)));
        }
    }
    

}

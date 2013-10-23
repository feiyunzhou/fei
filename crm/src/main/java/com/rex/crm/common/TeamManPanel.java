package com.rex.crm.common;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.SearchCRMUserPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.beans.Account;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class TeamManPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(TeamManPanel.class);
    private String etId;
    private String currentEntityName;

    public TeamManPanel(String id,final String en,final String entityId,final int type) {
        super(id);
        etId = entityId;
        currentEntityName = en;
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        //team sql
        String teamSql = "";
        if(en.equalsIgnoreCase("account")){
          teamSql = "select * from (select a.*,b.id as rid from crmuser as a inner join accountcrmuser as b on a.id=b.crmuserId where b.accountId=?) as atable";
        }else if(en.equalsIgnoreCase("contact")){
            teamSql = "select * from (select a.*,b.id as rid from crmuser as a inner join contactcrmuser as b on a.id=b.crmuserId where b.contactId=?) as atable";
        }else if(en.equalsIgnoreCase("crmuser")){
            if(type == 0){
                //for the 医院列表
               teamSql = "select * from (select a.*,b.id as rid from account as a inner join accountcrmuser as b on a.id=b.accountId where b.crmuserId=?) as atable";
            }else if(type == 1){
                //for the 医生列表
                teamSql = "select * from (select a.*,b.id as rid from contact as a inner join contactcrmuser as b on a.id=b.contactId where b.crmuserId=?) as atable";
            }
        }
        List mapList = DAOImpl.queryEntityRelationList(teamSql, entityId);
        Entity entity=null ;
        if(en.equalsIgnoreCase("account")||en.equalsIgnoreCase("contact")){
        	 entity = Configuration.getEntityByName("crmuser");
        	 if(en.equalsIgnoreCase("account")){
        	     add(new Label("title","医院"));
        	 }else{
        	     add(new Label("title","医生"));
        	 }
        }else{
            if(type == 0){
        	  entity = Configuration.getEntityByName("account");
        	  add(new Label("title","医院"));
            }else if(type == 1){
                entity = Configuration.getEntityByName("contact");
                add(new Label("title","医生"));
            }
        }
        
        List<Field> fields = entity.getFields();
        final String entityName = entity.getName();
        
        //add button submission
        if(roleId != 1){
        	 WebMarkupContainer con = new WebMarkupContainer("add_users_link");
             add(con);
             con.setVisible(false);
             //con.add(new AttributeAppender("style", new Model("display:none;"), ";"));
        }else{
        	add(new Link<Void>("add_users_link"){

                @Override
                public void onClick() {
                  this.setResponsePage(new SearchCRMUserPage(currentEntityName,entityId,type));
                }
                
            });

        }
                
        //set column name
        RepeatingView columnNameRepeater = new RepeatingView("columnNameRepeater");
        add(columnNameRepeater);
        int count=0;
        for(Field f:entity.getFields()){
            if (!f.isVisible()|| f.getPriority() >1)
                continue;
            AbstractItem item = new AbstractItem(columnNameRepeater.newChildId());
            if(count==0){
                item.add(new AttributeAppender("class", new Model("table-first-link"), " "));
                count++;
            }
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
            final String realId =  String.valueOf(map.get("id"));  
            
            for (Field f : fields) {
                if (!f.isVisible() || f.getPriority() >1)
                    continue;
                
                AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model() {
                    @Override
                    public Serializable getObject() {
                        Param p = new Param();
                        p.setId(rowId);
                        p.setExtraId(realId);
                        //Param par = new Parameter();
                        p.setEntityName(entityName);
                        //p.setExtraId(extraId);
                        return p;
                    }

                });
                
                if (f.isDetailLink()) {
                    String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                    if(value.equals("null")||value.equals("")||value.equals("dummy")){
                      value = "无";
                    }
                    columnitem.add(new AttributeAppender("class", new Model("table-first-link"), " "));
                    columnitem.add(new DetailLinkFragment("celldata","detailFragment",this,value));
                } else {
                    if (f.getPicklist() != null) {
                        // get option from picklist
                        String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryPickListByIdCached(f.getPicklist(), String.valueOf(map.get(f.getName()))));
                        if(value.equals("null")||value.equals("")||value.equals("dummy")){
                          value = "无";
                        }
                        columnitem.add(new Label("celldata", value));
                    } else if(f.getRelationTable() != null){
                        String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName()))));
                        if(value.equals("null")||value.equals("")||value.equals("dummy")){
                          value = "无";
                        }
                        columnitem.add(new Label("celldata", value));
                    }else {
                        String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                        if(value.equals("null")||value.equals("")||value.equals("dummy")){
                          value = "无";
                        }
                        columnitem.add(new Label("celldata", value));
                    }
                }
                columnRepeater.add(columnitem);
            }
            //add extra field in the last column
            AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(rowId));
            if(roleId == 1){
              columnitem.add(new ExtraFieldFragment("celldata","extraFieldFragment",this,"删除",type));
              columnitem.add(new AttributeAppender("class", new Model("table-first-link delete"), " "));
            }else{
              columnitem.add(new WebMarkupContainer("celldata"));
            }
             
            columnRepeater.add(columnitem);
            
        }
        add(new NewDataFormPanel("formPanel",entity,null));
    }

    public TeamManPanel(String id, IModel<?> model) {
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
                    setResponsePage(new EntityDetailPage(p.getEntityName(),p.getExtraId()));
                    
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
        public ExtraFieldFragment(String id, String markupId, MarkupContainer markupProvider,String caption,final int type)
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
                  
                    String rid = (String)getParent().getParent().getDefaultModelObject();
                    //remove the team member from the contact
                    String teamtable = "";
                    if(currentEntityName.equalsIgnoreCase("account")){
                        teamtable = "accountcrmuser";
                        
                    }else if(currentEntityName.equalsIgnoreCase("contact")){
                        teamtable = "contactcrmuser";
                    }else if(currentEntityName.equalsIgnoreCase("crmuser")){
                        if(type == 0){
                            teamtable = "accountcrmuser";
                        }else if(type == 1){
                            teamtable = "contactcrmuser";
                        }
                    }
                    logger.debug("delete row:"+rid);
                    DAOImpl.removeEntityFromTeam(teamtable,rid);
                    
                    setResponsePage(new EntityDetailPage(currentEntityName,etId));
                    
                }
            }.add(new Label("cap", caption)));
        }
    }
    

}

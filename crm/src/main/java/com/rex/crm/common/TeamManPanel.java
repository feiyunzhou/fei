package com.rex.crm.common;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
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
    List<String> selectedRowIds = Lists.newArrayList();
    public TeamManPanel(String id,final String en,final String entityId,final int type) {
        super(id);
        etId = entityId;
        currentEntityName = en;
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        final String userId = ((SignIn2Session)getSession()).getUserId();
        //team sql
        String teamSql = "";
        if(en.equalsIgnoreCase("account")){
          if(type == 0){
            //for the 岗位列表
            teamSql = "select * from (select  crmuser . *, accountcrmuser.id as rid,userinfo.name as userInfoName , role.name as permission from crmuser  left join accountcrmuser  ON crmuser.id = accountcrmuser.crmuserId left join user_position on user_position.positionId = accountcrmuser.crmuserId inner join userinfo on userinfo.id = user_position.userId left join role on crmuser.role = role.id where accountcrmuser.accountId = ?) as atable";
        }
//          else if (type == 1){
//          //for the 用户列表
//          teamSql = "select  * from userinfo inner join  (select  a.id as aid, b.id as rid from crmuser as a inner join accountcrmuser as b ON a.id = b.crmuserId where b.accountId = ?) as c on userinfo.positionId = c.aid";
//        } 
        }else if(en.equalsIgnoreCase("contact")){
            if(type == 0){
                //for the 岗位列表
                teamSql = "select * from (select  a . *, b.id as rid,c.name as userInfoName from crmuser as a left join contactcrmuser  as b ON a.id = b.crmuserId inner join userinfo as c on c.positionId = b.crmuserId where b.contactId = ?) as atable";
            }
//            else if (type == 1){
//              //for the 用户列表
//              teamSql = "select  * from userinfo inner join  (select  a.id as aid, b.id as rid from crmuser as a inner join contactcrmuser as b ON a.id = b.crmuserId where b.contactId = ?) as c on userinfo.positionId = c.aid";
//            } 
        }else if(en.equalsIgnoreCase("userInfo")){
          if(type == 0){
            //for the 岗位列表
           // teamSql = "select * from (select user_position.status,crmuser.name as positionId,userInfo.name as userId from user_position left join crmuser on user_position.positionId = crmuser.id left join userInfo on userInfo.id = user_position.userId where userId = ?) as atable";
          
           // teamSql = "select * from (select user_position.status,crmuser.name as positionId,userInfo.name as userId " +
           // 		"from user_position left join crmuser on user_position.positionId = crmuser.id left join userInfo on userInfo.id = user_position.userId where userId = ?) as atable";
        
              teamSql  = "select * from user_position where userId = ? order by whenadded DESC limit 1";
          }
//        else if (type == 1){
//          //for the 用户列表
//          teamSql = "select  * from userinfo inner join  (select  a.id as aid, b.id as rid from crmuser as a inner join contactcrmuser as b ON a.id = b.crmuserId where b.contactId = ?) as c on userinfo.positionId = c.aid";
//        } 
    }else if(en.equalsIgnoreCase("crmuser")){
            if(type == 0){
                //for the 医院列表
               teamSql = "select * from (select a.*,b.id as rid from account as a left join accountcrmuser as b on a.id=b.accountId where b.crmuserId=?) as atable";
            }else if(type == 1){
                //for the 医生列表
                teamSql = "select * from (select contact . * from contact left join accountcrmuser on accountcrmuser.accountId=contact.accountId where accountcrmuser.crmuserId = ?) as atable";
            }
//            else if (type == 2){
//              //for the 用户列表
//              teamSql = "select * from (select userinfo.*,user_position.status as status from  user_position left join userInfo on userInfo.id = user_position.userId where user_position.positionId = ?  ) as atable";
//            }
            else if (type == 3){
              //for the 下属岗位
              teamSql = "select * from (select crmuser.*,userInfo.name as userInfoName ,role.name as permission from  crmuser left join user_position on user_position.positionId = crmuser.id left join userInfo on userInfo.id = user_position.userId  left join role on crmuser.role = role.name where reportto = ?  ) as atable";
            }
        }
        List mapList = DAOImpl.queryEntityRelationList(teamSql, entityId);
        Entity entity=null ;
        if(en.equalsIgnoreCase("account")||en.equalsIgnoreCase("contact")){
          if(type == 0){
            entity = Configuration.getEntityByName("crmuser");
            add(new Label("title","岗位"));
            }else if(type == 1){
              entity = Configuration.getEntityByName("userinfo");
              add(new Label("title","用户"));
            } 
          }else if(en.equalsIgnoreCase("userInfo")){
          entity = Configuration.getEntityByName("user_position");
          add(new Label("title"," 用户岗位关系"));
        }else{
            if(type == 0){
        	  entity = Configuration.getEntityByName("account");
        	  add(new Label("title","医院"));
            }else if(type == 1){
                entity = Configuration.getEntityByName("contact");
                add(new Label("title","医生"));
            }
//            else if (type == 2){
//              entity = Configuration.getEntityByName("userInfo");
//              add(new Label("title","用户"));
//            }
            else if (type == 3){
              entity = Configuration.getEntityByName("crmuser");
              add(new Label("title","下属岗位"));
            }
        }
        
        List<Field> fields = entity.getFields();
        final String entityName = entity.getName();
        Form form = new Form("form");
        add(form);
        if(roleId != 1){
          WebMarkupContainer con = new WebMarkupContainer("remove_team_member_click");
            add(con);
            con.setVisible(false);
            //con.add(new AttributeAppender("style", new Model("display:none;"), ";"));
       
        }else{
        
           add(new SubmitLink("remove_team_member_click",form){
           @Override      
           public void onSubmit(){
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
               }else {
                 teamtable = "crmuser";
               }
           }else if(currentEntityName.equalsIgnoreCase("userinfo")){
        	   teamtable = "user_position";
           }
            
           for(String rid:selectedRowIds){
             try{
                   DAOImpl.removeEntityFromTeam(teamtable,rid);
                
             }catch(Exception e){
                 
             }
         }
           setResponsePage(new EntityDetailPage(currentEntityName,etId));
      
             }
           });
       }
  
        //add button submission
        if(roleId != 1){
        	 WebMarkupContainer con = new WebMarkupContainer("add_users_link");
             add(con);
             con.setVisible(false);
        }else{
        	add(new Link<Void>("add_users_link"){

                @Override
                public void onClick() {
                  this.setResponsePage(new SearchCRMUserPage(currentEntityName,entityId,userId,type));
                }
                
            });

        }
        CheckGroup group = new CheckGroup("group",new PropertyModel(this,"selectedRowIds"));
        form.add(group); 
        if(roleId == 1){
            CheckGroupSelector chks = new CheckGroupSelector("checkboxs");
            group.add(chks);
            WebMarkupContainer container_label = new WebMarkupContainer("checkboxs_label");
            group.add(container_label);
            container_label.add(new AttributeAppender("for", new Model(chks.getMarkupId()), " ")); 
        }else{
            WebMarkupContainer container = new WebMarkupContainer("checkboxs");
            container.setVisible(false);
            WebMarkupContainer container_label = new WebMarkupContainer("checkboxs_label");
            container_label.setVisible(false);
            group.add(container);
            group.add(container_label);
        }
        //set column name
        RepeatingView columnNameRepeater = new RepeatingView("columnNameRepeater");
        group.add(columnNameRepeater);
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
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        group.add(dataRowRepeater);
        for (int i = 0; i < mapList.size(); i++)
        {
            Map map = (Map)mapList.get(i);
            final String rowId =  String.valueOf(map.get("rid"));     
            final String realId =  String.valueOf(map.get("id"));  
            AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
            dataRowRepeater.add(item);
            RepeatingView columnRepeater = new RepeatingView("columnRepeater");
            item.add(columnRepeater);
            for (Field f : fields) {
                if (!f.isVisible() || f.getPriority() >1)
                    continue;
                AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model() {
                    @Override
                    public Serializable getObject() {
                        Param p = new Param();
                        p.setId(rowId);
                        p.setExtraId(realId);
                        p.setEntityName(entityName);
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
            
            WebMarkupContainer container_label = new WebMarkupContainer("checkbox_label");
            item.add(container_label);
            if(roleId == 1){
                Check chk = new Check("checkbox", new Model(String.valueOf(rowId)));
                container_label.add(new AttributeAppender("for", new Model(chk.getMarkupId()), " "));        
                item.add(chk);
            }else{
                WebMarkupContainer container = new WebMarkupContainer("checkbox");
                container.setVisible(false);
                item.add(container);
            }
         
            
        }
        add(new NewDataFormPanel("formPanel",entity,null));
    }

    public TeamManPanel(String id, IModel<?> model) {
        super(id, model);
    }
    
    private class DetailLinkFragment extends Fragment
    {
        public DetailLinkFragment(String id, String markupId, MarkupContainer markupProvider,String caption)
        {
            super(id, markupId, markupProvider);
            add(new Link("detailclick")
            {
                
                @Override
                public void onClick()
                {
                    Param p = (Param)getParent().getParent().getDefaultModelObject();
                    logger.debug(p+ " id:"+p.getId() + " name:"+p.getEntityName());
                    setResponsePage(new EntityDetailPage(p.getEntityName(),p.getExtraId()));
                    
                    //setResponsePage(new AccountDetailPage(id));
                }
            }.add(new Label("caption", new Model<String>(caption))));
        }
    }
    
    private class checkboxFragment extends Fragment {

      public checkboxFragment(String id, String markupId, MarkupContainer markupProvider,Model Imodel)
      {
        super(id, markupId, markupProvider);
        // TODO Auto-generated constructor stub
        add(new Check("checkbox",Imodel));
      }
      
    }
}

package com.rex.crm.common;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.SearchCRMUserPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.admin.AdminTreePage;
import com.rex.crm.admin.PositionTreePage;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.AccountCRMUserRelation;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.UserPosition;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;

public class TeamManPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(TeamManPanel.class);
    private String etId;
    private String currentEntityName;
    List<String> selectedRowIds = Lists.newArrayList();
    public List<Field> fieldsd;
    public TeamManPanel(String id,final String en,final String entityId,final int type) {
        super(id);
        etId = entityId;
        currentEntityName = en;
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final String user = ((SignIn2Session)getSession()).getUser();
        final String positionId = ((SignIn2Session)getSession()).getPositionId();
        //team sql
        String teamSql = "";
        if(en.equalsIgnoreCase("account")){
          if(type == 0){
            //for the 岗位列表
        	  if(roleId==1){
        		  teamSql = "select * from (select  crmuser . *, accountcrmuser.id as rid,userinfo.name as userInfoName , role.name as permission from crmuser  left join accountcrmuser  ON crmuser.id = accountcrmuser.crmuserId left join user_position on user_position.positionId = accountcrmuser.crmuserId inner join userinfo on userinfo.id = user_position.userId left join role on crmuser.role = role.id where accountcrmuser.accountId = ?) as atable";
              }else if(roleId==2){
            	  teamSql = "select * from (select  crmuser . *, accountcrmuser.id as rid,userinfo.name as userInfoName , role.name as permission from crmuser  left join accountcrmuser  ON crmuser.id = accountcrmuser.crmuserId left join user_position on user_position.positionId = accountcrmuser.crmuserId inner join userinfo on userinfo.id = user_position.userId left join role on crmuser.role = role.id where accountcrmuser.accountId = ? and crmuser.reportto = "+positionId+") as atable";
              }else{
            	  teamSql = "select * from (select  crmuser . *, accountcrmuser.id as rid,userinfo.name as userInfoName , role.name as permission from crmuser  left join accountcrmuser  ON crmuser.id = accountcrmuser.crmuserId left join user_position on user_position.positionId = accountcrmuser.crmuserId inner join userinfo on userinfo.id = user_position.userId left join role on crmuser.role = role.id where accountcrmuser.accountId = ? and crmuser.id ="+positionId+") as atable";
              }
           
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
        
              teamSql  = "select * from (select a.*,b.id as rid from account as a left join accountcrmuser as b on a.id=b.accountId left join user_position on user_position.positionId = b.crmuserId left join userinfo on userinfo.id = user_position.id where b.crmuserId=?) as atable";
          }
        else if (type == 2){
//          //for the 用户岗位
          teamSql = "select  * from   (select user_position.* ,user_position.id as rid,userinfo.name as name from  userinfo  inner join  user_position on user_position.userId = userinfo.id inner join  crmuser on crmuser.id = user_position.positionId where userinfo.id = ? ) as atable";
        } 
    }else if(en.equalsIgnoreCase("crmuser")){
            if(type == 0){
                //for the 医院列表
               teamSql = "select * from (select a.*,b.id as rid from account as a left join accountcrmuser as b on a.id=b.accountId  where b.crmuserId=? ) as atable";
            }else if(type == 1){
                //for the 医生列表
                teamSql = "select * from (select contact . * from contact left join accountcrmuser on accountcrmuser.accountId=contact.accountId where accountcrmuser.crmuserId = ?) as atable";
            }
            else if (type == 2){
              //for the 用户列表
              teamSql = "select * from (select userinfo.*,user_position.id as rid ,userinfo.name as username ,user_position.status as status , crmuser.name as positionId,role.val as crmuserRole  from  user_position left join userInfo on userInfo.id = user_position.userId left join crmuser on crmuser.id = user_position.positionId  left join role on role.id = crmuser.role where user_position.positionId = ? order by user_position.whenadded DESC limit 1 ) as atable";
              		//+ "select * from (select userinfo.*,user_position.status as status from  user_position left join userInfo on userInfo.id = user_position.userId where user_position.positionId = ?  ) as atable";
            }
            else if (type == 3){
              //for the 下属岗位	
              teamSql = "select * from (select crmuser.*,crmuser.id as rid ,userInfo.name as userInfoName ,role.name as permission from  crmuser left join user_position on user_position.positionId = crmuser.id left join userInfo on userInfo.id = user_position.userId  left join role on crmuser.role = role.name where reportto = ?  ) as atable";
            }else if (type == 4){
                //for the 区域管理	 teamSql = "select regionManage。* from user_position_query where " + getCondition(Integer.parseInt(entityId));
            	teamSql = "select user_position_query.* from user_position_query where " + getCondition(Integer.parseInt(entityId));
              }
        }
        List mapList = new ArrayList();
       if(type ==4){
    	    mapList = DAOImpl.queryEntityRelationList(teamSql);
       } else {
    	    mapList = DAOImpl.queryEntityRelationList(teamSql, entityId); 
       }
        Entity entity=null ;
        if(en.equalsIgnoreCase("account")||en.equalsIgnoreCase("contact")){
          if(type == 0){
            entity = Configuration.getEntityByName("crmuser");
            add(new Label("title","岗位"));
            }else if(type == 1){
              entity = Configuration.getEntityByName("userinfo");
              add(new Label("title","用户"));
            } 
          }
        else if(en.equalsIgnoreCase("userInfo")){
        	 entity = Configuration.getEntityByName("user_position");
             add(new Label("title"," 用户岗位关系"));
        }
        else{
            if(type == 0){
        	  entity = Configuration.getEntityByName("account");
        	  add(new Label("title","医院"));
            }else if(type == 1){
                entity = Configuration.getEntityByName("contact");
                add(new Label("title","医生"));
            }
            else if (type == 2){
              entity = Configuration.getEntityByName("userInfo");
              add(new Label("title","用户"));
            }
            else if (type == 3){
              entity = Configuration.getEntityByName("crmuser");
              add(new Label("title","下属岗位"));
            }else if (type == 4){
                entity = Configuration.getEntityByName("regionManage");
                add(new Label("title","区域管理"));
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
               }else if(type == 2){
                   teamtable = "user_position";
               }else if(type ==4){
            	   try{
                    	 delete(Integer.parseInt(entityId));
                     }catch(Exception e){
                     }    
               }else {
                 teamtable = "crmuser";
               }
           }else if(currentEntityName.equalsIgnoreCase("userinfo")){
        	   teamtable = "user_position";
           }
            
           for(String rid:selectedRowIds){
             try{
            	 int positionId = 0;
            	 int otherId = 0;
            	 String fromtable = teamtable;
            	  if(teamtable.equalsIgnoreCase("accountcrmuser")){
            		  AccountCRMUserRelation acr =  DAOImpl.getAccountsByAccountCrmuserId(Integer.parseInt(rid));
            		  positionId =  acr.getCrmuserId();
            		  otherId = acr.getAccountId();
            		  fromtable = teamtable;
            	  }else if(teamtable.equalsIgnoreCase("user_position")){
            		  UserPosition up =  DAOImpl.getUserPositionById(Integer.parseInt(rid));
            		  positionId =  up.getPositionId();
            		  otherId = up.getUserId();
            		  fromtable = "userinfo";
            	  }
                  if((type==3)&&fromtable.equalsIgnoreCase("crmuser")){
                	  DAOImpl.updateCrmUserReportById(rid, "-1");
                	  Entity entity = DAOImpl.getEntityById(fromtable, entityId);
                	  DAOImpl.insertAudit(entityName, "上级岗位", entity.getName(), "admin", rid, user);
                  }else{
                	  DAOImpl.removeEntityFromTeam(teamtable,rid);
                	  DAOImpl.insertRealtionHestory(fromtable,user,positionId,otherId);
                  }
                
             }catch(Exception e){
                 
             }
         }
           if(type == 4){
        	   PageParameters param = new PageParameters();
               param.add("positionId", entityId);
  			 setResponsePage(AdminTreePage.class,param );
           }else{
        	   if(selectedRowIds.size()>0){
        		   setResponsePage(new EntityDetailPage(currentEntityName,etId));
        	   }
           }
           
      
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
//        //add edit button
//        
//        if(roleId != 1||!currentEntityName.equalsIgnoreCase("userinfo")){
//       	 WebMarkupContainer con = new WebMarkupContainer("edit_team_member_click");
//            add(con);
//            con.setVisible(false);
//       }else{
//       	add(new Link<Void>("edit_team_member_click"){
//               @Override
//               public void onClick() {
//            	   PageParameters pp = new PageParameters();
//                   pp.add("id", entityId);
//                   pp.add("entityName", "userinfo");
//                   setResponsePage(new EditDataPage(entityName,entityId,EntityDetailPage.class,pp));
//               }
//           });
//       }
        
        
        
        CheckGroup group = new CheckGroup("group",new PropertyModel(this,"selectedRowIds"));
        form.add(group); 
        if(roleId == 1){
            if(type!=4){
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
        
                

        
        
        
         final Map<String, Map> tableData = Maps.newHashMap();
        List<String> ids = Lists.newArrayList();
        for (Map map : (List<Map>) mapList) {
            String key = String.valueOf(map.get("id"));
            ids.add(key);
            tableData.put(key, map);
        }
        fieldsd=fields;
        final PageableListView<String> listview = new PageableListView<String>("dataRowRepeater", ids, 10) {
            
          
            @Override          
            protected void populateItem(ListItem<String> item) {
                String key = item.getDefaultModelObjectAsString();
                RepeatingView columnRepeater = new RepeatingView("columnRepeater");
                Map map = tableData.get(key);
                item.add(columnRepeater);
                final String realId =  String.valueOf(map.get("id"));
                final String rowId =  String.valueOf(map.get("rid")); 
            for (Field f : fieldsd) {
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
                    // columnitem.add(new Label("celldata", value));
                    columnitem.add(new DetailLinkFragment("celldata","detailFragment",this.getParent().getParent().getParent(),value));
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
//            if(type!=4){
                item.add(container_label);
//            }else{
//            	container_label.setVisible(false);
//                item.add(container_label);
//            }
            
            if(roleId == 1){
                if(type!=4){
                	Check chk = new Check("checkbox", new Model(String.valueOf(rowId)));
                    container_label.add(new AttributeAppender("for", new Model(chk.getMarkupId()), " "));        
                    item.add(chk);
                }else{
                    WebMarkupContainer container = new WebMarkupContainer("checkbox");
                    container.setVisible(false);
                    item.add(container);
                }
            }else{
                WebMarkupContainer container = new WebMarkupContainer("checkbox");
                container.setVisible(false);
                item.add(container);
            }
         
            
        }
            
        };
        
       
        group.add(listview);
        //PagingNavigator nav = new PagingNavigator("navigator", listview);
        AjaxPagingNavigator nav =new AjaxPagingNavigator("navigator", listview);
        nav.setOutputMarkupId(true);
        
        group.setOutputMarkupId(true);
        group.setRenderBodyOnly(false);
        
        group.add(nav);
        
        group.setVersioned(false);
        
        
        
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
    private ArrayList<Integer> queryPosition(int positionId)
    {
	  	ArrayList<Integer> ids = new ArrayList<>();
	  	
	  	int level = queryPositionLevel(positionId);
	  	
	  	if(level == 11)
	  	{
	  		ids.add(positionId);
	  	}
	  	else if(level > 11)
	  	{
	  		for(int id : queryPositionByParent(positionId))
	  		{
	  			level = queryPositionLevel(positionId);
	  			
	  			if(level == 11)
	  			{
	  				ids.add(level);
	  			}
	  			else if(level > 11)
	  			{
	  				ids.addAll(queryPosition(id));
	  			}
	  		}
	  	}
	  	
	  	return ids;
    }
    
    private String getCondition(int positionId)
    {
  	  ArrayList<Integer> ids = queryPosition(positionId);
  	  StringBuilder sb = new StringBuilder();
  	  if(ids != null && ids.size() > 0)
  	  {
  		  sb = new StringBuilder();
  		  sb.append("position_id in(");
  		  sb.append(ids.get(0));
  		  ids.remove(0);
  		    		  
  		  for(int id : ids)
  		  {
  			  sb.append(",");
  			  sb.append(id);
  		  }
  		  
  		  sb.append(")");
  	  }


  	  return sb.toString();
    }
    private int queryPositionLevel(int positionId)
    {
    	int level = 100;
    	
    	Map position = DAOImpl.queryEntityById("select * from crmuser where id = ?", String.valueOf(positionId));
    	if(position.size() > 0)
    	{
    		level = (int) position.get("level");
    	}
    	
    	return level;
    }
    
    private ArrayList<Integer> queryPositionByParent(int positionId)
    {
    	List list = DAOImpl.searchCRMUserByManager(String.valueOf(positionId), "");
    	ArrayList<Integer> ids = new ArrayList<>();
    	for(Object o : list)
    	{
    		Map map = (Map) o;
    		ids.add(Integer.valueOf((Integer)map.get("id")));
    	}
    	
    	return ids;
    }
    private void delete(int positionId)
    {
    	int level = queryPositionLevel(positionId);
    	
    	if(level == 11)
    	{
    		DAOImpl.deleteAccountTeamWithPositionId(positionId);
    	}
    	else if(level > 1)
    	{
    		ArrayList<Integer> ids = queryPositionByParent(positionId);
    		for(int id : ids)
    		{
    			delete(id);
    		}
    	}
    }
   
}

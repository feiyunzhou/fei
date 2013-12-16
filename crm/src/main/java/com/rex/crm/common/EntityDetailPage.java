package com.rex.crm.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.common.collect.Maps;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivityPage;
import com.rex.crm.AlertPage;
import com.rex.crm.AreaPage;
import com.rex.crm.CoachingPage;
import com.rex.crm.ContactPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.TemplatePage;
import com.rex.crm.admin.PositionPage;
import com.rex.crm.admin.ProductPage;
import com.rex.crm.admin.UserPage;
import com.rex.crm.admin.UserPositionPage;
import com.rex.crm.beans.AccountCRMUserRelation;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.beans.UserPosition;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.db.model.Activity;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;
import com.rex.crm.util.SendEmail;

public class EntityDetailPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(EntityDetailPage.class);
    private static final long serialVersionUID = -2613412283023068638L;
    private final String user = ((SignIn2Session)getSession()).getUser();
    private final int roleId = ((SignIn2Session)getSession()).getRoleId();
    private static int NUM_OF_COLUMN  = 3;
    public EntityDetailPage(){
    	String entityId = this.getRequest().getRequestParameters().getParameterValue("id").toString();
  	  	String nameForEntity = this.getRequest().getRequestParameters().getParameterValue("entityName").toString();
  	  	if(null==entityId&&null==nameForEntity){
	    	RepeatingView div = new RepeatingView("promptDiv");
	        AbstractItem groupitem = new AbstractItem(div.newChildId());
	        Label promptButton = new Label("promptButton","X");
	        groupitem.add(promptButton);
	        final Label promptLabel = new Label("prompt","提示:操作已成功！");
	        groupitem.add(promptLabel);
	        div.add(new AttributeAppender("style",new Model("display:none"),";"));
	        groupitem.add(new AttributeAppender("style",new Model("display:none"),";"));
	        div.add(groupitem);
	        add(div);
	        add(new Label("name","null"));
	        WebMarkupContainer operationBar = new WebMarkupContainer("operationBar");
	    	add(operationBar);
	        WebMarkupContainer detailed = new WebMarkupContainer("detailed");
	    	add(detailed);
	    	WebMarkupContainer teamPanel = new WebMarkupContainer("teamPanel");
	    	add(teamPanel);
	    	WebMarkupContainer teamPanel2 = new WebMarkupContainer("teamPanel2");
	    	add(teamPanel2);
	    	WebMarkupContainer teamPanel3 = new WebMarkupContainer("teamPanel3");
	    	add(teamPanel3);
	    	WebMarkupContainer teamPanel4 = new WebMarkupContainer("teamPanel4");
	    	add(teamPanel4);
	    	RepeatingView relationRepeater = new RepeatingView("relationRepeater");
	    	AbstractItem relationItem = new AbstractItem(relationRepeater.newChildId());
	    	relationItem.add(new Label("relationPanel",""));
	    	add(relationRepeater);
  	  	}else{
  	  		initPage(nameForEntity.toString(),entityId.toString());
  	  	}

    }
    public EntityDetailPage(final String entityName, final String id){
    	initPage(entityName,id);
    }
    public void initPage(final String entityName, final String id){
        this.setPageTitle("详细信息");
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get(entityName);
        final RepeatingView div = new RepeatingView("promptDiv");
        final AbstractItem groupitem = new AbstractItem(div.newChildId());
        final Label promptButton = new Label("promptButton","X");
        groupitem.add(promptButton);
        final Label promptLabel = new Label("prompt","提示:操作已成功！");
        groupitem.add(promptLabel);
        div.add(new AttributeAppender("style",new Model("display:none"),";"));
        groupitem.add(new AttributeAppender("style",new Model("display:none"),";"));
        div.add(groupitem);
        add(div);
        long lid = Long.parseLong(id);
        Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(lid));
        if(entity.getName().equals("activity")||entity.getName().equals("coaching")||entity.getName().equals("willcoaching")){
        	add(new Label("name",String.valueOf(map.get("title"))));
        }else if(entity.getName().equals("crmuser")){
            add(new Label("name",String.valueOf(map.get("code"))));
        }else if(entity.getName().equals("user_position")){
              add(new Label("name",String.valueOf(map.get("userId"))));
        }else if(entity.getName().equals("province")||entity.getName().equals("city")){
              add(new Label("name",String.valueOf(map.get("val"))));
        }else{
              add(new Label("name",String.valueOf(map.get("name"))));
        }
        
        add(new EntityDetailPanel("detailed",entity,map,id,3,entityName));
        

        //set relations data
         List<Relation> relations = Configuration.getRelationsByName(entityName);
         
         RepeatingView relationRepeater = new RepeatingView("relationRepeater");
         add(relationRepeater);
         
         List<Field> paramFields = entity.getParamFields();
         final Map<String,Object> params = Maps.newHashMap();
         for(Field f:paramFields){
             params.put(entityName+"."+f.getName(), map.get(f.getName()));
         }
         for(Relation r:relations){
            if((r.getName().equalsIgnoreCase("accountcrmuserrelationhistory")||r.getName().equalsIgnoreCase("userposition_relation_history"))&&roleId!=1){
            	continue;
            }
            AbstractItem item = new AbstractItem(relationRepeater.newChildId());
            relationRepeater.add(item);
            logger.debug(r.getSql());
            logger.debug("parms:"+id);
            List list = DAOImpl.queryEntityRelationList(r.getSql(), id);
            item.add(new RelationDataPanel("relationPanel",r,entityName,list,String.valueOf(lid),params));
         }
         if(entityName.equalsIgnoreCase("account")){
             add(new TeamManPanel("teamPanel",entityName,String.valueOf(lid),0));
             add(new EmptyPanel("teamPanel2"));
             add(new EmptyPanel("teamPanel3"));
             add(new EmptyPanel("teamPanel4"));
         }else if(entityName.equalsIgnoreCase("crmuser")){
             add(new TeamManPanel("teamPanel",entityName,String.valueOf(lid),0));
             add(new EmptyPanel("teamPanel2"));
             add(new TeamManPanel("teamPanel3",entityName,String.valueOf(lid),2));
             add(new TeamManPanel("teamPanel4",entityName,String.valueOf(lid),3));
         }else if(entityName.equalsIgnoreCase("userInfo")){
           add(new EmptyPanel("teamPanel"));
           add(new EmptyPanel("teamPanel2"));
           add(new TeamManPanel("teamPanel3",entityName,String.valueOf(lid),2));
           add(new EmptyPanel("teamPanel4"));
       }
         else{
             add(new EmptyPanel("teamPanel"));
             add(new EmptyPanel("teamPanel2"));
             add(new EmptyPanel("teamPanel3"));
             add(new EmptyPanel("teamPanel4"));
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
         
         final Page this_page =this;
         ICRUDActionListener actionListener = new DefaultCRUDActionListener(){

            @Override
            public void delete() {
                if(entityName.equals("account")){
                	DAOImpl.deleteRecord(id, entityName);
                    setResponsePage(new AccountPage());
                }else if(entityName.equals("contact")){
                    DAOImpl.deleteRecord(id, entityName);
                    setResponsePage(new ContactPage());
                }else if(entityName.equals("activity")) {
                    DAOImpl.deleteRecord(id, entityName);
                    setResponsePage(new ActivityPage());
                }else if(entityName.equals("coaching")||entityName.equals("willcoaching")) {
                  DAOImpl.deleteRecord(id, entityName);
                  setResponsePage(new CoachingPage());
              }else if(entityName.equalsIgnoreCase("userInfo")){
                    if(DAOImpl.deleteRecord(id, entityName)>0){
                       DAOImpl.updateCrmUserReport(id, "-1");
                    }
                    setResponsePage(new UserPage());
              }else if(entityName.equals("crmuser")) {
                        DAOImpl.deleteRecord(id, entityName);
                        setResponsePage(new PositionPage());
              }else if(entityName.equals("alert")){
                	DAOImpl.deleteRecord(id, entityName);
                	setResponsePage(new AlertPage());
              }else if(entityName.equals("user_position")){
              	        DAOImpl.deleteRecord(id, entityName);
              	        setResponsePage(new UserPositionPage());
              }else if(entityName.equals("productcategory")){
                	DAOImpl.deleteRecord(id, entityName);
                	setResponsePage(new ProductPage());
              }else if(entityName.equals("product")){
                	DAOImpl.deleteProductRecord(id, entityName);
                	setResponsePage(new ProductPage());
              }else if(entityName.equals("productline")){
                	DAOImpl.deleteProductLineRecord(id, entityName);
                	setResponsePage(new ProductPage());
              }else if(entityName.equals("province")){
                        DAOImpl.deleteRecordFather(id,entityName,"city","parentId");
                	setResponsePage(new AreaPage());
              }else if(entityName.equals("city")){
                	DAOImpl.deleteRecord(id, entityName);
                	setResponsePage(new AreaPage());
              }
            }

            @Override
            public void update() {
            	if(entityName.equals("activity")||entityName.equals("coaching")||entityName.equals("willcoaching")){
            		//根据id获取对象如果是计划状态则进行操作
                    Activity activity =DAOImpl.getActivityById(Integer.parseInt(id));
                    if(activity.getStatus()==1){
                    	PageParameters pp = new PageParameters();
                        pp.add("id", id);
                        pp.add("entityName", entityName);
                        setResponsePage(new EditDataPage(entityName,id,this_page.getClass(),pp));
                    }
            	}else{
                    PageParameters pp = new PageParameters();
                    pp.add("id", id);
                    pp.add("entityName", entityName);
                    setResponsePage(new EditDataPage(entityName,id,this_page.getClass(),pp));
            	}
            	
            }
            @Override
            public void doneBtn(){
              //根据id获取对象如果是计划状态则进行操作
              Activity activity =DAOImpl.getActivityById(Integer.parseInt(id));
              if(activity.getStatus()==1){
            	  final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                  Date time = new Date();
                  String d = dateformat.format(time);
                  DAOImpl.doneRecord(id, entityName, d);
                  setResponsePage(new EntityDetailPage(entityName,id));
              }
            }
            @Override
            public void resetPassword(int userId){
            	if(DAOImpl.resetUserPassword(userId)>0){
            /*		//获取对象
                	UserInfo crmuser = DAOImpl.getUserInfoById(userId);
                	//发送邮件,判断成功与否
                	if(SendEmail.sendMail(String.valueOf(crmuser.getTs())+"_"+crmuser.getId(),crmuser.getEmail())){*/
                		div.add(new AttributeAppender("style",new Model("display:block"),";"));
                		groupitem.add(new AttributeAppender("style",new Model("display:block"),";"));
                		promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
                		promptButton.add(new AttributeAppender("style",new Model("display:block"),";"));
                	};
                	/*if(sendMail(crmuser.getLoginName(),crmuser.getEmail())){
                		//promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
                		setResponsePage(new UserPage());
                	};
            	};*/
            }

            @Override
            public void downLoadBtn() throws Exception
            {
              // TODO Auto-generated method stub
              
            }
            @Override
            public void noExecute(String entityName,int entityId){
            	//修改活动状态为未执行
            	//根据id获取对象如果是计划状态则进行操作
                Activity activity =DAOImpl.getActivityById(Integer.parseInt(id));
                if(activity.getStatus()==1){
	            	if(DAOImpl.updateActivityStatusById(entityId)){
	            		setResponsePage(new EntityDetailPage(entityName,String.valueOf(entityId)));
	            	};
                }
            }

			@Override
			public void merge() {
				// TODO Auto-generated method stub
				setResponsePage(new com.rex.crm.admin.MergePage(String.valueOf(id),null));
			}

			@Override
			public void ineffective() {
				// TODO Auto-generated method stub
				DAOImpl.updateUserInfoPositionByUserId(id);
				List<UserPosition> users = new ArrayList<UserPosition>();
				
				if(entityName.equalsIgnoreCase("userinfo")){
					DAOImpl.updateUserInfoPositionByUserId(id);
					for(UserPosition userinfo : users){
						DAOImpl.removeEntityFromTeam("user_position",String.valueOf(userinfo.getId()));
						DAOImpl.insertRealtionHestory("user_position",user,userinfo.getPositionId(),userinfo.getUserId());
					}
				}else if(entityName.equalsIgnoreCase("crmuser"))
					try {
						{
							DAOImpl.updateUserInfoPositionByUserId(id);
							users = DAOImpl.getUsersByPositionId(id);
							List<CRMUser> crmusers = DAOImpl.getPositionByReporttoId(id);
							DAOImpl.updateCrmUserReport(id, "-1");
							CRMUser reporttoCrmuser = DAOImpl.getCrmUserById(id);
							for(CRMUser crmuser :crmusers){
								DAOImpl.insertAudit("crmuser","上级岗位",reporttoCrmuser.getName(),"admin",String.valueOf(crmuser.getId()),user);
							}
							List<AccountCRMUserRelation> acrs =  DAOImpl.getAccountsByPositionId(Integer.parseInt(id));
							for(AccountCRMUserRelation acr : acrs){
								DAOImpl.removeEntityFromTeam("accountcrmuser",String.valueOf(acr.getId()));
								DAOImpl.insertRealtionHestory("accountcrmuser",user,acr.getCrmuserId(),acr.getAccountId());
							}
							for(UserPosition userinfo : users){
								DAOImpl.removeEntityFromTeam("user_position",String.valueOf(userinfo.getId()));
								DAOImpl.insertRealtionHestory("user_position",user,userinfo.getPositionId(),userinfo.getUserId());
							}
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if(entityName.equalsIgnoreCase("account")){
					List<AccountCRMUserRelation> acrs =  DAOImpl.getAccountsByAccountId((Integer.parseInt(id)));
					for(AccountCRMUserRelation acr : acrs){
						DAOImpl.removeEntityFromTeam("accountcrmuser",String.valueOf(acr.getId()));
						DAOImpl.insertRealtionHestory("accountcrmuser",user,acr.getCrmuserId(),acr.getAccountId());
					}
				}
				setResponsePage(new EntityDetailPage(entityName,id));
			}

         };


         add(new CRUDPanel("operationBar",entity.getName(),id, CRMUtility.getPermissionForEntity(roleId, entity.getName()),actionListener));
    }

}

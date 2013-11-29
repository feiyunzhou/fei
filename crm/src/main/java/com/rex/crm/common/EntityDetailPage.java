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
import com.rex.crm.CoachingPage;
import com.rex.crm.ContactPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.TemplatePage;
import com.rex.crm.admin.PositionPage;
import com.rex.crm.admin.UserPage;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;
import com.rex.crm.util.SendEmail;

public class EntityDetailPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(EntityDetailPage.class);
    private static final long serialVersionUID = -2613412283023068638L;

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
  	  		System.out.println("entity:"+nameForEntity+":"+entityId);
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
        Entity entity = entities.get(entityName);
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
        System.out.println("entityName:"+entity.getName());
        if(entity.getName().equals("activity")||entity.getName().equals("coaching")||entity.getName().equals("willcoaching")){
        	add(new Label("name",String.valueOf(map.get("title"))));
        }else if(entity.getName().equals("crmuser")){
          add(new Label("name",String.valueOf(map.get("code"))));
        }else{
        	add(new Label("name",String.valueOf(map.get("name"))));
        }
        
        add(new EntityDetailPanel("detailed",entity,map,id,3,entityName));
        

        //set relations data
         List<Relation> relations = Configuration.getRelationsByName(entityName);
         
         RepeatingView relationRepeater = new RepeatingView("relationRepeater");
         add(relationRepeater);
         
         List<Field> paramFields = entity.getParamFields();
         Map<String,Object> params = Maps.newHashMap();
         for(Field f:paramFields){
             params.put(entityName+"."+f.getName(), map.get(f.getName()));
         }
         
         
         for(Relation r:relations){
           AbstractItem item = new AbstractItem(relationRepeater.newChildId());
           relationRepeater.add(item);
           logger.debug(r.getSql());
           logger.debug("parms:"+id);
           System.out.println("rrrr:"+r.getSql());
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
                }
            }

            @Override
            public void update() {
                PageParameters pp = new PageParameters();
                pp.add("id", id);
                pp.add("entityName", entityName);
                setResponsePage(new EditDataPage(entityName,id,this_page.getClass(),pp));
            }
            @Override
            public void doneBtn(){
              final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               Date time = new Date();
              String d = dateformat.format(time);
              DAOImpl.doneRecord(id, entityName, d);
             setResponsePage(new EntityDetailPage(entityName,id));
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
            	if(DAOImpl.updateActivityStatusById(entityId)){
            		setResponsePage(new EntityDetailPage(entityName,String.valueOf(entityId)));
            	};
            }
         };
         

         add(new CRUDPanel("operationBar",entity.getName(),id, CRMUtility.getPermissionForEntity(roleId, entity.getName()),actionListener));
         
    }

}

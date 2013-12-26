package com.rex.crm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.Choice;
import com.rex.crm.common.CreateDataPage;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.db.DBHelper;
import com.rex.crm.reporter.ReportingTablePanel;
import com.rex.crm.reporter.VisitingReporter;
import com.rex.crm.util.Configuration;
import com.rexen.crm.beans.UserRole;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends TemplatePage {
    private static final long serialVersionUID = 1L;
    private String search_target = "";//add
    private final Map<String, IModel> models = Maps.newHashMap();
    public static long getNumOfAccountOfUser(String userId) {
        long size = 0;
        Connection conn = null;
        String sql = "select count(distinct account.id) as num_of_account from " +
                "accountcrmuser,account where  accountcrmuser.crmuserId=? AND account.id=accountcrmuser.accountId";
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, sql, new MapHandler(), userId);
            size = (long) map.get("num_of_account");
        } catch (Exception e) {
           
        } finally {
            DBHelper.closeConnection(conn);
        }

        return size;
    }
    
    
    public HomePage() {
        super();
        setPageTitle("主页");
        
        final String posId = ((SignIn2Session)getSession()).getPositionId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        // 业务概览注释
//        add(new Label("num_of_account_cell",DAOImpl.getNumOfAccountOfUser(posId)));
//        add(new Label("num_of_contact_cell",DAOImpl.getNumOfContactOfUser(posId)));
//        add(new Label("num_of_activity_cell",DAOImpl.getNumOfActivityOfUser(posId)));
//        
//        BookmarkablePageLink contact_link = new BookmarkablePageLink("contact_link", ContactPage.class);
//        add(contact_link);
//        BookmarkablePageLink account_link = new BookmarkablePageLink("account_link", AccountPage.class);
//        add(account_link);
//        BookmarkablePageLink activity_link = new BookmarkablePageLink("activity_link", CalendarPage.class);
//        add(activity_link);
//        
        
        
        //quick creation bar
        PageParameters param = new PageParameters();
        param.add("entityName", "contact");
        BookmarkablePageLink contact_create_link = new BookmarkablePageLink("contact_create_link", CreateDataPage.class,param );
        add(contact_create_link);
        
        WebMarkupContainer userInfo_create_li = new WebMarkupContainer("userInfo_create_li");
        param = new PageParameters();
        param.set("entityName", "userinfo");
        BookmarkablePageLink userInfo_create_link = new BookmarkablePageLink("userInfo_create_link", CreateDataPage.class,param);
        userInfo_create_li.setVisible(false);
        userInfo_create_li.add(userInfo_create_link);
        add(userInfo_create_li);
        
        if(roleId == 1){
        	userInfo_create_li.setVisible(true);
        }
       
       /* param = new PageParameters();
        param.add("entityName", "activity");
        BookmarkablePageLink activity_create_link = new BookmarkablePageLink("activity_create_link", CreateDataPage.class,param);
        add(activity_create_link);*/
        
        
        WebMarkupContainer activity_create_li = new WebMarkupContainer("activity_create_li");
        param = new PageParameters();
        param.set("en", "contact");
        param.set("excludeName", "activity");
        param.set("target",-1);                         
        BookmarkablePageLink activity_create_link = new BookmarkablePageLink("activity_create_link", SelectActivityContactPage.class,param);
        activity_create_li.setVisible(false);
        activity_create_li.add(activity_create_link);
        add(activity_create_li);
        
        if(roleId == 3){
        	activity_create_li.setVisible(true);
        }
        
        
        WebMarkupContainer coaching_create_li = new WebMarkupContainer("coaching_create_li");
        param = new PageParameters();
        param.add("entityName", "coaching");
        BookmarkablePageLink coaching_create_link = new BookmarkablePageLink("coaching_create_link", ActivitySelectPage.class,param);
        coaching_create_li.setVisible(false);
        coaching_create_li.add(coaching_create_link);
        add(coaching_create_li);
        
        if(roleId == 2){
        	coaching_create_li.setVisible(true);
        }
        
        WebMarkupContainer account_create_li = new WebMarkupContainer("account_create_li");
        param = new PageParameters();
        param.add("entityName", "account");
        BookmarkablePageLink account_create_link = new BookmarkablePageLink("account_create_link", CreateDataPage.class,param);
        account_create_li.setVisible(false);
        account_create_li.add(account_create_link);
        add(account_create_li);
        
        if(roleId == 1){
            account_create_li.setVisible(true);
        }
        
        WebMarkupContainer position_create_li = new WebMarkupContainer("position_create_li");
        param = new PageParameters();
        param.add("entityName", "crmuser");
        BookmarkablePageLink position_create_link = new BookmarkablePageLink("position_create_link", CreateDataPage.class,param);
        position_create_li.setVisible(false);
        position_create_li.add(position_create_link);
        add(position_create_li);
        
        if(roleId == 1){
        	position_create_li.setVisible(true);
        }
        
        
     
         
     Map<String, Entity> entities = Configuration.getEntityTable();
     final Entity entity = entities.get("alert");
    final String positionId = ((SignIn2Session) getSession()).getPositionId();
    List<Entity> entitys = Configuration.getEntities();
        
    
    IModel<Choice> selected_model =  new Model(new Choice(0l,"account"));
    List<Choice> entityChoice = new ArrayList<Choice>();
    final Map<String, Entity> entityList = new HashMap();
    for(Entity entityName :entitys){
    	if(entityName.isGlobalsearch()){
    		Choice choice =new Choice();
        	choice.setVal(entityName.getDisplay());
        	choice.setName(entityName.getName());
        	entityChoice.add(choice);
            entityList.put(String.valueOf(entityName.getName()), entityName); 
    	}
    }
    TextField search_input = new TextField("search_input", new PropertyModel(this, "search_target"));
    DropDownChoice search_select = new	DropDownChoice("search_select",selected_model,entityChoice, new IChoiceRenderer<Choice>() {
        @Override
        public Object getDisplayValue(Choice choice) {
            // TODO Auto-generated method stub
            return choice.getVal();
        }
        @Override
        public String getIdValue(Choice choice, int index) {
            // TODO Auto-generated method stub
            return String.valueOf(choice.getName());
        }
        
        
    });
    models.put("entityName", selected_model);
    search_select.setNullValid(false);
   
    Form form = new Form("form")
    {
    	
        @Override
        protected void onSubmit()
        {  
        	String entityName = String.valueOf(((Choice) models.get("entityName").getObject()).getName());
        	Entity  en =  entityList.get(entityName);
            String sql   = en.getSql();
            switch (roleId)
            {
                case UserRole.USER_ROLE_MANAGER:
                	 sql   = en.getSqlManager();
                    break;
                case UserRole.USER_ROLE_SALES:
                	 sql   = en.getSql();
                    break;
                case UserRole.USER_ROLE_ADMINISTRATOR:
                	 sql   = en.getSqlAdmin();
                    System.err.print(sql);
            }
            search_target = (search_target == null || search_target.equalsIgnoreCase("*")) ? "" : search_target;
            
            List<Field> searchableFields = en.getSearchableFields();
            String joint = " like '%" + search_target + "%'";
            String likequery = "";
            for (Field sf : searchableFields)
            {
                likequery = likequery + " OR " + sf.getName() + joint;
            }
            if(en.getName().equalsIgnoreCase("coaching")||en.getName().equalsIgnoreCase("activity")||en.getName().equalsIgnoreCase("willcoaching")){
            	sql = sql + " where title like '%" + search_target + "%' " + likequery;
            } else{
            	sql = sql + " where name like '%" + search_target + "%' " + likequery;
            }
            System.out.println(sql);
            List datalist = null;
            
            switch (roleId)
            {
                case UserRole.USER_ROLE_MANAGER:
                   
                	if(en.getName().equalsIgnoreCase("account")){
                		datalist = DAOImpl.queryEntityRelationList(sql, positionId, positionId,positionId);	
                	}else if(en.getName().equalsIgnoreCase("contact")){
                		datalist = DAOImpl.queryEntityRelationList(sql,positionId);
                	}else if(en.getName().equalsIgnoreCase("coaching")||en.getName().equalsIgnoreCase("activity")||en.getName().equalsIgnoreCase("willcoaching")){
                		datalist = DAOImpl.queryEntityRelationList(sql,positionId,positionId);
                	}
                    break;
                case UserRole.USER_ROLE_SALES:
                	if(en.getName().equalsIgnoreCase("account")){
                		datalist = DAOImpl.queryEntityRelationList(sql, positionId,positionId);	
                	}else if(en.getName().equalsIgnoreCase("coaching")||en.getName().equalsIgnoreCase("activity")
                			||en.getName().equalsIgnoreCase("willcoaching")||en.getName().equalsIgnoreCase("contact")){
                		datalist = DAOImpl.queryEntityRelationList(sql,positionId);
                	}
                    break;
                case UserRole.USER_ROLE_ADMINISTRATOR:
                    datalist = DAOImpl.queryEntityRelationList(sql);
                    System.err.print(sql);
            }
            
            if(entityName.equals("account")){
            	setResponsePage(new AccountPage(datalist));
            }else if(entityName.equals("contact")){
            	setResponsePage(new ContactPage(datalist));
            }else if(entityName.equals("activity")){
            	setResponsePage(new ActivityPage(datalist));
            }else if(entityName.equals("coaching")){
            	setResponsePage(new CoachingPage(datalist));
            }else if(entityName.equals("willcoaching")){
            	setResponsePage(new CoachingPage(datalist));
            }
            
        }
        
    };
//    ChoiceRenderer choiceRenderer = new ChoiceRenderer<Entity>("name","URL");
    
    add(form);
    form.add(search_select);
    form.add(search_input);
    
    
    String sql = "";
    
    switch (roleId)
    {
        case 1:
            sql = "SELECT * from  alert where  expired > now()  and publishDate < now()  ORDER BY whenadded DESC";
            break;
        case 2:
            sql = entity.getSqlManager();
            break;
        case 3:
            sql = entity.getSql();
            break;
    }
    List tdata =  null;
    //获取登录用户的positonID,从而获取crmuser获取大区
    if(((SignIn2Session)getSession()).getRoleId()!=1){   
    	CRMUser user = DAOImpl.getCrmUserById(((SignIn2Session)getSession()).getPositionId());
    	tdata = DAOImpl.queryEntityRelationList(sql,String.valueOf(user.getPl5())); 
    }else{
    	tdata = DAOImpl.queryEntityRelationList(sql);
    }
    add(new PageableTablePanel("datalist", entity, tdata, null));
      System.out.println("ds:"+((SignIn2Session)getSession()).getRoleId());
    
     if(((SignIn2Session)getSession()).getRoleId()==3){   
         final Entity entityAct = entities.get("todolist"); 
         String userName = ((SignIn2Session) getSession()).getUser();
         String sql2="SELECT * FROM crmdb.activity_alert where name='"+userName+"'";
         List tdataAct = DAOImpl.queryEntityRelationList(sql2);
         //Entity Fordetail = entities.get("activity"); 
         add(new PageableTablePanel("datalistAct", entityAct, tdataAct, null));
     }else if(((SignIn2Session)getSession()).getRoleId()==2){   
         final Entity entityAct = entities.get("todolistcoach"); 
         String userName = ((SignIn2Session) getSession()).getUser();
         String sql2="SELECT * FROM crmdb.activity_alert where name='"+userName+"'";
         List tdataAct = DAOImpl.queryEntityRelationList(sql2);
         //Entity Fordetail = entities.get("activity"); 
         add(new PageableTablePanel("datalistAct", entityAct, tdataAct, null));
     }
     else{
          WebMarkupContainer container_label = new WebMarkupContainer("datalistAct");
          container_label.setVisible(false);
          add( container_label);
     } 

    }
}

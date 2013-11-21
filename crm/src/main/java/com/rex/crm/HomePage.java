package com.rex.crm;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

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
        add(new Label("num_of_account_cell",DAOImpl.getNumOfAccountOfUser(posId)));
        add(new Label("num_of_contact_cell",DAOImpl.getNumOfContactOfUser(posId)));
        add(new Label("num_of_activity_cell",DAOImpl.getNumOfActivityOfUser(posId)));
        
        BookmarkablePageLink contact_link = new BookmarkablePageLink("contact_link", ContactPage.class);
        add(contact_link);
        BookmarkablePageLink account_link = new BookmarkablePageLink("account_link", AccountPage.class);
        add(account_link);
        BookmarkablePageLink activity_link = new BookmarkablePageLink("activity_link", CalendarPage.class);
        add(activity_link);
        
        
        
        //quick creation bar
        PageParameters param = new PageParameters();
        param.add("entityName", "contact");
        BookmarkablePageLink contact_create_link = new BookmarkablePageLink("contact_create_link", CreateDataPage.class,param );
        add(contact_create_link);
      
       
       /* param = new PageParameters();
        param.add("entityName", "activity");
        BookmarkablePageLink activity_create_link = new BookmarkablePageLink("activity_create_link", CreateDataPage.class,param);
        add(activity_create_link);*/
        
        
        WebMarkupContainer activity_create_li = new WebMarkupContainer("activity_create_li");
        param = new PageParameters();
        param.add("entityName", "activity");
        BookmarkablePageLink activity_create_link = new BookmarkablePageLink("activity_create_link", CreateDataPage.class,param);
        activity_create_li.setVisible(false);
        activity_create_li.add(activity_create_link);
        add(activity_create_li);
        
        if(roleId == 3){
        	activity_create_li.setVisible(true);
        }
        
        
        WebMarkupContainer coaching_create_li = new WebMarkupContainer("coaching_create_li");
        param = new PageParameters();
        param.add("entityName", "coaching");
        BookmarkablePageLink coaching_create_link = new BookmarkablePageLink("coaching_create_link", CreateDataPage.class,param);
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
        
        
        /**
         * 目标医生拜访频率统计
         */
        String cap = "目标医生拜访频率统计";
        List<Map> visitingFreq = VisitingReporter.generateVisitingFrequencyReportingByUserId(posId);
        Entity reportEntity = Configuration.getEntityByName("contactVisitingFrequency");
        ReportingTablePanel visiting_report_panel = new ReportingTablePanel("visiting_frequency_report_panel", cap, reportEntity, visitingFreq);
        add(visiting_report_panel);
        /**
        * 目标医生拜访覆盖率统计
        */
        cap = "目标医生拜访覆盖率统计";
        List<Map> visitingCoverList = VisitingReporter.generateVisitingCoverReportingByUserId(posId);
        Entity report_Entity = Configuration.getEntityByName("contactVisitingCoverRate");
        ReportingTablePanel visiting_reportPanel = new ReportingTablePanel("visiting_cover_report_panel", cap, report_Entity, visitingCoverList);
        add(visiting_reportPanel);

        cap = "区域内工作天数";
        List<Map> workingdays = VisitingReporter.generateWorkingDayReportingByUserId(posId);
        Entity reportEntity2 = Configuration.getEntityByName("workingday");
        ReportingTablePanel report_panel = new ReportingTablePanel("working_day_report_panel", cap, reportEntity2, workingdays);
        report_panel.add(new AttributeAppender("style",new Model("display:block;"),";"));
        add(report_panel);
       

        cap = "日均拜访量";
        List<Map> visitingPerDay = VisitingReporter.generateVisitingPerDay(visitingFreq,workingdays);

        if (visitingPerDay != null && visitingPerDay.size() > 0) {
            Entity reportentity = Configuration.getEntityByName("visitingPerDayReport");
            ReportingTablePanel reportPanel = new ReportingTablePanel("num_of_visiting_per_day_report_panel", cap, reportentity, visitingPerDay);
            add(reportPanel);
        } else {
            add(new WebMarkupContainer("num_of_visiting_per_day_report_panel").setVisible(false));
        }
        
        
        // add(new Label("activity_name","活动名称"));
        // add(new Label("day_name","昨天"));
         
         

  

         Map<String, Entity> entities = Configuration.getEntityTable();
       final Entity entity = entities.get("alert");


    final String positionId = ((SignIn2Session) getSession()).getPositionId();
    
        
            Form form;
        form = new Form("form")
        {
            
            @Override
            protected void onSubmit()
            {
                String sql = entity.getSql();
                switch (roleId)
                {
                    case 1:
                        sql = entity.getSqlAdmin();
                        break;
                    case 2:
                        sql = entity.getSqlManager();
                        break;
                    case 3:
                        sql = entity.getSql();
                        break;
                }
                
                search_target = (search_target == null || search_target.equalsIgnoreCase("*")) ? "" : search_target;
                
                List<Field> searchableFields = entity.getSearchableFields();
                String joint = " like '%" + search_target + "%'";
                String likequery = "";
                for (Field sf : searchableFields)
                {
                    likequery = likequery + " OR " + sf.getName() + joint;
                }
                sql = sql + " where name like '%" + search_target + "%' " + likequery;
                System.out.println(sql);
                List datalist = null;
                
                switch (roleId)
                {
                    case UserRole.USER_ROLE_MANAGER:
                        datalist = DAOImpl.queryEntityRelationList(sql, positionId, positionId, positionId);
                        break;
                    case UserRole.USER_ROLE_SALES:
                        datalist = DAOImpl.queryEntityRelationList(sql, positionId, positionId);
                        break;
                    case UserRole.USER_ROLE_ADMINISTRATOR:
                        datalist = DAOImpl.queryEntityRelationList(sql);
                        System.err.print(sql);
                }
                
                
                
            }
            
        };
    add(form);

    TextField search_input = new TextField("search_input", new PropertyModel(this, "search_target"));
    form.add(search_input);
    
    String sql = "select * from alert limit 3";
    List tdata = DAOImpl.queryEntityRelationList(sql);
        
    add(new PageableTablePanel("datalist", entity, tdata, null));
        
    final Entity entityAct = entities.get("todolist"); 
    
    
//    
//    String sql2="SELECT id," +
//"title" +
//"FROM crmdb.activity act" +
//"where event_type=1 and status=1 order by a";
    
    String sql2="SELECT * FROM crmdb.activity_alert ";
    
//    String sql2="Select * from activity";
    System.out.print("!!!!!!!!!!!!!!!!!!!"+sql2);
    
    
    List tdataAct = DAOImpl.queryEntityRelationList(sql2);
    add(new PageableTablePanel("datalistAct", entityAct, tdataAct, null));
        
        IModel<Integer> textModel1 = new Model<>(0);
        try
        {
            textModel1 = new Model<>(Integer.parseInt(visitingFreq.get(0).get("Chart1").toString()));
        }
        catch (Exception e)
        {
            System.out.println("无数据");
        }
        finally
        {
            add(new TextField("data001").add(new AttributeModifier("value", textModel1)));
        }
        
        
//        IModel<Integer> textModel2a = new Model<>(0);
//        try
//        {
//            textModel2a = new Model<>(Integer.parseInt(visitingCoverList.get(0).get("Chart2a").toString()));
//        }
//        catch (Exception e)
//        {
//            System.out.println("无数据");
//        }
//        finally
//        {
//            add(new TextField("data002a").add(new AttributeModifier("value", textModel2a)));
//        }
        
        
        IModel<Integer> textModel2b = new Model<>(0);
        try
        {
            textModel2b = new Model<>(Integer.parseInt(visitingCoverList.get(0).get("Chart2b").toString()));
        }
        catch (Exception e)
        {
            System.out.println("无数据");
        }
        finally
        {
            add(new TextField("data002b").add(new AttributeModifier("value", textModel2b)));
        }

        
        IModel<Integer> textModel3 = new Model<>(0);
        try
        {
             textModel3 = new Model<>(Integer.parseInt(workingdays.get(0).get("Chart3").toString()));
        }
        catch (Exception e)
        {
            System.out.println("无数据");
        }
        finally
        {
            add(new TextField("data003").add(new AttributeModifier("value", textModel3)));
        }
        
        
        IModel<Integer> textModel4 = new Model<>(0);
        try
        {
             textModel4 = new Model<>(Integer.parseInt(visitingPerDay.get(0).get("Chart4").toString()));
        }
        catch (Exception e)
        {
            System.out.println("无数据");
        }
        finally
        {
            add(new TextField("data004").add(new AttributeModifier("value", textModel4)));
        }
        

    }
}

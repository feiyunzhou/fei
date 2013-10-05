package com.rex.crm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.common.collect.Maps;
import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.CalendarPanel;
import com.rex.crm.common.ContactTeamManPanel;
import com.rex.crm.common.CreateDataPage;
import com.rex.crm.common.Entity;
import com.rex.crm.common.EntityDetailPanel;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.NewDataFormPanel;
import com.rex.crm.common.Relation;
import com.rex.crm.common.RelationDataPanel;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.reporter.ReportingTablePanel;
import com.rex.crm.reporter.VisitingReporter;
import com.rex.crm.util.Configuration;

public class HomePage extends TemplatePage {
    private static final long serialVersionUID = 1L;

    public HomePage() {
        super();
        setPageTitle("主页");
        
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        add(new Label("num_of_account_cell",DAOImpl.getNumOfAccountOfUser(userId)));
        add(new Label("num_of_contact_cell",DAOImpl.getNumOfContactOfUser(userId)));
        add(new Label("num_of_activity_cell",DAOImpl.getNumOfActivityOfUser(userId)));
        
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
      
       
        BookmarkablePageLink activity_create_link = new BookmarkablePageLink("activity_create_link", CreateEventPage.class);
        add(activity_create_link);
        
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
        
        
        
        /**
         * 目标医生拜访频率统计
         */
        String cap = "目标医生拜访频率统计";
        List<Map> visitingFreq = VisitingReporter.generateVisitingFrequencyReportingByUserId(userId);
        
        if (visitingFreq != null && visitingFreq.size() > 0) {
            Entity reportEntity = Configuration.getEntityByName("contactVisitingFrequency");
            ReportingTablePanel visiting_report_panel = new ReportingTablePanel("visiting_frequency_report_panel", cap, reportEntity, visitingFreq);
            add(visiting_report_panel);
        } else {
            add(new WebMarkupContainer("visiting_frequency_report_panel").setVisible(false));
        }
        /**
        * 目标医生拜访覆盖率统计
        */
        cap = "目标医生拜访覆盖率统计";
        List<Map> visitingCoverList = VisitingReporter.generateVisitingCoverReportingByUserId(userId);

        if (visitingCoverList != null && visitingCoverList.size() > 0) {
            Entity reportEntity = Configuration.getEntityByName("contactVisitingCoverRate");
            ReportingTablePanel visiting_report_panel = new ReportingTablePanel("visiting_cover_report_panel", cap, reportEntity, visitingCoverList);
            add(visiting_report_panel);
        } else {
            add(new WebMarkupContainer("visiting_cover_report_panel").setVisible(false));
        }
        
        
        
        
        cap = "区域内工作天数";
        List<Map> workingdays = VisitingReporter.generateWorkingDayReportingByUserId(userId);

        if (workingdays != null && workingdays.size() > 0) {
            Entity reportEntity = Configuration.getEntityByName("workingday");
            ReportingTablePanel report_panel = new ReportingTablePanel("working_day_report_panel", cap, reportEntity, workingdays);
            add(report_panel);
        } else {
            add(new WebMarkupContainer("working_day_report_panel").setVisible(false));
        }
        
        
        
        cap = "日均拜访量";
        List<Map> visitingPerDay = VisitingReporter.generateVisitingPerDay(visitingFreq,workingdays);

        if (visitingPerDay != null && visitingPerDay.size() > 0) {
            Entity reportEntity = Configuration.getEntityByName("visitingPerDayReport");
            ReportingTablePanel report_panel = new ReportingTablePanel("num_of_visiting_per_day_report_panel", cap, reportEntity, visitingPerDay);
            add(report_panel);
        } else {
            add(new WebMarkupContainer("num_of_visiting_per_day_report_panel").setVisible(false));
        }
        
        
    }
}

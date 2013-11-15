package com.rex.crm.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivitySelectPage;
import com.rex.crm.DataPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.CRUDPanel.Permissions;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rexen.crm.integration.DataExportDelegate;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class DownLoadPage extends DataPage{
    public DownLoadPage(){
       init();
    }

	public void init()
	{
	  final int roleId = ((SignIn2Session)getSession()).getRoleId();
		Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("downLoad");
//        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        
        add(new Link("account") {

          @Override
          public void onClick() {
            DataExportDelegate dataExport = new  DataExportDelegate();
//          String template = DAOImpl.selectTemplate();
          String teample = "Account Export Full Template 1.0";
         HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
         try
        {
          dataExport.export(teample, response);
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
          setResponsePage(new UpLoadPage());
          }
      });
        add(new Link("contact") {

          @Override
          public void onClick() {
            DataExportDelegate dataExport = new  DataExportDelegate();
//          String template = DAOImpl.selectTemplate();
          String teample = "Contact Export Full Template 1.0";
         HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
         try
        {
          dataExport.export(teample, response);
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
          setResponsePage(new UpLoadPage());
          }
      });
        add(new Link("position") {

          @Override
          public void onClick() {
            DataExportDelegate dataExport = new  DataExportDelegate();
//          String template = DAOImpl.selectTemplate();
          String teample = "Position Export Full Template 1.0";
         HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
         try
        {
          dataExport.export(teample, response);
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
          setResponsePage(new UpLoadPage());
          }
      });
        add(new Link("activity") {

          @Override
          public void onClick() {
            DataExportDelegate dataExport = new  DataExportDelegate();
//          String template = DAOImpl.selectTemplate();
          String teample = "Activity Export Full Template 1.0";
         HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
         try
        {
          dataExport.export(teample, response);
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
          setResponsePage(new UpLoadPage());
          }
      });
//        add(new Link("accountTeam") {
  //
//          @Override
//          public void onClick() {
//            DataExportDelegate dataExport = new  DataExportDelegate();
////          String template = DAOImpl.selectTemplate();
//          String teample = "Account Team Export Full Template 1.0";
//         HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
//         try
//        {
//          dataExport.export(teample, response);
//        }
//        catch (Exception e)
//        {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
//          setResponsePage(new UpLoadPage());
//          }
//      });
        add(new Link("activityCrmuser") {

          @Override
          public void onClick() {
            DataExportDelegate dataExport = new  DataExportDelegate();
//          String template = DAOImpl.selectTemplate();
          String teample = "ActivityUser Export Full Template 1.0";
         HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
         try
        {
          dataExport.export(teample, response);
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
          setResponsePage(new UpLoadPage());
          }
      });  
	}
}
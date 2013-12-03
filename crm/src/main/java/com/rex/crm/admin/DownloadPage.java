package com.rex.crm.admin;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.link.Link;
import com.rex.crm.SignIn2Session;
import com.rexen.crm.integration.DataExportDelegate;
import com.rex.crm.common.Entity;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class DownloadPage extends AdminTemplatePage{
    
    public DownloadPage(){
    	setPageTitle("系统管理-下载数据"); 
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
          String teample = "Account  Export Full Template 1.0";
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
         setResponsePage(new DownloadPage());
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
         setResponsePage(new DownloadPage());
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
         setResponsePage(new DownloadPage());
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
         setResponsePage(new DownloadPage());
          }
      });
        add(new Link("accountCrmuser") {

          @Override
          public void onClick() {
            DataExportDelegate dataExport = new  DataExportDelegate();
//          String template = DAOImpl.selectTemplate();
          String teample = "Account Team Export Full Template 1.0";
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
         setResponsePage(new DownloadPage());
          }
      });  
        add(new Link("userPosition") {

          @Override
          public void onClick() {
            DataExportDelegate dataExport = new  DataExportDelegate();
//          String template = DAOImpl.selectTemplate();
          String teample = "UserPosition Team Export Full Template 1.0";
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
         setResponsePage(new DownloadPage());
          }
      }); 
	}
}
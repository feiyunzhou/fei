package com.rex.crm.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.rex.crm.CreateEventPage;
import com.rex.crm.EventViewerPage;
import com.rex.crm.SignIn2Session;



public class CalendarPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(CalendarPanel.class);

    public CalendarPanel(String id) {
        super(id);       
        //TODO Get permission info of user from database.

        //add(new CRUDPanel("operationBar","calendar",EnumSet.of(CRUDPanel.Permissions.ADD)));

    }
    
    

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        Map<String, Object> map = new HashMap<>();
        //TODO get userID from session
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        map.put("user_event_data", com.rex.crm.ajax.DataProvider.getEventsByUserId(new String[]{userId}));
        PageParameters params = new PageParameters();
        WebPage page = null;
        if(roleId==1){
        	page = new CreateEventPage();
        }else if(roleId==2){
        	page = new CreateDataPage(null,null);   
        }else{
        	page = new CreateDataPage(null,null);   
        }
        System.out.println("page:"+page);
        CharSequence pageUrl = getRequestCycle().urlFor(new RenderPageRequestHandler(new PageProvider(page)));
        String url = pageUrl.toString();
        url =  url.substring(0, url.indexOf("?")+1);
        map.put("create_event_page_url", url);
        CharSequence viewPageUrl = getRequestCycle().urlFor(new RenderPageRequestHandler(new PageProvider(new EventViewerPage())));
        url = viewPageUrl.toString();
        url =  url.substring(0, url.indexOf("?")+1);
        map.put("event_view_page_url", url);
       // map.put("context_name",getRootContext());
 
        PackageTextTemplate ptt = new PackageTextTemplate(getClass(),"calendar.js");
        //logger.debug(ptt.asString(map));
        response.render(JavaScriptHeaderItem.forScript(ptt.asString(map), null));
        try {
            ptt.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public CalendarPanel(String id, IModel<?> model) {
        super(id, model);
    }
    
    
    public static String getRootContext(){
        
        String rootContext = "";
 
        WebApplication webApplication = WebApplication.get();
        if(webApplication!=null){
            ServletContext servletContext = webApplication.getServletContext();
            if(servletContext!=null){
                rootContext = servletContext.getServletContextName();
            }else{
                //do nothing
            }
        }else{
            //do nothing
        }
 
        return rootContext;
 
     }

}

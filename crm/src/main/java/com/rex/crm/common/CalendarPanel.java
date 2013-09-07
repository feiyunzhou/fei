package com.rex.crm.common;

import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import org.apache.wicket.markup.html.panel.Panel;

import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;

import org.apache.wicket.util.template.PackageTextTemplate;



public class CalendarPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(CalendarPanel.class);

    public CalendarPanel(String id) {
        super(id);       
        //TODO Get permission info of user from database.
        add(new CRUDPanel("operationBar","calendar",null,EnumSet.of(CRUDPanel.Permissions.ADD)));
    }
    
    

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        Map<String, Object> map = new HashMap<>();
        //TODO get userID from session
        String userId = "20";
        map.put("user_event_data", com.rex.crm.ajax.DataProvider.getEventsByUserId(new String[]{userId}));
        map.put("context_name", getRootContext());
 
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

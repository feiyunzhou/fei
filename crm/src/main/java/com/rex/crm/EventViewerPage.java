package com.rex.crm;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.Contact;
import com.rex.crm.common.EditDataPage;
import com.rex.crm.common.Entity;
import com.rex.crm.common.EntityDetailPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class EventViewerPage extends TemplatePage
{
    
    private static final Logger logger = Logger.getLogger(EventViewerPage.class);
    public Date startDate;
    protected String endDate;
    protected String endTime;
    protected String startTime;
    protected String hidden_contact_select;
	/**
	 * Constructor
	 */
	public EventViewerPage()
	{
		System.out.println("无参数");
		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        final String posId = ((SignIn2Session)getSession()).getPositionId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        StringValue eventIdValue = this.getRequest().getRequestParameters().getParameterValue("eventid");
        //final long eventId
        long eid = 0;
        if(!eventIdValue.isEmpty()  && !eventIdValue.isNull()){
            eid = eventIdValue.toLong();
        }
        final long eventId = eid;
        System.out.println("eventID:"+eventId);
        Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(eventId));
        //if the complete and edit button visible;
        boolean write_btn_visible = true;
        //get the event status
        int status = 2;
        if(map != null){
            Object st = map.get("act_status");
             status = ((Number)st).intValue();      
        }

        if(status == 1){
            write_btn_visible =true;
        }else{
            write_btn_visible =false;
        }
        
        if (map != null) {
            int eventType = ((Number) map.get("event_type")).intValue();
            logger.debug("eventType:"+eventType);
            if (eventType == 2 && roleId == 3) {
                // for the sales rep, no permission to edit the coaching event
                write_btn_visible = false;
            }
        }
        
        Form form = new Form("form"){
            @Override
            protected void onSubmit()
            {
                //update status of calenderEvent mark it to be completed
            	Date act_end_datetime = new Date();
                DAOImpl.updateStatusOfCalendarEvent((int)eventId, 2,act_end_datetime);
                setResponsePage(PageFactory.createPage("calendar"));
               
            }
        };
        add(form);
        int eventType = 0;
        if(map != null){
        	 eventType = ((Number) map.get("event_type")).intValue();
             logger.debug("eventType:"+eventType);
        }
        final int event_type  = eventType;  
        System.out.println("event_type:"+eventType);
        Link edit_event_btn = new Link("edit_event_btn") {
            @Override
            public void onClick() {
                //判断活动类型
            	if(event_type==1){
            		 setResponsePage(new EventEditorPage(eventId));
            	}else{
            		setResponsePage(new EventCoachEditorPage(eventId));
            	}
               
            }
        };
        edit_event_btn.setVisible(write_btn_visible);
        form.addOrReplace(edit_event_btn);
 
        Link delete_event_btn = new Link("delete_event_btn") {
            
            @Override
            public void onClick() {
        		 DAOImpl.deleteRecord( String.valueOf(eventId), "activity");
                 setResponsePage(PageFactory.createPage("calendar"));
            }
        };
        delete_event_btn.setVisible(write_btn_visible);
        form.addOrReplace(delete_event_btn);
        if(map!= null){
           //获取当前时间
            Date currentDate = new Date();
            logger.debug("currentDate:"+currentDate);;
            //获取开始时间
            Date startDate = new Date(((Number)map.get("starttime")).longValue());
            logger.debug("startDate:"+startDate);
            //如果两个时间比较大于0则开始时间大于当前时间
            if(startDate.compareTo(currentDate)>0){
              logger.debug("计划时间晚于当前时间");
              write_btn_visible = false;
            }
        }
        WebMarkupContainer complete_btn = new WebMarkupContainer("complete_btn");
        complete_btn.setVisible(write_btn_visible);
        form.add(complete_btn);
        
        // List<CalendarEvent> events = DAOImpl.getEventsByUserId(Integer.parseInt(uid));
         logger.debug("eventID is:"+ eventId);
         
         //add(new Label("name",String.valueOf(map.get("name"))));
         add(new EntityDetailPanel("detailed",entity,map,String.valueOf(eventId),1,"calendar"));
	}
	
	public EventViewerPage(String id)
	{
		System.out.println("id:"+id);
		Map<String, Entity> entities = Configuration.getEntityTable();
		final Entity entity  = entities.get("activity");
	    setPageTitle(entity.getDisplay());
	    final int roleId = ((SignIn2Session)getSession()).getRoleId();
	    final int eventId = Integer.parseInt(id);
	    logger.debug("entity"+entity);
	    Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(eventId));
	    logger.debug("entity.getSql_ent()"+entity.getSql_ent());
	    //if the complete and edit button visible;
	    boolean write_btn_visible = true;
	    //get the event status
	    int status = 2;
	      
	    if(map != null){
	        Object st = map.get("act_status");
	         status = ((Number)st).intValue();      
	    }

	    if(status == 1){
	        write_btn_visible =true;
	    }else{
	        write_btn_visible =false;
	    }
	    
	    if (map != null) {
            int eventType = ((Number) map.get("event_type")).intValue();
            if (eventType == 2 && roleId == 3) {
                // for the sales rep, no permission to edit the coaching event
                write_btn_visible = false;
            }
        }//	    logger.debug("roleId" + roleId);
	    
	    Form form = new Form("form"){
	        @Override
	        protected void onSubmit()
	        {
	            //update status of calenderEvent mark it to be completed
	            Date act_end_datetime = new Date();
	            DAOImpl.updateStatusOfCalendarEvent((int)eventId, 2,act_end_datetime);
	            if(entity.getName().equals("activity")){
	               setResponsePage(new ActivityPage());
	             }else{
	               setResponsePage(PageFactory.createPage("calendar"));  
	             }
	        }
	    };
	    add(form);
	    int eventType = 0;
        if(map != null){
        	 eventType = ((Number) map.get("event_type")).intValue();
             logger.debug("eventType:"+eventType);
        }
        final int event_type  = eventType;  
        System.out.println("event_type:"+eventType);
	    Link edit_event_btn = new Link("edit_event_btn") {
	        
	        @Override
	        public void onClick() {
	        	if(event_type==1){
	        		setResponsePage(new EventEditorPage(eventId));
	        	}else{
	        		setResponsePage(new EventCoachEditorPage(eventId));
	        	}
	        }
	    };
	    edit_event_btn.setVisible(write_btn_visible);
	    form.addOrReplace(edit_event_btn);

	    Link delete_event_btn = new Link("delete_event_btn") {
	        
	        @Override
	        public void onClick() {
	            
	            DAOImpl.deleteRecord( String.valueOf(eventId), "activity");
	            if(entity.getName().equals("activity")){
                setResponsePage(new ActivityPage());
              }else{
                setResponsePage(PageFactory.createPage("calendar"));  
              }
	        }
	    };
	    delete_event_btn.setVisible(write_btn_visible);
	    form.addOrReplace(delete_event_btn);
	    if(map!= null){
       	 //获取当前时间
           Date currentDate = new Date();
           logger.debug("currentDate:"+currentDate);;
           //获取开始时间
           Date startDate = new Date(((Number)map.get("starttime")).longValue());
           logger.debug("startDate:"+startDate);
           //如果两个时间比较大于0则开始时间大于当前时间
           if(startDate.compareTo(currentDate)>0){
           	logger.debug("计划时间晚于当前时间");
           	write_btn_visible = false;
           }
       }
	    WebMarkupContainer complete_btn = new WebMarkupContainer("complete_btn");
	    complete_btn.setVisible(write_btn_visible);
	    form.add(complete_btn);
	    
	    // List<CalendarEvent> events = DAOImpl.getEventsByUserId(Integer.parseInt(uid));
	     logger.debug("eventID is:"+ eventId);
	     
	     //add(new Label("name",String.valueOf(map.get("name"))));
	     add(new EntityDetailPanel("detailed",entity,map,String.valueOf(eventId),1,"calendar"));
	     
	    
	}

	class SelectOption implements Serializable{
	    private int key;
	    private String value;
	   
	    public SelectOption(int key, String value) {
	      this.key = key;
	      this.value = value;
	    }
	    public SelectOption() {
	          
	        }

	    public int getKey() {
	        return key;
	    }

	    public void setKey(int key) {
	        this.key = key;
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }
	  }
}




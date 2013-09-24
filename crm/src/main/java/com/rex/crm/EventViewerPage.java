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
	    

		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        final String uid = ((SignIn2Session)getSession()).getUserId();
        StringValue eventIdValue = this.getRequest().getRequestParameters().getParameterValue("eventid");
        //final long eventId
       
        long eid = 0;
        if(!eventIdValue.isEmpty()  && !eventIdValue.isNull()){
            eid = eventIdValue.toLong();
            
        }
        final long eventId = eid;
        Form form = new Form("form"){
            @Override
            protected void onSubmit()
            {
                //update status of calenderEvent mark it to be completed
                DAOImpl.updateStatusOfCalendarEvent((int)eventId, 2);
               
               setResponsePage(PageFactory.createPage("calendar"));
               
            }
        };
        add(form);
        
        
        
        // List<CalendarEvent> events = DAOImpl.getEventsByUserId(Integer.parseInt(uid));
         logger.debug("eventID is:"+ eventId);
         Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(eventId));
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




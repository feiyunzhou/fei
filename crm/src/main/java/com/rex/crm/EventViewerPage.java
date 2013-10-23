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
        StringValue eventIdValue = this.getRequest().getRequestParameters().getParameterValue("eventid");
        long eid = 0;
        if(!eventIdValue.isEmpty()  && !eventIdValue.isNull()){
            eid = eventIdValue.toLong();  
        }
    final long eventId = eid;
    initPage(eid,0);
  }
  
  public EventViewerPage(String id) {
     
      final int eventId = Integer.parseInt(id);
      initPage(eventId,1);
  }

public void initPage(final long eid,final int type ){
  Map<String, Entity> entities = Configuration.getEntityTable();
  Entity entity  = entities.get("activity");
  setPageTitle(entity.getDisplay());
  final String uid = ((SignIn2Session)getSession()).getUserId();
  final int roleId = ((SignIn2Session)getSession()).getRoleId();
  Map map = DAOImpl.queryEntityById(entity.getSql_ent(), String.valueOf(eid));
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
                DAOImpl.updateStatusOfCalendarEvent((int)eid, 2,act_end_datetime);
                //判断类型如果是辅导类型则跳转到评分界面
               if(type == 0){
                setResponsePage(PageFactory.createPage("calendar")); 
               }else{
                 setResponsePage(new ActivityPage());
               }
               
            }
        };
        add(form);
        
        Link edit_event_btn = new Link("edit_event_btn") {
            
            @Override
            public void onClick() {
                
                setResponsePage(new EventEditorPage(eid));
            }
        };
        edit_event_btn.setVisible(write_btn_visible);
        form.addOrReplace(edit_event_btn);
 
        Link delete_event_btn = new Link("delete_event_btn") {
            
            @Override
            public void onClick() {
                
                DAOImpl.deleteRecord( String.valueOf(eid), "activity");
               if(type == 0){
                setResponsePage(PageFactory.createPage("calendar")); 
               }else{
                 setResponsePage(new ActivityPage());
               }
            }
        };
        delete_event_btn.setVisible(write_btn_visible);
        form.addOrReplace(delete_event_btn);
        
        WebMarkupContainer complete_btn = new WebMarkupContainer("complete_btn");
        complete_btn.setVisible(write_btn_visible);
        form.add(complete_btn);
        
        // List<CalendarEvent> events = DAOImpl.getEventsByUserId(Integer.parseInt(uid));
         logger.debug("eventID is:"+ eid);
         
         //add(new Label("name",String.valueOf(map.get("name"))));
         add(new EntityDetailPanel("detailed",entity,map,String.valueOf(eid),1,"calendar"));
         
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




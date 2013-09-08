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
import com.rex.crm.beans.Contact;
import com.rex.crm.common.Entity;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class CreateEventPage extends TemplatePage
{
    
    private static final Logger logger = Logger.getLogger(CreateEventPage.class);
    public Date startDate;
    protected String endDate;
    protected String endTime;
    protected String startTime;
    protected String hidden_contact_select;
	/**
	 * Constructor
	 */
	public CreateEventPage()
	{
	    

		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        final String uid = ((SignIn2Session)getSession()).getUserId();
        
        Form form = new Form("form"){
            @Override
            protected void onSubmit()
            {
                logger.debug("the form was submitted!");
                
                //logger.debug("startDate:"+  startDate);
                String sd = getRequest().getPostParameters().getParameterValue("start_date").toString();
                String st = getRequest().getPostParameters().getParameterValue("start_time").toString();
                String ed = getRequest().getPostParameters().getParameterValue("end_date").toString();
                String et = getRequest().getPostParameters().getParameterValue("end_time").toString();
                String visit_type = getRequest().getPostParameters().getParameterValue("visit_type").toString();
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String sdt = sd+ " " +st;
                String edt = ed + " " + et;
                Date startdt = null;
                Date enddt = null;
                try {
                     startdt = dateformat.parse(sdt);
                     enddt = dateformat.parse(edt);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                logger.debug(String.format("time info %s %s %s %s", sd,st,ed,et));
                
                logger.debug("contact id:"+ hidden_contact_select);
                logger.debug("visit_type:" + visit_type);
                try {
                    
                    DAOImpl.addCalendarEvent(Integer.parseInt(uid), Integer.parseInt(hidden_contact_select), visit_type, visit_type, String.valueOf(startdt.getTime()/1000), String.valueOf(enddt.getTime()/1000), 1);
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                setResponsePage(PageFactory.createPage("calendar"));
                
            }
        };
        add(form);
        
        
         StringValue startdateValue = this.getRequest().getRequestParameters().getParameterValue("startdate");
         
         String startdate  = null;
         long current = System.currentTimeMillis();
         Date current_date_time = new Date(current);
         SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
         if(startdateValue != null){
              startdate = startdateValue.toString();
         }else{
             
             
             startdate = dateformat.format(current_date_time);
         }
         logger.debug("startdate:"+startdate);
         
         
         WebMarkupContainer start_date_input = new WebMarkupContainer("start_date_input");
         //startDate = startdate;
         //DateTextField start_date_input = new DateTextField("start_date_input", new PropertyModel<Date>(this,"startDate"));
         form.add(start_date_input);
         start_date_input.add(new AttributeModifier("value",startdate));
         
         SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");
         WebMarkupContainer start_time_input = new WebMarkupContainer("start_time_input");
         form.add(start_time_input);
         start_time_input.add(new AttributeModifier("value",timeformatter.format(current_date_time)));
         
         Date sd = current_date_time;
         try {
             sd = dateformat.parse(startdate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         Date next_date_time = new Date(current_date_time.getTime()+3600*1000);
         WebMarkupContainer end_date_input = new WebMarkupContainer("end_date_input");
         form.add(end_date_input);
         end_date_input.add(new AttributeModifier("value",dateformat.format(next_date_time)));
         
         

         
         WebMarkupContainer end_time_input = new WebMarkupContainer("end_time_input",new Model(""));
         form.add(end_time_input);
         end_time_input.add(new AttributeModifier("value",timeformatter.format(next_date_time)));
         logger.debug("BSDFSDFSDF:"+ timeformatter.format(next_date_time));
         
        
//         Entity et = entities.get("account");
//         final String userId = ((SignIn2Session)getSession()).getUserId();
//         List mapList = DAOImpl.queryEntityRelationList(et.getSql(), userId);
//         
//         List<SelectOption> accounts = Lists.newArrayList();
//         for(Map map:(List<Map>)mapList){
//             SelectOption at = new SelectOption();
//             at.setKey(((Number)(map.get("id"))).intValue());
//             at.setValue((String)map.get("name"));
//             accounts.add(at);
//         }
//        
//         //SelectOption[] options = new SelectOption[] {new SelectOption("&", "AND"), new SelectOption("|", "OR")};
//         ChoiceRenderer choiceRenderer = new ChoiceRenderer("value", "key");
//        
//        add(new DropDownChoice("account_select", accounts, choiceRenderer));
//      
         
         
         

         form.add(new HiddenField("hidden_contact_select" ,new PropertyModel<String>(this,"hidden_contact_select")));
         form.add(new TextField("contact_select", new Model("")));
        
        
        
        
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




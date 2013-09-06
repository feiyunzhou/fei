package com.rex.crm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.string.StringValue;

import com.rex.crm.common.Entity;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class CreateEventPage extends TemplatePage
{
    
    private static final Logger logger = Logger.getLogger(CreateEventPage.class);
	/**
	 * Constructor
	 */
	public CreateEventPage()
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        
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
         add(start_date_input);
         start_date_input.add(new AttributeModifier("value",startdate));
         
         SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
         WebMarkupContainer start_time_input = new WebMarkupContainer("start_time_input");
         add(start_time_input);
         start_time_input.add(new AttributeModifier("value",timeformatter.format(current_date_time)));
         
         Date sd = current_date_time;
         try {
             sd = dateformat.parse(startdate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         Date next_date_time = new Date(sd.getTime()+3600);
         WebMarkupContainer end_date_input = new WebMarkupContainer("end_date_input");
         add(end_date_input);
         end_date_input.add(new AttributeModifier("value",dateformat.format(next_date_time)));
         
         

         
         WebMarkupContainer end_time_input = new WebMarkupContainer("end_time_input");
         add(end_time_input);
         end_time_input.add(new AttributeModifier("value",timeformatter.format(next_date_time)));
         
        
         
        
        
        
		
	}
}
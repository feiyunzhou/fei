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
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.Choice;
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
    protected long visiting_purpose = 1L;
    protected long feature_product = 1L;
    protected long activity_type = 1L;
    protected String act_title_input = "";
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
                //String visit_type = getRequest().getPostParameters().getParameterValue("visit_type").toString();
                String visit_type = String.valueOf(activity_type);
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
                    
                    DAOImpl.addCalendarEvent(Integer.parseInt(uid), Integer.parseInt(hidden_contact_select), visit_type, act_title_input, String.valueOf(startdt.getTime()/1000), String.valueOf(enddt.getTime()/1000), 1);
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
         //set default time to current time
         if(startdateValue != null && !startdateValue.isEmpty() && !startdateValue.isNull()){
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
         
         SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         
         Date sDate = current_date_time;
         try {
             sDate = dateformat2.parse(startdate+ " " + timeformatter.format(current_date_time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.warn("failed to parse date:",e);
        }
         
         
         Date next_date_time = new Date(sDate.getTime()+3600*1000);
         WebMarkupContainer end_date_input = new WebMarkupContainer("end_date_input");
         form.add(end_date_input);
         end_date_input.add(new AttributeModifier("value",dateformat.format(next_date_time)));
         
         

         
         WebMarkupContainer end_time_input = new WebMarkupContainer("end_time_input",new Model(""));
         form.add(end_time_input);
         end_time_input.add(new AttributeModifier("value",timeformatter.format(next_date_time)));
              
         
         PopupSettings popupSettings = new PopupSettings("查找").setHeight(470)
                 .setWidth(850).setLeft(150).setTop(200);
           form.add(new BookmarkablePageLink<Void>("search_btn", SearchContactPage.class).setPopupSettings(popupSettings));


         form.add(new HiddenField("hidden_contact_select" ,new PropertyModel<String>(this,"hidden_contact_select")));
         form.add(new TextField("contact_select", new Model("")));
        
         form.add(createDropDownListFromPickList("activity_type_input","activity_activity_types_pl",new PropertyModel(this,"activity_type")));
         form.add(createDropDownListFromPickList("visiting_purpose_input","activity_visiting_purpose_pl",new PropertyModel(this,"visiting_purpose")));
         form.add(createDropDownListFromPickList("feature_product_input","activity_feature_product_pl",new PropertyModel(this,"feature_product")));
         form.add(new TextField("act_title_input", new PropertyModel(this,"act_title_input")));
        
        
	}
	
	private DropDownChoice createDropDownListFromPickList(String markupId, String picklistName,PropertyModel default_model){
	    
	    List<Choice> pickList = DAOImpl.queryPickList(picklistName);
        final Map<Long, String> list = Maps.newHashMap();
        List<Long> ids = Lists.newArrayList();
        for (Choice p : pickList) {
            list.put(p.getId(), p.getVal());
            ids.add(p.getId());
        }
	    
	  return new DropDownChoice<Long>(markupId, default_model, ids,
                new IChoiceRenderer<Long>() {

                    @Override
                    public Object getDisplayValue(Long id) {
                        // TODO Auto-generated method stub
                        return list.get(id);
                    }

                    @Override
                    public String getIdValue(Long id, int index) {
                        return String.valueOf(id);
                    }

                });
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




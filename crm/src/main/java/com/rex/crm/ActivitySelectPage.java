package com.rex.crm;

import java.util.Map;
import java.util.Set;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.google.common.collect.Maps;
import com.rex.crm.common.CreateDataPage;

public class ActivitySelectPage extends TemplatePage{
	/**
	 * Constructor
	 */
	public ActivitySelectPage(){
		 //传参时间和实体名
        StringValue entityName = getRequest().getRequestParameters().getParameterValue("entityName");
        final String name = entityName.toString();
        Set<String> names = getRequest().getRequestParameters().getParameterNames();
        final Map<String,Object> map = Maps.newHashMap();
        for(String nm:names){
            StringValue sv = getRequest().getRequestParameters().getParameterValue(nm);
            map.put(nm, sv.toString());
        }
        if(("activity").equals(name)){
        	initPage(name,map);
        }else{
        	Form form = new Form("buttonForm");
        	form.add(new Button("callCoachBtn") {
                @Override
                public void onSubmit() {
                	setResponsePage(new CreateDataPage("coaching", map));
                }
            });
        	form.add(new Link("willCoachBtn") {
                 @Override
                 public void onClick() {
                	 setResponsePage(new CreateDataPage("willCoaching", map));
                 }
             });
            add(form);
        }
	}
	public void initPage(final String name,final Map<String,Object> params){
		setResponsePage(new CreateDataPage(name,params));
	}
}

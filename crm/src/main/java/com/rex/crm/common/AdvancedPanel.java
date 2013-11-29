package com.rex.crm.common;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpRetryException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivitySelectPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.db.model.Activity;
import com.rex.crm.util.CRMUtility;
import com.rexen.crm.integration.DataAccessObject;
import com.rexen.crm.integration.DataExport;
import com.rexen.crm.integration.DataExportDelegate;

public class AdvancedPanel extends Panel {
    
	private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(PageableTablePanel.class);
    
    public AdvancedPanel(String id,Entity entity) {
		super(id);
		// TODO Auto-generated constructor stub
		
		RepeatingView dataRowRepeater = new RepeatingView("datarowrepeater"); 
		add(dataRowRepeater);
		AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
		dataRowRepeater.add(item);
		RepeatingView dataColumeRepeater = new RepeatingView("datacolumnrepeater");
		item.add(dataColumeRepeater);
		List<String> fieldNames = entity.getDisplayNames();
		for(String name :fieldNames ){
			AbstractItem columeName = new AbstractItem(dataColumeRepeater.newChildId());
			columeName.add(new Label(name));
			dataColumeRepeater.add(columeName);
		}
		 Form form = new Form("searchform"){
				@Override
				protected void onSubmit(){
					
				}
			};
			form.add(dataRowRepeater);
			add(form);
	}
   
	
}

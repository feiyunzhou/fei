/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rex.crm;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.behavior.AttributeAppender;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.rex.crm.ajax.DataProvider;
import com.rex.crm.ajax.FunctionClass;
import com.rex.crm.ajax.FunctionInvoker;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.common.DownLoadPage;
import com.rex.crm.common.UpLoadPage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.userlog.LogInOut;
import com.rex.crm.util.CRMUtility;

/**
 * 
 * @author Dong
 */
public abstract class DataPage extends AuthenticatedWebPage {
	/** title of the current page. */
	private String pageTitle = "DataPageTest";
	 private static final Logger logger = Logger.getLogger(DataPage.class);

    protected static ImmutableMap<String,MenuItem>  pageMenuMap;
    
    static{
        //TODO Load them from configuration
        ImmutableMap.Builder<String, MenuItem> builder = new ImmutableMap.Builder<String,MenuItem>();
        MenuItem item = new MenuItem();
        item.setCaption("<i class=\"icon-upload-alt icon-large\"></i>上传数据");
        item.setDestination(UpLoadPage.class);
        item.setId("navitem-upLoad");
        builder.put("upLoad", item);
        
        item = new MenuItem();
        item.setCaption("<i class=\"icon-download-alt icon-large\"></i>下载数据");
        item.setDestination(DownLoadPage.class);
        item.setId("navitem-downLoad");
        builder.put("downLoad", item);
        
        pageMenuMap = builder.build();
    	 
    }
	/**
	 * Constructor
	 */
	public DataPage() {
		
//	  final int roleId = ((SignIn2Session) getSession()).getRoleId();
		add(new Label("title", new PropertyModel<String>(this, "pageTitle")));
//		UserInfo user = DAOImpl.getUserInfoById(Integer.parseInt(((SignIn2Session) getSession()).getUserId()));
		
		//TODO get function work with real id
	    List<String> menulist = Lists.newArrayList();
  	    menulist.add("upLoad");
        menulist.add("downLoad");
	    ArrayList<MenuItem> menu = Lists.newArrayList();
	      for(String key:menulist){
	          menu.add(pageMenuMap.get(key));
	      }
	      ListView lv = new ListView("datamenu", menu) {
            @Override
            protected void populateItem(ListItem item) {
                MenuItem menuitem = (MenuItem) item.getModelObject();
                BookmarkablePageLink link = new BookmarkablePageLink("link", menuitem.getDestination());
                link.add(new Label("caption", menuitem.getCaption()).setEscapeModelStrings(false));
                item.add(link);
                item.add(new AttributeAppender("id", Model.of(menuitem.getId())));
            }
        };  
        add(lv);
        
      
	}
}



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
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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
import com.rex.crm.common.CalendarPanel;
import com.rex.crm.db.DAOImpl;

/**
 * 
 * @author Feiyun Zhou
 */
public abstract class TemplatePage extends AuthenticatedWebPage {
	/** title of the current page. */
	private String pageTitle = "TemplatePageTest";
	 private static final Logger logger = Logger.getLogger(TemplatePage.class);

    protected static ImmutableMap<String,MenuItem>  pageMenuMap;
    
    static{
        //TODO Load them from configuration
        ImmutableMap.Builder<String, MenuItem> builder = new ImmutableMap.Builder<String,MenuItem>();
        MenuItem item = new MenuItem();
        item.setCaption("<i class=\"icon-home icon-large\"></i>主页");
        item.setDestination(HomePage.class);
        item.setId("navitem-homepage");
        builder.put("home", item);
        
        item = new MenuItem();
        item.setCaption("<i class=\"icon-hospital icon-large\"></i>医院");
        item.setDestination(AccountPage.class);
        item.setId("navitem-account");
        builder.put("account", item);
        
        item = new MenuItem();
        item.setCaption("<i class=\"icon-user-md icon-large\"></i>医生");
        item.setDestination(ContactPage.class);
        item.setId("navitem-contact");
        builder.put("contact", item);
        
        item = new MenuItem();
        item.setCaption("<i class=\"icon-calendar icon-large\"></i>日历");
        item.setDestination(CalendarPage.class);
        item.setId("navitem-calendar");
        builder.put("calendar", item);

        
        item = new MenuItem();
        item.setCaption("<i class=\"icon-bolt icon-large\"></i>活动");
        item.setDestination(ActivityPage.class);
        item.setId("navitem-activity");
        builder.put("activity", item);
        
//        item = new MenuItem();
//        item.setCaption("<i class=\"icon-truck icon-large\"></i>经销商");
//        item.setDestination(DealerAccountPage.class);
//        item.setId("navitem-dealerAccount");
//        builder.put("dealerAccount", item);
//        
//        item = new MenuItem();
//        item.setCaption("<i class=\"icon-umbrella icon-large\"></i>经销商联系人");
//        item.setDestination(DealerContactPage.class);
//        item.setId("navitem-dealerContact");
//        builder.put("dealerContact", item);
        
        item = new MenuItem();
        item.setCaption("<i class=\"icon-user icon-large\"></i>用户");
        item.setDestination(UserPage.class);
        item.setId("navitem-crmuser");
        builder.put("user", item);
        
        pageMenuMap = builder.build();
    	 
    }
	/**
	 * Constructor
	 */
	public TemplatePage() {
		add(new Label("title", new PropertyModel<String>(this, "pageTitle")));
		
		//TODO get function work with real id
		List<String> menulist = DAOImpl.getMenuByUserId("test");
		
		//populate menu items. TODO componentize it. pass arg: menuList
		ArrayList<MenuItem> menu = Lists.newArrayList();
	    for(String key:menulist){
	        menu.add(pageMenuMap.get(key));
	    }
        
        //@SuppressWarnings("unchecked")
        ListView lv = new ListView("menu", menu) {
            @Override
            protected void populateItem(ListItem item) {
                MenuItem menuitem = (MenuItem) item.getModelObject();
                BookmarkablePageLink link = new BookmarkablePageLink("link", menuitem.getDestination());
                link.add(new Label("caption", menuitem.getCaption()).setEscapeModelStrings(false));
                item.add(link);
                item.add(new AttributeAppender("id", Model.of(menuitem.getId())));
               // item.add(new SimpleAttributeModifier("class", "my-css-class"));
                
            }
        };  
        add(lv);
        
        //end of populate menu items
		
		AbstractAjaxBehavior ajaxBehaviour = new AbstractAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onRequest() {
				RequestCycle requestCycle = RequestCycle.get();
				readRequestData(requestCycle);
			}

			private void readRequestData(final RequestCycle requestCycle) {
				WebRequest wr = (WebRequest) requestCycle.getRequest();

				HttpServletRequest hsr = (HttpServletRequest) wr
						.getContainerRequest();
				String response = "{}";
				try {
					BufferedReader br = hsr.getReader();

					String jsonString = br.readLine();
					if ((jsonString == null) || jsonString.isEmpty()) {
					    logger.debug(" no json found");
					} else {
						logger.debug(" json  is :" + jsonString);
					}
					FunctionClass method = new Gson().fromJson(jsonString,
							FunctionClass.class);

					FunctionInvoker invoker = new FunctionInvoker(
							DataProvider.class);
					System.out.println(" method:" + method);
					response = (String) invoker.invoke(method.getF(),
							method.getP());
				System.out.println("response:"+response);

					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

//				requestCycle
//						.scheduleRequestHandlerAfterCurrent(new TextRequestHandler(
//								"application/json", "UTF-8", response));
				requestCycle.replaceAllRequestHandlers(new TextRequestHandler(
								"application/json", "UTF-8", response));

			}

			@Override
			public void renderHead(Component component, IHeaderResponse response) {
				super.renderHead(component, response);

				 final String userId = ((SignIn2Session)getSession()).getUserId();
				String callbackUrl = getCallbackUrl().toString();

				Map<String, Object> map = new HashMap<>();
				map.put("userInfo",DataProvider.getCRMUserInfoById(new String[]{userId}));
				map.put("ajaxURL", callbackUrl);
				//map.put("allUsers", DataProvider.getAllCRMUsers(new String[0]));
				//map.put("allAccounts", DataProvider.getAllAccounts(new String[0]));
                map.put("context_name",getRootContext());
                
				PackageTextTemplate ptt = new PackageTextTemplate(getClass(),"template.js");
                //System.out.println(ptt.asString(map));
				response.render(JavaScriptHeaderItem.forScript(ptt.asString(map), null));
				try {
					ptt.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
		add(ajaxBehaviour);

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

	/**
	 * Gets the title.
	 * 
	 * @return title
	 */
	public final String getPageTitle() {
		return pageTitle;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            title
	 */
	public final void setPageTitle(String title) {
		pageTitle = title;
	}
}

class MenuItem implements  IModel {
    /** the caption of the menu item */
    private String caption;
    /** the (bookmarkable) page the menu item links to */
    private Class destination;
    private String id;
    
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public Class getDestination() {
        return destination;
    }
    public void setDestination(Class destination) {
        this.destination = destination;
    }
    @Override
    public void detach() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Object getObject() {
        // TODO Auto-generated method stub
        return this;
    }
    @Override
    public void setObject( Object item) {
        this.caption = ((MenuItem)item).getCaption();
        this.destination = ((MenuItem)item).getDestination();
        
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
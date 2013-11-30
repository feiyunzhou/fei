package com.rex.crm.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.rex.crm.ActivitySelectPage;
import com.rex.crm.CreateEventPage;
import com.rex.crm.EventViewerPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.ajax.DataProvider;
import com.rex.crm.ajax.FunctionClass;
import com.rex.crm.ajax.FunctionInvoker;
import com.rex.crm.beans.AdvancedSearchField;
import com.rex.crm.beans.AdvancedSearchFilter;
import com.rex.crm.beans.Choice;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;



public class AdvancedSearchPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(AdvancedSearchPanel.class);

    private String entityName;
    
    
    public AdvancedSearchPanel(String id,final String entityName) {
        super(id);
        
        this.entityName = entityName;
        
        
        AbstractAjaxBehavior ajaxBehaviour = new AbstractAjaxBehavior() {

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
                    Gson gson = new Gson();
                    AdvancedSearchFilter[] filters = gson.fromJson(jsonString, AdvancedSearchFilter[].class);
                    
                    for(AdvancedSearchFilter ft:filters){
                        
                    }

                    
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                requestCycle.replaceAllRequestHandlers(new TextRequestHandler(
                                "application/json", "UTF-8", response));

            }
            
            
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);

               
                String callbackUrl = getCallbackUrl().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("ad_search_ajax_url", callbackUrl);
                PackageTextTemplate ptt = new PackageTextTemplate(getClass(),"advancedSearch.js");
                
                
                Entity entity = Configuration.getEntityByName(entityName);
                List<Field> fields = entity.getSearchableFields();
                
                AdvancedSearchField[] advancedFields = new AdvancedSearchField[fields.size()];
                int i = 0;
                for(Field f:fields){  
                    advancedFields[i] = new AdvancedSearchField();
                    if (f.getPicklist() != null) {
                        
                        List<Choice> list = DAOImpl.queryPickList(f.getPicklist());
                        AdvancedSearchField[] pickList = new AdvancedSearchField[list.size()];
                        int j = 0;
                        for(Choice choice:list){  
                            pickList[j] = new AdvancedSearchField();
                            pickList[j].setId(String.valueOf(choice.getId()));
                            pickList[j].setLabel(choice.getVal());
                            j++;
                        }
                        advancedFields[i].setList(pickList);
                        advancedFields[i].setType("lov");
                        
                    }else{
                        advancedFields[i].setType(f.getDataType());
                    }
                    advancedFields[i].setId(f.getName());
                    advancedFields[i].setLabel(f.getDisplay());
                   
                    i++;   
                }
                
                Gson gson = new Gson();      
                map.put("search_fields", gson.toJson(advancedFields, AdvancedSearchField[].class));
               
               // PackageTextTemplate ptt = new PackageTextTemplate(getClass(),"advancedSearch.js");
                
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


    public AdvancedSearchPanel(String id, IModel<?> model) {
        super(id, model);
    }
    

}

package com.rex.crm.common.advancedSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivitySelectPage;
import com.rex.crm.ContactPage;
import com.rex.crm.CreateEventPage;
import com.rex.crm.EventViewerPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.admin.UserPage;
import com.rex.crm.ajax.DataProvider;
import com.rex.crm.ajax.FunctionClass;
import com.rex.crm.ajax.FunctionInvoker;
import com.rex.crm.beans.AdvancedSearchField;
import com.rex.crm.beans.AdvancedSearchFilter;
import com.rex.crm.beans.Choice;
import com.rex.crm.beans.FilterMeta;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;



public class AdvancedSearchPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(AdvancedSearchPanel.class);

    private static Map<String,String> operator2NameMap = Maps.newHashMap();
    static{
        operator2NameMap.put("eq", "=");
        operator2NameMap.put("ne", "!=");
        operator2NameMap.put("sw", "前缀为");
        operator2NameMap.put("ct", "包含");
        operator2NameMap.put("fw", "后缀为");
        operator2NameMap.put("in", "属于");
        operator2NameMap.put("null", "为空");
        operator2NameMap.put("nn", "不为空");
        operator2NameMap.put("gt", "大于");
        operator2NameMap.put("lt", "小于");
        operator2NameMap.put("bw", "介于之间");
    }
   // private String entityName;
    
    
    public AdvancedSearchPanel(String id,final String entityName,final AdvancedSearchFilter[] filters) {
        super(id);
        
       // this.entityName = entityName;
       final Entity entity = Configuration.getEntityByName(entityName);
       final String target_url ;
      if(entity.getName().equalsIgnoreCase("account")){
    	    target_url = urlFor(AccountPage.class, null).toString();
      }else if(entity.getName().equalsIgnoreCase("contact")){
    	    target_url = urlFor(ContactPage.class, null).toString();
      }else if(entity.getName().equalsIgnoreCase("userinfo")){
    	    target_url = urlFor(UserPage.class, null).toString();
      }else{
    	    target_url = urlFor(AccountPage.class, null).toString();  
      }
       
      
       Form form = new Form("form"){

        @Override
        protected void onSubmit() {
            IRequestParameters parameters = getRequestCycle().getRequest().getPostParameters();
           if(!parameters.getParameterValue("elem").isNull() && ! parameters.getParameterValue("elem").isEmpty()){
               int num_of_elem = parameters.getParameterValue("elem").toInt();
               AdvancedSearchFilter[] filters =  new AdvancedSearchFilter[num_of_elem];
               for(int i=0;i<num_of_elem;i++){
                   filters[i] = new AdvancedSearchFilter();
                   
                   //for the field
                   FilterMeta meta = new FilterMeta();
                   Field field = entity.getFieldByName(parameters.getParameterValue("fld-"+(i+1)).toString());
                   meta.setValue(parameters.getParameterValue("fld-"+(i+1)).toString());
                   meta.setLabel(field.getDisplay());
                   filters[i].setField(meta);
                   
                   //for the operator
                    meta = new FilterMeta();
                    meta.setValue(parameters.getParameterValue("op-"+(i+1)).toString());
                    meta.setLabel(operator2NameMap.get(parameters.getParameterValue("op-"+(i+1)).toString()));
                    filters[i].setOperator(meta);
                    
                    //for the value
                    meta = new FilterMeta();             
                    String label = parameters.getParameterValue("val-"+(i+1)).toString();
                    String value = parameters.getParameterValue("val-"+(i+1)).toString();
                    if(field.getPicklist()!=null){
                        Iterable<String> splits = Splitter.on(",").split(value);
                        List<String> labelList = Lists.newArrayList();
                        Iterator<String> it = splits.iterator();
                        while(it.hasNext()){
                            String id = it.next();
                            String val = DAOImpl.queryPickListByIdCached(field.getPicklist(), id);
                            labelList.add(val);
                        }
                        label = Joiner.on(",").join(labelList);
                    }
                    meta.setValue(value);
                    meta.setLabel(label);
                    filters[i].setValue(meta); 
               }
               
               
              // String filter_sql = buildFilterSQL(entity,filters);
              // logger.debug("filter string is:"+filter_sql);
              
             //PageParameters params = new PageParameters();
            // params.add("ad_search_sql", filter_sql);
             setResponsePage(new AdvancedSearchPage(entityName,filters));
               
           }
           
        }
           
           
       };
       add(form);
        
        AbstractAjaxBehavior ajaxBehaviour = new AbstractAjaxBehavior() {

            @Override
            public void onRequest() {
                RequestCycle requestCycle = RequestCycle.get();
                readRequestData(requestCycle);              
            }

            private void readRequestData(final RequestCycle requestCycle) {
                WebRequest wr = (WebRequest) requestCycle.getRequest();

                HttpServletRequest hsr = (HttpServletRequest) wr.getContainerRequest();
                String response = "{}";
                String filterString = "";
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
                    filterString = buildFilterSQL(entity, filters);
                    
                    System.out.println(filterString);
                    
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                PageParameters parameters = new PageParameters();
                parameters.add("ad_search_sql", filterString);
               
                String responseStr = target_url + "?ad_search_sql="+ filterString;
                logger.debug(responseStr);

                 JsonPrimitive jp = new JsonPrimitive(responseStr);
           
               logger.debug(jp.toString());
                requestCycle.scheduleRequestHandlerAfterCurrent(new TextRequestHandler( "application/json", "UTF-8", jp.toString()));
               //requestCycle.replaceAllRequestHandlers();
              // requestCycle.setResponsePage(AccountPage.class, parameters );

            }


            
            
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);

               
                String callbackUrl = getCallbackUrl().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("ad_search_ajax_url", callbackUrl);
                //map.put("target_url", target_url);
                PackageTextTemplate ptt = new PackageTextTemplate(getClass(),"advancedSearch.js");
                
                
                
                List<Field> fields = Lists.newArrayList();
                for(Field f:entity.getFields()){
                    if(!f.isVisible()) continue;
                    fields.add(f);
                }  
                        
                
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
                String default_search_filters = "[]";
                if(filters!=null){
                    default_search_filters = gson.toJson(filters,AdvancedSearchFilter[].class);
                }
                map.put("default_search_filters", default_search_filters);
                
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
        
       // add(new PageableTablePanel("datalist", entityName, tdata, null));
        
    }

    
    

    public AdvancedSearchPanel(String id, IModel<?> model) {
        super(id, model);
    }
    
    
    public String buildFilterSQL(final Entity entity, AdvancedSearchFilter[] filters) {
        String filterString;
        List<String> combinedFilters = Lists.newArrayList();
        for(AdvancedSearchFilter ft:filters){
           String fieldName = ft.getField().getValue();
           Field field = entity.getFieldByName(fieldName);
           
           if(field.getDataType().equalsIgnoreCase("number")){
               if(field.getPicklist()!=null){
                   
                   if(ft.getOperator().getValue().equalsIgnoreCase("in")|| ft.getOperator().getValue().equalsIgnoreCase("eq")){
                       combinedFilters.add(ft.getField().getValue() +" in ("+ ft.getValue().getValue()+") ");
                   }
                  
               }else{
                 
                   if(ft.getOperator().getValue().equalsIgnoreCase("eq")){
                       combinedFilters.add(ft.getField().getValue() +" = "+ ft.getValue().getValue());
                   }else if(ft.getOperator().getValue().equalsIgnoreCase("gt")){
                       combinedFilters.add(ft.getField().getValue() +" > "+ ft.getValue().getValue());
                   }else if(ft.getOperator().getValue().equalsIgnoreCase("lt")){
                       combinedFilters.add(ft.getField().getValue() +" < "+ ft.getValue().getValue());
                   }else if(ft.getOperator().getValue().equalsIgnoreCase("ne")){
                       combinedFilters.add(ft.getField().getValue() +" != "+ ft.getValue().getValue());
                   }else if(ft.getOperator().getValue().equalsIgnoreCase("null")){
                       combinedFilters.add(ft.getField().getValue() +" is null ");
                   }else if(ft.getOperator().getValue().equalsIgnoreCase("nn")){
                       combinedFilters.add(ft.getField().getValue() +" is not null ");
                   }
                   
               }
           }else{
               if(ft.getOperator().getValue().equalsIgnoreCase("eq")){
                   combinedFilters.add(ft.getField().getValue() +" = '"+ ft.getValue().getValue()+"'");
               }else if(ft.getOperator().getValue().equalsIgnoreCase("ne")){
                   combinedFilters.add(ft.getField().getValue() +" != '"+ ft.getValue().getValue()+"'");
               }else if(ft.getOperator().getValue().equalsIgnoreCase("ct")){
                   combinedFilters.add(ft.getField().getValue() +" like '%"+ ft.getValue().getValue()+"%'");
               }else if(ft.getOperator().getValue().equalsIgnoreCase("null")){
                   combinedFilters.add(ft.getField().getValue() +" is null ");
               }else if(ft.getOperator().getValue().equalsIgnoreCase("nn")){
                   combinedFilters.add(ft.getField().getValue() +" is not null ");
               }
           }
        }

         filterString = Joiner.on(" AND ").join(combinedFilters);
        return filterString;
    }
    
    

}
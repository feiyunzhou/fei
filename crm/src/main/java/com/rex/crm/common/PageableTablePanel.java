package com.rex.crm.common;

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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivitySelectPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rexen.crm.integration.DataAccessObject;
import com.rexen.crm.integration.DataExportDelegate;

public class PageableTablePanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(PageableTablePanel.class);
    
    public PageableTablePanel(String id, final Entity entity, List mapList,final Map<String,Object> params) {
        super(id);

        add(new Label("table_title",entity.getDisplay()));
        // TODO Get the table definition from database or configuration
        final String primaryKeyName = entity.getPrimaryKeyName();
        final List<Field> fields = entity.getFields();
        final List<String> fn = entity.getFieldNames();
        final String entityName = entity.getName();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        // RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        // add(dataRowRepeater);

        final Map<String, Map> tableData = Maps.newHashMap();
        List<String> ids = Lists.newArrayList();
        for (Map map : (List<Map>) mapList) {
            String key = String.valueOf(map.get("id"));
            ids.add(key);
            tableData.put(key, map);
        }

        final WebMarkupContainer datacontainer = new WebMarkupContainer("data");
        datacontainer.setOutputMarkupId(true);
        add(datacontainer);

        // set column name
        RepeatingView columnNameRepeater = new RepeatingView("columnNameRepeater");
        datacontainer.add(columnNameRepeater);
        int count=0;
        for (Field f : entity.getFields()) {
            if (!f.isVisible()|| f.getPriority() >1)
                continue;
            AbstractItem item = new AbstractItem(columnNameRepeater.newChildId());
            if(count==0){
                item.add(new AttributeAppender("class", new Model("table-first-link"), " "));
                count++;
            }
            columnNameRepeater.add(item);
            item.add(new Label("columnName", f.getDisplay()));
        }
        // end of set column name
        final PageableListView<String> listview = new PageableListView<String>("dataRowRepeater", ids, 15) {
            @Override
            protected void populateItem(ListItem<String> item) {
                String key = item.getDefaultModelObjectAsString();
                RepeatingView columnRepeater = new RepeatingView("columnRepeater");
                Map map = tableData.get(key);
                item.add(columnRepeater);
                final String rowId = String.valueOf(map.get(primaryKeyName));
                for (Field f : fields) {
                    if (!f.isVisible() || f.getPriority() >1)
                        continue;
                    AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model() {
                        @Override
                        public Serializable getObject() {
                            Param p = new Param();
                            p.setId(rowId);
                            p.setEntityName(entityName);
                            return p;
                        }

                    });
                    if (f.isDetailLink()) {
                        String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                        if(value.equals("null")||value.isEmpty()){
                          value = "无";
                        }
                        columnitem.add(new DetailLinkFragment("celldata", "detailFragment", this.getParent().getParent(), value,entity));
                        columnitem.add(new AttributeAppender("class", new Model("table-first-link"), " "));
//                        columnitem.add(new ButtonFragment("celldata","buttonFragment",this,"鍒犻櫎"));
                    } else {
                        if (f.getPicklist() != null) {
                            // get option from picklist
                            String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryPickListByIdCached(f.getPicklist(), String.valueOf(map.get(f.getName()))));
                            if(value.equals("null")||value.isEmpty()){
                              value = "无";
                            }
                            columnitem.add(new Label("celldata", value));
                        } else if(f.getRelationTable() != null){
                            String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName()))));
                            if(value.equals("null")||value.isEmpty()){
                              value = "无";
                            }
                            columnitem.add(new Label("celldata", value));
                        }else {
                            String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                            if(value.equals("null")||value.isEmpty()){
                              value = "无";
                            }
                            columnitem.add(new Label("celldata", value));
                        }
                    }
                    columnRepeater.add(columnitem);
                }
                
            }
        };
        
        datacontainer.add(listview);
        //PagingNavigator nav = new PagingNavigator("navigator", listview);
        AjaxPagingNavigator nav =new AjaxPagingNavigator("navigator", listview);
        nav.setOutputMarkupId(true); 

        datacontainer.add(nav);
        datacontainer.setVersioned(false);
        
        ICRUDActionListener actionListener = new DefaultCRUDActionListener(){
            @Override
            public void create() { 
            	if(entity.getName().equals("coaching")){
            		setResponsePage(new ActivitySelectPage());     
            	}else{
            		setResponsePage(new CreateDataPage(entity.getName(),params));     
            	}
            }
            @Override
            public void downLoadBtn() throws Exception { 
              DataExportDelegate dataExport = new  DataExportDelegate();
//              String template = DAOImpl.selectTemplate(); 
             HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
             dataExport.export("Account Full Import Template 1.0", response);
              setResponsePage(new AccountPage());
            }    
        };
        
        add(new CRUDPanel("operationBar",entity.getName(),null, CRMUtility.getPermissionOfEntityList(roleId,entity.getName()),actionListener));
        
    	add(new NewDataFormPanel("formPanel",entity,null));

    }

    public PageableTablePanel(String id, IModel<?> model) {
        super(id, model);
    }

    private class DetailLinkFragment extends Fragment {
        /**
         * Construct.
         * 
         * @param id
         *            The component Id
         * @param markupId
         *            The id in the markup
         * @param markupProvider
         *            The markup provider
         */
        public DetailLinkFragment( String id, String markupId, MarkupContainer markupProvider, String caption, final Entity entity) {
            super(id, markupId, markupProvider);
            add(new Link("detailclick") {

                @Override
                public void onClick() {
                    Param p = (Param) getParent().getParent().getDefaultModelObject();
                    logger.debug(p + " id:" + p.getId() + " name:" + p.getEntityName());
//                    Attribute att = new Attribute();
                   /*if(entity.getName().equals("activity")){
                	   setResponsePage(new EventViewerPage(p.getId()));
                   }else{
                	   
                   } */
                   setResponsePage(new EntityDetailPage(p.getEntityName(), p.getId()));
                    // setResponsePage(new AccountDetailPage(id));
                }
            }.add(new Label("caption", new Model<String>(caption))));
        }
        
    }
    private  class  ButtonFragment extends Fragment{

		public ButtonFragment(String id, String markupId,
				MarkupContainer markupProvider, String delmas) {
			super(id, markupId, markupProvider);
			// TODO Auto-generated constructor stub
			
		}

		
    	
    }
}

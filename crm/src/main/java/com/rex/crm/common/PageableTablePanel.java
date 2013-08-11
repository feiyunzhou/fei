package com.rex.crm.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.account.AccountDetailPage;
import com.rex.crm.beans.Account;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class PageableTablePanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(PageableTablePanel.class);

    public PageableTablePanel(String id, Entity entity, List mapList) {
        super(id);

        add(new Label("table_title",entity.getDisplay()));
        // TODO Get the table definition from database or configuration
        final String primaryKeyName = entity.getPrimaryKeyName();
        final List<Field> fields = entity.getFields();
        final List<String> fn = entity.getFieldNames();
        final String entityName = entity.getName();

        // RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        // add(dataRowRepeater);

        final Map<String, Map> tableData = Maps.newHashMap();
        List<String> ids = Lists.newArrayList();
        for (Map map : (List<Map>) mapList) {
            String key = String.valueOf(map.get("id"));
            ids.add(key);
            tableData.put(key, map);
        }

        WebMarkupContainer datacontainer = new WebMarkupContainer("data");
        datacontainer.setOutputMarkupId(true);
        add(datacontainer);

        // set column name
        RepeatingView columnNameRepeater = new RepeatingView("columnNameRepeater");
        datacontainer.add(columnNameRepeater);
        for (Field f : entity.getFields()) {
            if (!f.isVisible()|| f.getPriority() >1)
                continue;
            AbstractItem item = new AbstractItem(columnNameRepeater.newChildId());
            columnNameRepeater.add(item);
            item.add(new Label("columnName", f.getDisplay()));
        }
        // end of set column name
        PageableListView<String> listview = new PageableListView<String>("dataRowRepeater", ids, 15) {
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
                        columnitem.add(new DetailLinkFragment("celldata", "detailFragment", this.getParent().getParent(), value));
                    } else {
                        if (f.getPicklist() != null) {
                            // get option from picklist
                            String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryPickListByIdCached(f.getPicklist(), String.valueOf(map.get(f.getName()))));
                            columnitem.add(new Label("celldata", value));

                        } else if(f.getRelationTable() != null){
                            String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName()))));
                            columnitem.add(new Label("celldata", value));
                        }else {
                            String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                            columnitem.add(new Label("celldata", value));
                        }
                    }
                    columnRepeater.add(columnitem);
                }
                
            }
        };
        
        datacontainer.add(listview);
        datacontainer.add(new AjaxPagingNavigator("navigator", listview));
        datacontainer.setVersioned(false);
        
        add(new CRUDPanel("operationBar",entity.getName(),EnumSet.of(CRUDPanel.Permissions.ADD)));
        
        
        add(new NewDataFormPanel("formPanel",entity));

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
        public DetailLinkFragment(String id, String markupId, MarkupContainer markupProvider, String caption) {
            super(id, markupId, markupProvider);
            add(new Link("detailclick") {

                @Override
                public void onClick() {
                    // System.out.println(getParent().getId());
                    // System.out.println("this link is clicked!"+this.getParent().getParent().getDefaultModelObject());
                    // Account selected =
                    // (Account)getParent().getDefaultModelObject();
                    Param p = (Param) getParent().getParent().getDefaultModelObject();
                    logger.debug(p + " id:" + p.getId() + " name:" + p.getEntityName());
                    setResponsePage(new EntityDetailPage(p.getEntityName(), p.getId()));

                    // setResponsePage(new AccountDetailPage(id));
                }
            }.add(new Label("caption", new Model<String>(caption))));
        }
    }

}

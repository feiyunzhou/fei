package com.rex.crm.common;

import java.awt.Button;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class EntityDetailPanel extends Panel {

    private static final Logger logger = Logger.getLogger(EntityDetailPanel.class);
    private static final long serialVersionUID = -2613412283023068638L;
    private Map<String, List<Field>> fieldGroupMap = Maps.newHashMap();
    private int number_of_column = 1;

    public EntityDetailPanel(String id, final Entity schema, final Map data, String entityId, int number_of_column, final String pageName) {
        super(id);
//        this.number_of_column = number_of_column;
        // TODO Get permission info of user from database.
        // add(new
        // CRUDPanel("operationBar",EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.EDIT)));


        // TODO Get the table definition from database or configuration

        String primaryKeyName = schema.getPrimaryKeyName();
        List<Field> fields = schema.getFields();//得到所有fields
        for (Field f : fields) {
            if (fieldGroupMap.get(f.getFieldGroup()) != null) {
                fieldGroupMap.get(f.getFieldGroup()).add(f);//以fieldgroup为条件查询，并将其添加入新的集合钟
            } else {
                List<Field> fs = Lists.newArrayList();
                fs.add(f);
                fieldGroupMap.put(f.getFieldGroup(), fs);
            }
        }
        List<String> groupNames = Configuration.getSortedFieldGroupNames();//得到分组信息
        RepeatingView fieldGroupRepeater = new RepeatingView("fieldGroupRepeater");
        add(fieldGroupRepeater);
        int gNum = 0;
        for (String gn : groupNames) {
            List<Field> groupfields = fieldGroupMap.get(gn);
            if (groupfields == null) {
                continue;
            }
            AbstractItem groupitem = new AbstractItem(fieldGroupRepeater.newChildId());
            fieldGroupRepeater.add(groupitem);
            WebMarkupContainer container = new WebMarkupContainer("divButton");
            groupitem.add(container);
            container.add(new AttributeAppender("data-target", new Model("#2" + (gNum++)), ";"));
            WebMarkupContainer icon = new WebMarkupContainer("icon");
            container.add(icon);
            icon.add(new AttributeAppender("id", new Model(gNum), ";"));
            RepeatingView divRepeater = new RepeatingView("divRepeater");
            groupitem.add(divRepeater);
            AbstractItem div = new AbstractItem(divRepeater.newChildId());
            divRepeater.add(div);
            div.add(new AttributeAppender("id", new Model("2" + (--gNum)), ";"));
            if (!(gNum == 0)) {
                div.add(new AttributeAppender("class", new Model("collapse"), ";"));
            } else {
                div.add(new AttributeAppender("class", new Model("collapse in"), ";"));
            }
            gNum++;
            RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
            div.add(dataRowRepeater);
            int numOfField = 0;
            List<Field> visibleFields = Lists.newArrayList();

            for (Field f : groupfields) {
                if (!f.isVisible() || f.getName().equalsIgnoreCase("name")) {
                    continue;
                }

                numOfField++;
                visibleFields.add(f);
            }

            groupitem.add(new Label("groupname", gn));

            int num_of_row = (numOfField / this.number_of_column) + 1;

            for (int i = 0; i < num_of_row; i++) {
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                item.add(new AttributeAppender("class", new Model(i), ";"));
                dataRowRepeater.add(item);
                RepeatingView columnRepeater = new RepeatingView("columnRepeater");
                item.add(columnRepeater);
                if (data == null) {
                    continue;
                }
                for (int j = 0; j < 2 * this.number_of_column; j++) {
                    AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(String.valueOf(data.get(primaryKeyName))));

                    if ((i * this.number_of_column + j / 2) >= visibleFields.size()) {
                        if ((i * this.number_of_column + j / 2) >= visibleFields.size()) {
                            continue;
                        }
                        columnitem.add(new Label("celldata", "&nbsp;").setEscapeModelStrings(false));
                        columnRepeater.add(columnitem);
                        continue;
                    }
                    Field currentField = visibleFields.get(i * this.number_of_column + j / 2);
                    if (currentField.getPicklist() != null) {

                        if (j % 2 == 0) {
                            columnitem.add(new Label("celldata", currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            String value = CRMUtility.formatValue(currentField.getFormatter(), DAOImpl.queryPickListByIdCached(currentField.getPicklist(), String.valueOf(data.get(currentField.getName()))));
                            value = (value == null) ? "" : value;
                            columnitem.add(new Label("celldata", value).setEscapeModelStrings(false));
                        }
                    } else if (currentField.getRelationTable() != null) {
                        if (j % 2 == 0) {
                            columnitem.add(new Label("celldata", currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            String value = CRMUtility.formatValue(currentField.getFormatter(), DAOImpl.queryCachedRelationDataById(currentField.getRelationTable(), String.valueOf(data.get(currentField.getName()))));
                            value = (value == null) ? "" : value;
                            if ((currentField.getName().equals("accountId"))) {
                                columnitem.add(new DetailLinkFragment("celldata", "detailFragment", this, value));
                            } else {
                                columnitem.add(new Label("celldata", value).setEscapeModelStrings(false));
                            }
                        }
                    } else {
                        if (j % 2 == 0) {
                            columnitem.add(new Label("celldata", currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            Object rawvalue = data.get(currentField.getName());
                            rawvalue = (rawvalue == null) ? "" : rawvalue;
                            String value = CRMUtility.formatValue(currentField.getFormatter(), String.valueOf(rawvalue));
                            value = (value == null) ? "" : value;
                            columnitem.add(new Label("celldata", value).setEscapeModelStrings(false));
                        }
                    }
                    columnRepeater.add(columnitem);
                }
            }// end of set the detailed info
        }// end of groupNames loop
        add(new AbstractAjaxBehavior() {
            @Override
            public void onRequest() {
            }

            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);
                response.render(OnDomReadyHeaderItem.forScript("$(\"#navitem-" + pageName + "\").addClass(\"active\");"));
            }
        });
    }

    private class DetailLinkFragment extends Fragment {

        public DetailLinkFragment(String id, String markupId, MarkupContainer markupProvider, String caption) {
            super(id, markupId, markupProvider);
            final String str = DAOImpl.queryEntityByName(caption);

            add(new Link("detailclick") {
                @Override
                public void onClick() {

                    setResponsePage(new EntityDetailPage("account", str));
                }
            }.add(new Label("caption", new Model<String>(caption))));
        }
    }
}

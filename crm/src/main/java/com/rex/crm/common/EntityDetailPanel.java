package com.rex.crm.common;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
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
	private static final Logger logger = Logger
			.getLogger(EntityDetailPanel.class);
	private static final long serialVersionUID = -2613412283023068638L;

	private Map<String, List<Field>> fieldGroupMap = Maps.newHashMap();

    private int number_of_column = 3;

    public EntityDetailPanel(String id, final Entity schema, final Map data, String entityId,int number_of_column) {
        super(id);
        this.number_of_column = number_of_column;
        // TODO Get permission info of user from database.
        // add(new
        // CRUDPanel("operationBar",EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.EDIT)));


        // TODO Get the table definition from database or configuration

        String primaryKeyName = schema.getPrimaryKeyName();
        List<Field> fields = schema.getFields();//得到所有fields
        // List<String> fn = schema.getFieldNames();
        for (Field f : fields) {
            if (fieldGroupMap.get(f.getFieldGroup()) != null) {
                fieldGroupMap.get(f.getFieldGroup()).add(f);//以fieldgroup为条件查询，并将其添加入新的集合钟
//                System.out.println(fieldGroupMap.get(f.getFieldGroup()));
            } else {
                List<Field> fs = Lists.newArrayList();
                fs.add(f);
                fieldGroupMap.put(f.getFieldGroup(), fs);
            }
        }
        List<String> groupNames = Configuration.getSortedFieldGroupNames();//得到分组信息
//        System.out.println(groupNames.toString());
        RepeatingView fieldGroupRepeater = new RepeatingView("fieldGroupRepeater");
        add(fieldGroupRepeater);
        for (String gn : groupNames) {
            List<Field> groupfields = fieldGroupMap.get(gn);
            if(groupfields == null) continue;
            AbstractItem groupitem = new AbstractItem(fieldGroupRepeater.newChildId());
            fieldGroupRepeater.add(groupitem);
//            System.out.println(groupitem);
            RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
            groupitem.add(dataRowRepeater);
//            System.out.println(dataRowRepeater);
            int numOfField = 0;
            List<Field> visibleFields = Lists.newArrayList();
            
            for (Field f : groupfields) {
                if (!f.isVisible() || f.getName().equalsIgnoreCase("name"))
                    continue;

                numOfField++;
                visibleFields.add(f);
            }

            groupitem.add(new Label("groupname", gn));
            // logger.debug("numOfField:"+numOfField);
            // set the detailed info
            int num_of_row = (numOfField / number_of_column) + 1;
            // logger.debug("num_of_row:"+num_of_row);

            for (int i = 0; i < num_of_row; i++) {
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);
                RepeatingView columnRepeater = new RepeatingView("columnRepeater");
                item.add(columnRepeater);

                for (int j = 0; j < 2 * number_of_column; j++) {
                    AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(String.valueOf(data.get(primaryKeyName))));

                    if ((i * number_of_column + j / 2) >= visibleFields.size()) {
                        columnitem.add(new Label("celldata", "&nbsp;").setEscapeModelStrings(false));
                        columnRepeater.add(columnitem);
                        continue;
                    }
                    Field currentField = visibleFields.get(i * number_of_column + j / 2);

                    if (currentField.getPicklist() != null) {

                        if (j % 2 == 0) {
                            columnitem.add(new Label("celldata", currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("style", new Model("text-align:left;width:200px"), ";"));
                        } else {
                            String value = CRMUtility.formatValue(currentField.getFormatter(),DAOImpl.queryPickListByIdCached(currentField.getPicklist(), String.valueOf(data.get(currentField.getName()))));
                            value = (value == null) ? "" : value;
                            columnitem.add(new Label("celldata", value).setEscapeModelStrings(false));
                            columnitem.add(new AttributeAppender("style", new Model("text-align:left;width:200px"), ";"));
                        }
                    } else if (currentField.getRelationTable() != null) {
                        if (j % 2 == 0) {
                            columnitem.add(new Label("celldata", currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("style", new Model("text-align:left;width:200px"), ";"));
                        } else {
                            String value = CRMUtility.formatValue(currentField.getFormatter(),
                                    DAOImpl.queryCachedRelationDataById(currentField.getRelationTable(), String.valueOf(data.get(currentField.getName()))));
                            value = (value == null) ? "" : value;
                            columnitem.add(new Label("celldata", value).setEscapeModelStrings(false));
                            columnitem.add(new AttributeAppender("style", new Model("text-align:left;width:200px"), ";"));
                        }
                    } else {
                        if (j % 2 == 0) {
                            columnitem.add(new Label("celldata", currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("style", new Model("text-align:left;width:200px"), ";"));
                        } else {
                            Object rawvalue = data.get(currentField.getName());
                            rawvalue = (rawvalue == null) ? "" : rawvalue;
                            String value = CRMUtility.formatValue(currentField.getFormatter(), String.valueOf(rawvalue));
                            value = (value == null) ? "" : value;
                            columnitem.add(new Label("celldata", value).setEscapeModelStrings(false));
                            columnitem.add(new AttributeAppender("style", new Model("text-align:left;width:200px"), ";"));
                        }
                    }

                    columnRepeater.add(columnitem);
                }

            }// end of set the detailed info

        }// end of groupNames loop

        // set navigation bar active
        add(new AbstractAjaxBehavior() {

            @Override
            public void onRequest() {
            }

            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);
                response.render(OnDomReadyHeaderItem.forScript("$(\"#navitem-" + schema.getName() + "\").addClass(\"active\");"));
            }

        });

      
    }
}

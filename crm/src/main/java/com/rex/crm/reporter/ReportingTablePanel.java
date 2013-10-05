package com.rex.crm.reporter;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.SearchCRMUserPage;
import com.rex.crm.account.AccountDetailPage;
import com.rex.crm.beans.Account;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class ReportingTablePanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(ReportingTablePanel.class);

    public ReportingTablePanel(String id,String reporting_caption,Entity entity,List<Map> dataList) {
       super(id);
       
       add(new Label("reporting_cap",reporting_caption));
       //set column name
       RepeatingView column_name_repeater = new RepeatingView("column_name_repeater");
       add(column_name_repeater);
       List<Field> fields = entity.getFields();
       for( Field f:fields){
           AbstractItem item = new AbstractItem(column_name_repeater.newChildId());
           column_name_repeater.add(item);
           item.add(new Label("column_name",f.getDisplay()));
       }
       
       if(dataList != null){
           RepeatingView row_repeater = new RepeatingView("row_repeater");
           add(row_repeater);
           for(Map data:dataList){
               AbstractItem row_item = new AbstractItem(row_repeater.newChildId());
               row_repeater.add(row_item);
               
               RepeatingView column_repeater = new RepeatingView("column_repeater");
               row_item.add(column_repeater);
               for( Field f:fields){
                   AbstractItem col_item = new AbstractItem(column_repeater.newChildId());
                   column_repeater.add(col_item);
                   col_item.add(new Label("cell_value",String.valueOf(data.get(f.getName()))));
               }
           }
       }
       
    }


}

package com.rex.crm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import com.google.common.collect.Maps;
import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.CalendarPanel;
import com.rex.crm.common.Entity;
import com.rex.crm.common.EntityDetailPanel;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.NewDataFormPanel;
import com.rex.crm.common.Relation;
import com.rex.crm.common.RelationDataPanel;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

public class HomePage extends TemplatePage {
    private static final long serialVersionUID = 1L;

    public HomePage() {
        super();
        setPageTitle("主页");
        List<Relation> relations = Configuration.getRelationsByName("account");

        Relation relation = relations.get(0);
        System.out.println(relation);
        List list = DAOImpl.queryEntityRelationList(relation.getSql(), "20");
        
        Entity accountEntity = Configuration.getEntityByName("account");

        add(new RelationDataPanel("testpanel", relation, list,"1"));
        // add(new CalendarPanel("testpanel"));
        //add(new EntityDetailPanel("testpanel","account","20"));
        
        //add(new NewDataFormPanel("testpanel",accountEntity.getFields()));
        
        

//        Map<String, Boolean> filter = Maps.newHashMap();
//        filter.put("1", true);
//        filter.put("2", true);
//        filter.put("3", true);
        
       // List<Pair<String, Map<String, Object>>> types = DAOImpl.getNumberOfTypeOfAccount("20");
//        List<Pair<String, Map<String, Object>>> types = DAOImpl.queryFilters(accountEntity.getSql(), accountEntity.getFilterField(), accountEntity.getFieldByName(accountEntity.getFilterField()).getPicklist(), "20");
//        add(new FilterPanel("testpanel",types ,filter,AccountPage.class));
    }
}

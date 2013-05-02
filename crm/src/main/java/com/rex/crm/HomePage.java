package com.rex.crm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.CalendarPanel;
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

        add(new RelationDataPanel("testpanel", relation, list));
        // add(new CalendarPanel("testpanel"));
    }
}

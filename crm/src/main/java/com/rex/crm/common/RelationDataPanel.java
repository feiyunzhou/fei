package com.rex.crm.common;

import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;



public class RelationDataPanel extends Panel {
  

    public RelationDataPanel( String id, Relation relation, List list) {
        super(id);
        

        WebMarkupContainer divC = new WebMarkupContainer("collapseDiv");
        divC.setOutputMarkupId(true);
        add(divC);
        
        final String collapseId = divC.getMarkupId();
        WebMarkupContainer linkC = new WebMarkupContainer("aLink"){

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("href", "#"+collapseId);
            }
            
        };
        add(linkC);
        linkC.add(new Label("caption",relation.getDisplay()));
        Entity entity = Configuration.getEntityByName(relation.getTo());
        divC.add(new TableDataPanel("tableData",entity,list));
    }

}

package com.rex.crm.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.rex.crm.SearchCRMUserPage;
import com.rex.crm.SearchContactPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;



public class RelationDataPanel extends Panel {
  

    public RelationDataPanel( String id, Relation relation, String entityName,List list,final String entityId) {
        super(id);
        

        WebMarkupContainer divC = new WebMarkupContainer("collapseDiv");
        divC.setOutputMarkupId(true);
        add(divC);
        
        final String collapseId = divC.getMarkupId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
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
        divC.add(new PageableTablePanel("tableData",entity,list));
        
        
        RepeatingView btn_repeater = new RepeatingView("btn_repeater");
        add(btn_repeater);
        
        List<Reaction> reactions = relation.getReactions();
        if(reactions!=null){
        
        for(Reaction act:reactions){
          AbstractItem item = new AbstractItem(btn_repeater.newChildId());
          btn_repeater.add(item);
          final PageParameters pars = new PageParameters();
//          pars.set("entityName", relation.getTo());
          pars.set("cid", entityId);
          pars.set("entityname",entityName);
          //System.out.println("PPPPPP:"+act.getParamName());
          if(act.getParamName()!=null && !act.getParamName().isEmpty()){
              pars.set(act.getParamName(),entityId);
          }
          if(roleId == 3){
            WebMarkupContainer con = new WebMarkupContainer("actionlink");
            con.add(new AttributeAppender("class", ""));
            con.add(new WebMarkupContainer("cap"));
            item.add(con);
          }else{
            BookmarkablePageLink link = new BookmarkablePageLink("actionlink", SearchCRMUserPage.class,pars);  
            link.add(new Label("cap",act.getDisplay()));
            item.add(link);
          }
         }
        }
    }

}

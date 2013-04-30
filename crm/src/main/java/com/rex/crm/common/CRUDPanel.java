package com.rex.crm.common;

import java.util.EnumSet;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.rex.crm.beans.Account;

public class CRUDPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;

    public enum Permissions {
        ADD, DEL, EDIT;

    }

    public CRUDPanel(String id, EnumSet<Permissions> userPerms) {
        super(id);

//        WebMarkupContainer addCon = new WebMarkupContainer("addCon");
//        add(addCon);
//        WebMarkupContainer delCon = new WebMarkupContainer("delCon");
//        add(delCon);
//        WebMarkupContainer editCon = new WebMarkupContainer("editCon");
//        add(editCon);
        
        if (userPerms == null || userPerms.size() == 0) {
            add(new Fragment("addCon","emptyFragment",this));
            add(new Fragment("delCon","emptyFragment",this));
            add(new Fragment("editCon","emptyFragment",this));
            
        } else {
            
            if (userPerms.contains(Permissions.ADD)) {
                add(new Fragment("addCon","addFragment",this));
                System.out.println("add fragment");
            }else{
                add(new Fragment("addCon","emptyFragment",this));
            }
            
            if (userPerms.contains(Permissions.DEL)) {
                add(new Fragment("delCon","delFragment",this));
               System.out.println("dele fragment");
            }else{
                add(new Fragment("delCon","emptyFragment",this));
            }
            
            if (userPerms.contains(Permissions.EDIT)) {
                addOrReplace(new Fragment("editCon","editFragment",this));
            }else{
                addOrReplace(new Fragment("editCon","emptyFragment",this));
            }
            
            
        }

    }

}

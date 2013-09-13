package com.rex.crm.common;

import java.util.EnumSet;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.rex.crm.AccountPage;
import com.rex.crm.beans.Account;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

public class CRUDPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;

    public enum Permissions {
        ADD, DEL, EDIT,NONE;

    }

    public CRUDPanel(   final String id, final String entityName,final String entityId,EnumSet<Permissions> userPerms) {
        super(id);
        
        Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get(entityName);
        
        if (userPerms == null || userPerms.size() == 0) {
            add(new Fragment("addCon","emptyFragment",this));
            add(new Fragment("delCon","emptyFragment",this));
            add(new Fragment("editCon","emptyFragment",this));
            
        } else {
            
            if (userPerms.contains(Permissions.ADD)) {
               Fragment addfrag = new Fragment("addCon","addFragment",this);
               addfrag.add(new Link("create_new_data_btn") {

                   @Override
                   public void onClick() {
                      
                       setResponsePage(new CreateDataPage(entity.getName()));
                   }
               });
                add(addfrag);
            }else{
                add(new Fragment("addCon","emptyFragment",this));
            }
            
            if (userPerms.contains(Permissions.DEL)) {
            	Fragment delfrag = new Fragment("delCon","delFragment",this);
            	delfrag.add(new Link("del_data_btn") {

                    @Override
                    public void onClick() {
                    	if(entityName.equals("account")){
                    		DAOImpl.deleteaCountactRecord(entityId);
                        	DAOImpl.deleteaAcountCrmuserRecord(entityId);
                    	}else if(entityName.equals("contant")){
                    		DAOImpl.deleteaAcountCrmuserRecord(entityId);
                    	}
                        DAOImpl.deleteRecord(entityId, entityName);
                        setResponsePage(new AccountPage());
                    }
                });
                 add(delfrag);
            }else{
                add(new Fragment("delCon","emptyFragment",this));
            }
            
            if (userPerms.contains(Permissions.EDIT)) {
            	Fragment editfrag = new Fragment("editCon","editFragment",this);
            	editfrag.addOrReplace(new Link("edit_data_btn") {
            		
                    @Override
                    public void onClick() {
                    	
                        setResponsePage(new EditDataPage(entity.getName(),entityId));
                    }
                });
            	addOrReplace(editfrag);
            }else{
                addOrReplace(new Fragment("editCon","emptyFragment",this));
            }
            
            
            
        }

    }

}

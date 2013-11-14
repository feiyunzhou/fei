package com.rex.crm.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.rex.crm.DataPage;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class DownLoadPage extends DataPage{
    private String search_target = "";
    public DownLoadPage(){
       init(null,null);
    }
    public DownLoadPage(final Map<String,Boolean> filter,List tdata){
        init(filter,tdata);
    }

	public void init(final Map<String,Boolean> filter,List tdata)
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("upLoad");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        
        
        Form form = new Form("form"){

            @Override
            protected void onSubmit() {

                String sql =  entity.getSqlAdmin();
            }
        };
        add(form);
        
	}
}
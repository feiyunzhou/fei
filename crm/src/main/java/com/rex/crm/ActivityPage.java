package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.CreateDataPage;
import com.rex.crm.common.Entity;
import com.rex.crm.common.EntityDetailPage;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.Param;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class ActivityPage extends TemplatePage
{
    private String search_target = "";
    public ActivityPage(){
       init(null);
    }
    public ActivityPage(List tdata){
        init(tdata);
    }

	public void init(List tdata)
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        
        Form form = new Form("form"){

            @Override
            protected void onSubmit() {

                String sql = entity.getSql();
                if(roleId == 1){
                    sql = entity.getSqlAdmin();
                }     
                search_target = (search_target==null || search_target.equalsIgnoreCase("*"))? "":search_target;
               
                sql =  sql + " where title like '%"+search_target+"%'";
                List datalist = DAOImpl.queryEntityRelationList(sql, userId);
                setResponsePage(new ActivityPage(datalist));
                
            }
            
        };
        add(form);
        
        TextField search_input = new TextField("search_input", new PropertyModel(this,"search_target"));
        form.add(search_input);
        
        
        
        String sql = entity.getSql();
        if(roleId == 1){
            sql = entity.getSqlAdmin();
        }
        
        if(tdata == null || tdata.size() == 0){
           tdata = DAOImpl.queryEntityRelationList(sql, userId);
        }
		add(new PageableTablePanel("datalist",entity,tdata));
	
	}
}
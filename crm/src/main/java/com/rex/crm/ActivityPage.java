package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

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
	/**
	 * Constructor
	 */
	public ActivityPage()
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        final String userId = ((SignIn2Session)getSession()).getUserId();
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        
        String sql = entity.getSql();
        if(roleId == 1){
            sql = entity.getSqlAdmin();
        }
        List mapList = DAOImpl.queryEntityRelationList(sql, userId);
		add(new PageableTablePanel("datalist",entity,mapList));
		
		
		
		add(new Link("detailclick") {

            @Override
            public void onClick() {
                // System.out.println(getParent().getId());
                // System.out.println("this link is clicked!"+this.getParent().getParent().getDefaultModelObject());
                // Account selected =
                // (Account)getParent().getDefaultModelObject();
                  //Param p = (Param) getParent().getParent().getDefaultModelObject();
                 //System.out.println(p + " id:" + p.getId() + " name:" + p.getEntityName());
                setResponsePage(new CreateDataPage("contact"));

                // setResponsePage(new AccountDetailPage(id));
            }
        });
		
	}
}
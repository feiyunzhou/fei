package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.google.common.collect.Lists;
import com.rex.crm.beans.Contact;
import com.rex.crm.common.NewDataFormPanel;
import com.rex.crm.db.DAOImpl;

/**
 * 
 * 
 * @author Feiyun
 */
public class SelectEntryPage extends WebPage {
    private static final Logger logger = Logger.getLogger(SelectEntryPage.class);

    private String search_target;

    
    /**
     * Constructor
     * 
     * @param parameters
     *            Page parameters (ignored since this is the home page)
     */
    public SelectEntryPage() {
        String entityName = getRequest().getRequestParameters().getParameterValue("en").toString();
        initPage(null,entityName);
    }

    public SelectEntryPage(List<Map> maplist,String entityName) {
        initPage(maplist,entityName);
    }

    public void initPage(List<Map> list,final String entityName) {
        final String userId = ((SignIn2Session) getSession()).getUserId();

        final int roleId = ((SignIn2Session) getSession()).getRoleId();
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                List<Map> maplist = null;
                if(entityName.equalsIgnoreCase("account")){
                    maplist = DAOImpl.searchAccount(userId, search_target, roleId);
                }else if(entityName.equalsIgnoreCase("crmuser")){
                    maplist = DAOImpl.searchCRMUser(search_target);
                }
                PageParameters parm = new PageParameters();
                parm.set("en", entityName);
                //this.setResponsePage(cls, parameters)
                setResponsePage(new SelectEntryPage(maplist,entityName));

            }
        };
        form.add(new TextField<String>("search_input", new PropertyModel<String>(this, "search_target")));
        add(form);
       
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);

        if (list != null) {
            for (Map map : list) {
                int uid = ((Number) map.get("id")).intValue();
                String name = (String) map.get("name");
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);

                item.add(new AttributeAppender("data-id",new Model(uid)));
                item.add(new AttributeAppender("data-name",new Model(name)));
                item.add(new AttributeAppender("data-ename",entityName));
                
                //item.add(new AttributeAppender("data-cname", new Model(cname)));
                Label cap = new Label("name_span", new Model(name));
                item.add(cap);
                
                
            }
        }
    }
}

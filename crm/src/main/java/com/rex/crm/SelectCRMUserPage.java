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
public class SelectCRMUserPage extends WebPage {
    private static final Logger logger = Logger.getLogger(SelectCRMUserPage.class);

    private String search_target;

    /**
     * Constructor
     * 
     * @param parameters
     *            Page parameters (ignored since this is the home page)
     */
    public SelectCRMUserPage() {
        StringValue mid = getPageParameters().get("mid");
        initPage(null,mid.toString());
    }

    public SelectCRMUserPage(List<Map> maplist,String managerId) {
        initPage(maplist,managerId);
    }

    public void initPage(List<Map> list,final String managerId) {
       // final String userId = ((SignIn2Session) getSession()).getUserId();
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                logger.debug("the form was submitted!");
                // new PropertyModel<String>(this, "selected")
                //List<Map> maplist = DAOImpl.searchCRMUser(managerId, search_target);
                List<Map> maplist = DAOImpl.searchCRMUserOfManager(managerId,search_target);
                setResponsePage(new SelectCRMUserPage(maplist,managerId));

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
                String cellPhone = (String) map.get("cellPhone");
                String jobTitle = (String) map.get("jobTitle");
                String email = (String) map.get("email");
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);
                Label cap = new Label("name_span", new Model(name));
                item.add(new AttributeAppender("data-uid",new Model(uid)));
                item.add(new AttributeAppender("data-uname",new Model(name)));
                
                //item.add(new AttributeAppender("data-cname", new Model(cname)));
                item.add(cap);
                item.add(new Label("cellPhone_span", cellPhone));
                item.add(new Label("email_span", email));
                item.add(new Label("jobTitle_span", jobTitle));
                
                
            }
        }
    }
}

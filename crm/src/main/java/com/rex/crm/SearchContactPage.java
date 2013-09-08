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

import com.google.common.collect.Lists;
import com.rex.crm.beans.Contact;
import com.rex.crm.common.NewDataFormPanel;
import com.rex.crm.db.DAOImpl;

/**
 * 
 * 
 * @author Feiyun
 */
public class SearchContactPage extends WebPage {
    private static final Logger logger = Logger.getLogger(SearchContactPage.class);

    private String search_target;

    /**
     * Constructor
     * 
     * @param parameters
     *            Page parameters (ignored since this is the home page)
     */
    public SearchContactPage() {
        initPage(null);
    }

    public SearchContactPage(List<Map> maplist) {
        initPage(maplist);
    }

    public void initPage(List<Map> list) {
        final String userId = ((SignIn2Session) getSession()).getUserId();
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                logger.debug("the form was submitted!");
                // new PropertyModel<String>(this, "selected")
                List<Map> maplist = DAOImpl.searchContact(userId, search_target);
                System.out.println("AAAA:"+maplist);
                setResponsePage(new SearchContactPage(maplist));

            }
        };
        form.add(new TextField<String>("search_input", new PropertyModel<String>(this, "search_target")));
        add(form);

        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        add(dataRowRepeater);

        if (list != null) {
            for (Map map : list) {
                int cid = ((Number) map.get("cid")).intValue();
                String cname = (String) map.get("cname");
                String aname = (String) map.get("aname");
                System.out.println(cname);
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);
                Label cap = new Label("cname_span", new Model(cname));
                item.add(new AttributeAppender("data-cid",new Model(cid)));
                item.add(new AttributeAppender("data-cname",new Model(cname)));
                item.add(new AttributeAppender("data-aname",new Model(aname)));
                //item.add(new AttributeAppender("data-cname", new Model(cname)));
                item.add(cap);
                item.add(new Label("aname_span", new Model(aname)));
                
                
            }
        }
    }
}

package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
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
import com.rex.crm.common.EntityDetailPage;
import com.rex.crm.common.NewDataFormPanel;
import com.rex.crm.db.DAOImpl;

/**
 *
 *
 * @author Feiyun
 */
public class SearchCRMUserPage extends WebPage {

    private static final Logger logger = Logger.getLogger(SearchCRMUserPage.class);
    private String search_target;
    List<String> selectedUserIds = Lists.newArrayList();
    private String entityId;
//     private String entityName;
    private int type = 0;

    /**
     * Constructor
     *
     * @param parameters Page parameters (ignored since this is the home page)
     */
    public SearchCRMUserPage() {
        StringValue value = this.getRequest().getRequestParameters().getParameterValue("cid");
        StringValue entityname = getRequest().getRequestParameters().getParameterValue("entityname");
        if (value != null) {
            entityId = value.toString();
        }
        initPage(entityname.toString(), null, entityId, type);
    }

    public SearchCRMUserPage(String entityName, String entityId, int type) {
        //logger.debug("sdfsfsdfdsf:"+entityName);
        this.entityId = entityId;
        this.type = type;
        initPage(entityName, null, entityId, type);
    }

    public SearchCRMUserPage(List<Map> maplist, String entityName, String cid, int type) {
        entityId = cid;
        this.type = type;
        initPage(entityName, maplist, cid, type);
    }

    public void initPage(final String entityname, List<Map> list, final String cid, final int type) {
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                logger.debug("the form was submitted!");
                List<Map> maplist = null;

                if (entityname.equals("account") || entityname.equals("contact")) {
                    maplist = DAOImpl.searchCRMUser(search_target);
                } else {
                    if (type == 0) {

                        maplist = DAOImpl.searchCRMAccount(search_target);
                    } else {
                        maplist = DAOImpl.searchCRMContact(search_target);
                    }
                }


                setResponsePage(new SearchCRMUserPage(maplist, entityname, cid, type));

            }
        };
        form.add(new TextField<String>("search_input", new PropertyModel<String>(this, "search_target")));
        add(form);




        Form users_sbumission_form = new Form("users_sbumission_form") {
            @Override
            protected void onSubmit() {

                logger.debug("seletedUserIds:" + selectedUserIds);
                for (String uid : selectedUserIds) {
                    try {
                        DAOImpl.insertRelationOfEntityIDCRMUserID(entityname, cid, uid, type);

                    } catch (Exception e) {
                    }
                }
                setResponsePage(new EntityDetailPage(entityname, cid));

            }
        };
        final int roleId = ((SignIn2Session) getSession()).getRoleId();
        add(users_sbumission_form);
        CheckGroup group = new CheckGroup("group", new PropertyModel(this, "selectedUserIds"));
        if (roleId == 1) {
            CheckGroupSelector chks = new CheckGroupSelector("checkboxs");
            group.add(chks);
            WebMarkupContainer container_label = new WebMarkupContainer("checkboxs_label");
            group.add(container_label);
            container_label.add(new AttributeAppender("for", new Model(chks.getMarkupId()), " "));
        } else {
            WebMarkupContainer container = new WebMarkupContainer("checkboxs");
            container.setVisible(false);
            group.add(container);
        }
        users_sbumission_form.add(group);

        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        group.add(dataRowRepeater);

        if (list != null) {
            for (Map map : list) {

                int uid = ((Number) map.get("id")).intValue();
                String name = (String) map.get("name");
                String cellPhone = (String) map.get("cellPhone");
                String division = (String) map.get("division");
                String email = (String) map.get("email");
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);
                Check chk = new Check("checkbox", new Model(String.valueOf(uid)));
                item.add(chk);
                WebMarkupContainer container_label = new WebMarkupContainer("checkboxs_label");
                item.add(container_label);
                container_label.add(new AttributeAppender("for", new Model(chk.getMarkupId()), " "));
                item.add(new Label("name", name));
                item.add(new Label("cellPhone", cellPhone));
                item.add(new Label("division", division));
                item.add(new Label("email", email));



            }
        }
    }
}

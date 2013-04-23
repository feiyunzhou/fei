package com.rex.crm.account;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.rex.crm.beans.Account;

public class AccountListPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;

    public AccountListPanel(String id) {
        super(id);

        DataView<Account> dataView = new DataView<Account>("pageable", new AccountDataProvider(20)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(Item<Account> item) {
                Account account = item.getModelObject();
                item.add(new ActionPanel("name",item.getModel(),account.getName()));
                item.add(new Label("address", account.getAddress()));
                item.add(new Label("tele", account.getTele()));
                item.add(new Label("classification", account.getClassification()));
            }
        };
        dataView.setItemsPerPage(10000L);
        add(dataView);

    }

    public AccountListPanel(String id, IModel<?> model) {
        super(id, model);
        // TODO Auto-generated constructor stub
    }
    
    class ActionPanel extends Panel
    {
        /**
         * @param id
         *            component id
         * @param model
         *            model for contact
         */
        public ActionPanel(String id, IModel<Account> model,String name)
        {
            super(id, model);
            add(new Link("select")
            {
                
                @Override
                public void onClick()
                {
                    Account selected = (Account)getParent().getDefaultModelObject();
                    setResponsePage(new AccountDetailPage(selected));
                }
            }.add(new Label("label", new Model<String>(name)))
            );
        }
    }

}

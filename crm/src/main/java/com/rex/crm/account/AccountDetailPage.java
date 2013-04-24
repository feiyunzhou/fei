package com.rex.crm.account;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.rex.crm.TemplatePage;
import com.rex.crm.ajax.DataProvider;
import com.rex.crm.beans.Account;

/**
 * @author Feiyun Zhou
 */
public class AccountDetailPage extends TemplatePage {
    /**
     * Constructor
     */
    public AccountDetailPage(Account account) {
        setPageTitle("Account Detail");

        List<String> filedsName = account.getFieldNames();
        Map<String, String> fieldMap = account.getMappingOfField2ColumnName();

        RepeatingView rv = new RepeatingView("entityDetail");
        add(rv);

        for (String name : filedsName) {
            if (fieldMap.get(name) != null) {
                WebMarkupContainer item = new WebMarkupContainer(rv.newChildId());
                rv.add(item);
                Label label = null;
                try {
                    label = new Label("caption", fieldMap.get(name) + ": " + BeanUtils.getProperty(account, name));
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                item.add(label);
            }
        }

    }
}
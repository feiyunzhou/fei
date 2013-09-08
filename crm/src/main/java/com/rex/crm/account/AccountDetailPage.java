package com.rex.crm.account;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;

import com.rex.crm.TemplatePage;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.common.CRUDPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.user.CRMUserDataProvider;

/**
 * @author Feiyun Zhou
 */
public class AccountDetailPage extends TemplatePage {
  
    public AccountDetailPage(String accountId){
        Account account = DAOImpl.getAccountById(Integer.parseInt(accountId));
        initPage(account);
    }
    
    public AccountDetailPage(Account account) {
        setPageTitle("客户");
        initPage(account);   
    }

    private void initPage(Account account){
        //TODO Get permission infor of user from database.
        add(new CRUDPanel("operationBar","account",null,EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.EDIT)));
        
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
        
        
        
        DataView<CRMUser> dataView = new DataView<CRMUser>("team-pageable", new CRMUserDataProvider(account.getId())) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(Item<CRMUser> item) {
                CRMUser user = item.getModelObject();
                item.add(new Label("name", user.getName()));
                item.add(new Label("cellphone", user.getCellPhone()));
                item.add(new Label("division", user.getDivision()));
                item.add(new Label("jobtitle", user.getJobTitle()));
                item.add(new Label("email", user.getEmail()));
                
            }

          
        };
        dataView.setItemsPerPage(10000L);
        add(dataView);
        
        //TODO Get permission info of user from database.
        add(new CRUDPanel("team-operationBar","account",null,EnumSet.of(CRUDPanel.Permissions.ADD)));

    }
}
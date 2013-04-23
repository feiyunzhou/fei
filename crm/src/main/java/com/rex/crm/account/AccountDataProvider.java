package com.rex.crm.account;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import com.rex.crm.beans.Account;
import com.rex.crm.db.DAOImpl;

public class AccountDataProvider extends SortableDataProvider<Account, String> {

    private int userId;
    public AccountDataProvider(int userId){
        this.userId = userId;
    }
    
    @Override
    public Iterator<? extends Account> iterator(long first, long count) {
        // TODO Auto-generated method stub
        List<Account> list = DAOImpl.getAccountsByUserId(userId);
        return list.iterator();
    }

    @Override
    public long size() {
        return DAOImpl.getSizeOfAccountByUserId(userId);
    }

    @Override
    public IModel<Account> model(Account object) {
        return new DetachableAccountModel(object);
    }

}

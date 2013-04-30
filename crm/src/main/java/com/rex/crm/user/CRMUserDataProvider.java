package com.rex.crm.user;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import com.rex.crm.account.DetachableAccountModel;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.db.DAOImpl;

public class CRMUserDataProvider extends SortableDataProvider<CRMUser, String> {

    private int accountId;
    public CRMUserDataProvider(int accountId){
        this.accountId = accountId;
    }
    
    @Override
    public Iterator<? extends CRMUser> iterator(long first, long count) {
        // TODO Auto-generated method stub
        List<CRMUser> list = DAOImpl.getUsersByAccountId(accountId);
      
        return list.iterator();
    }

    @Override
    public long size() {
        return DAOImpl.getSizeOfUsersByAccountId(accountId);
    }

    @Override
    public IModel<CRMUser> model(CRMUser object) {
        return new DetachableCRMUserModel(object);
    }

}

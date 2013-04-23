package com.rex.crm.account;

import com.rex.crm.TemplatePage;
import com.rex.crm.ajax.DataProvider;
import com.rex.crm.beans.Account;

/**
 * @author Feiyun Zhou 
 */
public class AccountDetailPage extends TemplatePage
{
	/**
	 * Constructor
	 */
	public AccountDetailPage(Account account)
	{
		setPageTitle("Account Detail");
		
		System.out.println("AccountDetailPage:"+account);
		//add(new TreePanel("accounTreePanel",DataProvider.categorizeAccountsByProvinceAndCity()));
		
	}
}
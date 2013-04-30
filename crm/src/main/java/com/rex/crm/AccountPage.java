package com.rex.crm;

import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.TableDataPanel;


/**
 * @author Feiyun Zhou 
 */
public class AccountPage extends TemplatePage
{
	/**
	 * Constructor
	 */
	public AccountPage()
	{
		setPageTitle("Account Page");
		
		//add(new TreePanel("accounTreePanel",DataProvider.categorizeAccountsByProvinceAndCity()));
		
		add(new TableDataPanel("accountList","account"));
		
	}
}
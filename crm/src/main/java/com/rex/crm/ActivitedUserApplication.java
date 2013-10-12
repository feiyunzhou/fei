package com.rex.crm;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.rex.crm.beans.CRMUser;
import com.rex.crm.db.DAOImpl;

public class ActivitedUserApplication extends WebApplication{
	private String loginName = "";
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
    public Session newSession(Request request, Response response)
    {
        return new SignIn2Session(request);
    }
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		//如何获取参数
		//getRequestCycleSettings().
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8"); 
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        System.out.println("跳转界面！");
        CRMUser user = DAOImpl.getUserByActivation(loginName);
	    //判断如果user不等于null，则跳转页面，设置密码
	    if(null!=user){
	    	mountPage("/mount/ActivitedUser?loginName="+loginName+"", ActivitedUser.class);
	    }
	}
}

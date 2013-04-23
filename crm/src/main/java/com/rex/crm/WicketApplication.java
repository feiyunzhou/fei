package com.rex.crm;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.util.file.File;

import com.rex.crm.FolderResource;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.rex.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

	       getRequestCycleSettings().setResponseRequestEncoding("UTF-8"); 
	         getMarkupSettings().setDefaultMarkupEncoding("UTF-8"); 
	        // add your configuration here
	         getSharedResources().add("image", new FolderResource(new File(getServletContext().getRealPath("image"))));
	         mountResource("/image", new SharedResourceReference("image"));
	}
}

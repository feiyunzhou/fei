package com.rex.crm;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.util.file.File;

import com.rex.crm.common.CreateDataPage;
import com.rex.crm.common.EditDataPage;
import com.rex.crm.common.EntityDetailPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.rex.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
    
    
    
	@Override
    public RuntimeConfigurationType getConfigurationType() {
        // TODO Auto-generated method stub
        return RuntimeConfigurationType.DEPLOYMENT;
    }

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

	         getRequestCycleSettings().setResponseRequestEncoding("UTF-8"); 
	         getMarkupSettings().setDefaultMarkupEncoding("UTF-8"); 
	        // add your configuration here
	         getSharedResources().add("image", new FolderResource(new File(getServletContext().getRealPath("image"))));
	         mountResource("/image", new SharedResourceReference("image"));
	         
	         mountPage("/mount/searchContactPage", SearchContactPage.class);
	         mountPage("/mount/createEventPage", CreateEventPage.class);
	         mountPage("/mount/eventViewer",EventViewerPage.class);
	         mountPage("/mount/searchCRMUser", SearchCRMUserPage.class);
	 	     mountPage("/mount/ActivitedUser", ActivitedUser.class);
	 	     mountPage("/mount/CreateDataPage", CreateDataPage.class);
	 	     mountPage("/mount/ActivitySelectPage",ActivitySelectPage.class);
	 	     mountPage("/mount/EntityDetailPage",EntityDetailPage.class);
	         // Register the authorization strategy
	         getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy()
	         {
	             public boolean isActionAuthorized(Component component, Action action)
	             {
	                 // authorize everything
	                 return true;
	             }

	             public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
	                 Class<T> componentClass)
	             {
	                 // Check if the new Page requires authentication (implements the marker interface)
	                 if (AuthenticatedWebPage.class.isAssignableFrom(componentClass))
	                 {
	                     // Is user signed in?
	                     if (((SignIn2Session)Session.get()).isSignedIn())
	                     {
	                         // okay to proceed
	                         return true;
	                     }

	                     // Intercept the request, but remember the target for later.
	                     // Invoke Component.continueToOriginalDestination() after successful logon to
	                     // continue with the target remembered.
	                     throw new RestartResponseAtInterceptPageException(SignIn.class);
	                 }

	                 // okay to proceed
	                 return true;
	             }
	         });
	         
	}
	

}

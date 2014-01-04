/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rex.crm;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.beans.UserPosition;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.userlog.LogInOut;
import com.rex.crm.util.CRMUtility;


/**
 * Simple example of a sign in page. It is based on auth-role's SignInPanel which already provides
 * all what is necessary.
 * 
 * @author Jonathan Locke
 */
public final class SignIn extends WebPage
{
	/**
	 * Construct
	 */
	public SignIn()
	{
		//create feedback panel and add to page
		add(new FeedbackPanel("feedback"));
		//add sign-in form to page
		add(new SignInForm("signInForm"));
   
	}
    public final class SignInForm extends Form<Void>
    {
    	private static final String USERNAME ="username";
    	private static final String PASSWORD = "password";
    	private final ValueMap properties = new ValueMap();
    	/**
    	 * Constructor
    	 * 
    	 * @param id
    	 * 			 id of the form component
    	 */
    	public SignInForm(final String id)
    	{
    		super(id);
    		add(new TextField<String>(USERNAME,new PropertyModel<String>(properties,USERNAME)));
    		add(new PasswordTextField(PASSWORD,new PropertyModel<String>(properties,PASSWORD)));
    	}
    	/**
         * sign 
         */
        @SuppressWarnings("unused")
		public final void onSubmit()
        {
        	//get session info
        	SignIn2Session session = getMysession();
        	//clear session user
        	session.setUser(null);
        	//判断用户是否激活密码是否存在
        	
        	if("".equals(getUsername())||"".equals(getPassword())){
        		 String errmsg = getString("loginError", null, "用户名和密码不能为空!");
                 error(errmsg);
        	}else{
            	UserInfo user = DAOImpl.getUserByLoginName(getUsername());
            	if(null!=user){
              	        	// Sign the user in
            	  
              	            if (session.signIn(getUsername(),getPassword()))
                            {
                              
                              UserPosition userPosition = DAOImpl.getActivityPositionInfoByUserId(Integer.parseInt(session.getUserId()));
                              if(userPosition == null){
                                  String errmsg = getString("loginError", null, "无有效岗位!");
                                  // Register the error message with the feedback panel
                                  error(errmsg);
                                  return;
                              }
                              CRMUser crmuser = DAOImpl.getCRMUserInfoById(userPosition.getPositionId());
                              if(crmuser.getPl1()==2){
                                  String errmsg = getString("loginError", null, "岗位已失效!");
                                  // Register the error message with the feedback panel
                                  error(errmsg);
                                  return;
                              }
                              session.setPositionId(String.valueOf(userPosition.getPositionId()));
                              session.setLevel(crmuser.getLevel());
                              session.setRoleId(crmuser.getRole());
              	                
              	                //statistic log
                                LogInOut loginout = new LogInOut();
                                loginout.setLoginName(session.getUser());
                                loginout.setLogints(System.currentTimeMillis());
                                loginout.setSessionId(session.getId());
                                CRMUtility.printStat(CRMUtility.STAT_LOG_IN_OUT,loginout,LogInOut.class);
                                
                               
                             
                                if(user.getNum_of_signIn()==0){
                                	 setResponsePage(new SettingKeyUserInfoPage(user));
                                }else{
                                	//根据userId查找用户岗位。如果岗位有多个则选择岗位
                                	List<UserPosition> positions = DAOImpl.getPositionsByUserId(user.getId());
                                	if(positions.size()>1){
                                		setResponsePage(new SelectPositionPage(positions));
                                	}else {
                                	  if(session.getPositionId().equals(-1)){
                                	    setResponsePage(new NullPosition(null));
                                	  }
                                		setResponsePage(getApplication().getHomePage());
                                	}
                                	DAOImpl.addSignInNumber(user.getId(),user.getNum_of_signIn()+1);
                                }
                            }
              	            else
              	            {
              	                // Get the error message from the properties file associated with the Component
              	                String errmsg = getString("loginError", null, "用户名或密码错误!");
              	                // Register the error message with the feedback panel
              	                error(errmsg);
              	            }
            		
            	}else{
            		String errmsg = getString("loginError", null, "用户名不存在!");
   	                error(errmsg);
            	}
        	}
        }
    	/**
    	 * @return
    	 */
    	private String getUsername()
    	{
    		return properties.getString(USERNAME);
    	}
    	private String getPassword()
    	{
    		return properties.getString(PASSWORD);
    	}
    	private SignIn2Session getMysession()
    	{
    		return (SignIn2Session)getSession();
    	}
    	
    }
}

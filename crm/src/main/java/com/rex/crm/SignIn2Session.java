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

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.rex.crm.beans.CRMUser;
import com.rex.crm.db.DAOImpl;

/**
 * Session class for signin example. Holds and authenticates users.
 * 
 * @author Jonathan Locke
 */
public final class SignIn2Session extends AuthenticatedWebSession
{
	/** Trivial user representation */
	private String user;
	private String userId;
	private int roleId;

	/**
	 * Constructor
	 * 
	 * @param request
	 *            The current request object
	 */
	protected SignIn2Session(Request request)
	{
		super(request);

	}

	/**
	 * Checks the given username and password, returning a User object if if the username and
	 * password identify a valid user.
	 * 
	 * @param username
	 *            The username
	 * @param password
	 *            The password
	 * @return True if the user was authenticated
	 */
	@Override
	public final boolean authenticate(final String username, final String password)
	{
		if (user == null)
		{
		    
		    CRMUser crmUser = DAOImpl.login(username, password);
		    if(crmUser!=null && crmUser.getId() != 0){
		        user = crmUser.getName();
		        userId = String.valueOf(crmUser.getId());
		        roleId = crmUser.getRole();
		    }
		}

		return user != null;
	}

	/**
	 * @see org.apache.wicket.authentication.AuthenticatedWebSession#getRoles()
	 */
	@Override
	public Roles getRoles()
	{
		if (isSignedIn())
		{
			// If the user is signed in, they have these roles
			return new Roles(Roles.ADMIN);
		}
		return null;
	}

	/**
	 * @return User
	 */
	public String getUser()
	{
		return user;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

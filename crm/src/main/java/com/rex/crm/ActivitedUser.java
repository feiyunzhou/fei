package com.rex.crm;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.StringValue;

import com.google.common.collect.Maps;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.common.ErrorPromptPage;
import com.rex.crm.db.DAOImpl;

public class ActivitedUser extends WebPage{

	private static final long serialVersionUID = 7417410502168544421L;
	private static final Logger logger = Logger.getLogger(UpdateSignPassword.class);
	final Map<String, IModel> models = Maps.newHashMap();
	public ActivitedUser(){
		String loginName = this.getRequest().getRequestParameters().getParameterValue("loginName").toString();
		logger.debug("loginName:"+loginName);
		CRMUser crmuser = DAOImpl.getUserByActivation(loginName);
		if(crmuser.getIsActivited()==0){
			initPage(loginName);
		}else{
			setResponsePage(new ErrorPromptPage());
		}
	}
	public void initPage(final String loginName){
		logger.debug("init");
		CRMUser crmuser = DAOImpl.getUserByActivation(loginName);
		final int userId = crmuser.getId();
		//此时获取到对象，接收客户端的数据
		final  Label promptLabel = new Label("prompt","操作失败重新输入！");
		Form form = new Form("form"){
			@Override
			protected void onSubmit() {
				String password =  models.get("password").getObject() == null? null:String.valueOf(models.get("password").getObject());
				logger.debug("pwd:"+password);
				if(DAOImpl.updateCrmUserPassword(userId, password)){
					//修改crmuser的激活状态为已激活
					DAOImpl.updateUserActivited(userId);
					//用此用户登录
					SignIn2Session session = getMysession();
		        	session.setUser(null);
		            if (session.signIn(loginName, password))
		            {
	                    setResponsePage(getApplication().getHomePage());
		            }else{
		            	promptLabel.add(new AttributeAppender("style",new Model("display:block;"),";"));
		            }
				}else{
					promptLabel.add(new AttributeAppender("style",new Model("display:block;"),";"));
				}
			}
		};
		add(form);
		add(promptLabel);
		form.add(new Label("userName",crmuser.getName()));
		IModel<String> textModel = new Model<String>("");
		PasswordTextField password = new PasswordTextField("password",textModel);
		password.add(new AttributeAppender("value",""));
		models.put("password",textModel);
		form.add(password);
	}
	private SignIn2Session getMysession()
	{
		return (SignIn2Session)getSession();
	}
}

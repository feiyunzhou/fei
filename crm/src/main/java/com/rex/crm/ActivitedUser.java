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
		logger.debug("activitedUser");
		String activitedCode = this.getRequest().getRequestParameters().getParameterValue("activitedCode").toString();
		logger.debug("loginName:"+activitedCode);
		String [] strs=activitedCode.split("_");
		logger.debug("str:"+strs[0]);
		long createTime = Long.parseLong(strs[0]);
		int userID =Integer.parseInt(strs[1]);
		CRMUser crmuser = DAOImpl.getUserByActivation(userID,createTime);
		if(crmuser.getIsActivited()==0){
			initPage(userID,createTime);
		}else{
			setResponsePage(new ErrorPromptPage());
		}
	}
	public void initPage(final int userID,final long createTime){
		logger.debug("init");
		CRMUser crmuser = DAOImpl.getUserByActivation(userID,createTime);;
		final int userId = crmuser.getId();
		final String userLoginName = crmuser.getLoginName();
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
		            if (session.signIn(userLoginName, password))
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
		form.add(new Label("loginName",crmuser.getLoginName()));
		PasswordTextField password = new PasswordTextField("password",textModel);
		models.put("password",textModel);
		form.add(password);
		IModel<String> textModelPassword = new Model<String>("");
		PasswordTextField newPassword = new PasswordTextField("newPassword",textModel);
		models.put("newPassword",textModelPassword);
		form.add(newPassword);
	}
	private SignIn2Session getMysession()
	{
		return (SignIn2Session)getSession();
	}
}

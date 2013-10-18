package com.rex.crm;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.eclipse.jetty.util.security.Credential.MD5;

import com.google.common.collect.Maps;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.db.DAOImpl;

public class UpdateSignPassword extends TemplatePage{
	private static final long serialVersionUID = -317099683219015628L;
	private static final Logger logger = Logger.getLogger(UpdateSignPassword.class);
	final Map<String, IModel> models = Maps.newHashMap();
	public UpdateSignPassword(){
		initPage();
	}
	public  void initPage(){
		//获取到登录对象，将用户名传入页面，然后如何获取
		final int userId =Integer.parseInt(((SignIn2Session)getSession()).getUserId());
		CRMUser user = DAOImpl.getCRMUserInfoById(userId);
		Form form = new Form("form"){
			@Override
			protected void onSubmit() {
				//首先获取form表单数据。验证旧密码是否正确。然后修改密码
				String oldPassword =  models.get("oldPassword").getObject() == null? null:String.valueOf(models.get("oldPassword").getObject());
				String newPassword =  models.get("newPassword").getObject() == null? null:String.valueOf(models.get("newPassword").getObject());
				CRMUser crmUser = DAOImpl.getCRMUserInfoById(Integer.parseInt(((SignIn2Session)getSession()).getUserId()));
				logger.debug("oldPwd:"+oldPassword);
				logger.debug("old:"+DigestUtils.md5Hex(oldPassword));
				logger.debug("password:"+crmUser.getPassword());
				//判断旧密码是否正确,修改密码
				if(DigestUtils.md5Hex(oldPassword).equals(crmUser.getPassword())){
					logger.debug("true");
					//修改密码
					if(DAOImpl.updateCrmUserPassword(userId, newPassword)){
						/*Label promptTextTrue = new Label("prompt","密码修改成功！");
						add(promptTextTrue);*/
					};
				}else{
					logger.debug("false");
					/*Label promptTextFalse = new Label("prompt","旧密码错误，请重新输入！");
					add(promptTextFalse);*/
				}
			};
		};
		add(form);
		Label promptTextFalse = new Label("prompt","旧密码错误，请重新输入！");
		add(promptTextFalse);
		form.add(new Label("userName",user.getName()));
		IModel<String> textModel = new Model<String>("");
		PasswordTextField oldPassword = new PasswordTextField("oldPassword",textModel);
		oldPassword.add(new AttributeAppender("value",""));
		models.put("oldPassword",textModel);
		IModel<String> newPasswordModel = new Model<String>("");
		PasswordTextField newPassword = new PasswordTextField("newPassword", newPasswordModel);
		newPassword.add(new AttributeAppender("value",""));
		models.put("newPassword",newPasswordModel);
		IModel<String> checkNewPasswordModel = new Model<String>("");
		PasswordTextField checkPassword = new PasswordTextField("checkNewPassword",checkNewPasswordModel);
		checkPassword.add(new AttributeAppender("value",""));
		models.put("checkNewPassword",checkNewPasswordModel);
		form.add(oldPassword);
		form.add(newPassword);
		form.add(checkPassword);
	}

}

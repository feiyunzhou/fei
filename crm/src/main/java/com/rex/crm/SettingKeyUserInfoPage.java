package com.rex.crm;


import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Maps;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.db.DAOImpl;

public class SettingKeyUserInfoPage extends WebPage{
	private static final long serialVersionUID = -3798004593574047292L;
	private static final Logger logger = Logger.getLogger(SettingKeyUserInfoPage.class);
	final Map<String, IModel> models = Maps.newHashMap();
	
	public SettingKeyUserInfoPage(UserInfo userInfo){
	    final int userId = userInfo.getId();
	    final int numOfSign = userInfo.getNum_of_signIn();
		Form form = new Form("form"){
			@Override
			protected void onSubmit() {
				String phone = models.get("phone").getObject() == null ? null : String.valueOf(models.get("phone").getObject());
                String email = models.get("email").getObject() == null ? null : String.valueOf(models.get("email").getObject());
                String password = models.get("newPassword").getObject() == null ? null : String.valueOf(models.get("newPassword").getObject());
                //修改用户关键信息，并增加登录次数
                if(DAOImpl.updateKeyUserInfoMessage(phone, email, password,userId)){
                	setResponsePage(getApplication().getHomePage());
                	DAOImpl.addSignInNumber(userId,numOfSign+1);
                }
			}
		};
		Model<String> textModel = new Model<String>("");
		TextField<String> phone = new TextField<String>("phone", textModel);
		phone.add(new AttributeAppender("class","required-field"));
		phone.add(new AttributeModifier("pattern", new Model("^((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)")));
		models.put("phone",textModel);
		Model<String> emailModel = new Model<String>(""); 
		TextField<String> email = new TextField<String>("email", emailModel);
		email.add(new AttributeAppender("class","required-field"));
		email.add(new AttributeModifier("type", new Model("email")));
		models.put("email", emailModel);
        IModel<String> newPasswordModel = new Model<String>("");
        PasswordTextField newPassword = new PasswordTextField("newPassword", newPasswordModel);
        newPassword.add(new AttributeAppender("value", ""));
        newPassword.add(new AttributeAppender("class","required-field"));
        models.put("newPassword", newPasswordModel);
        IModel<String> checkNewPasswordModel = new Model<String>("");
        PasswordTextField checkPassword = new PasswordTextField("checkNewPassword", checkNewPasswordModel);
        checkPassword.add(new AttributeAppender("value", ""));
        checkPassword.add(new AttributeAppender("class","required-field"));
        models.put("checkNewPassword", checkNewPasswordModel);
        form.add(new Label("userName",userInfo.getName()));
        form.add(phone);
        form.add(email);
        form.add(newPassword);
        form.add(checkPassword);
        add(form);
	}

}

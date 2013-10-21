package com.rex.crm.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.rex.crm.AccountPage;
import com.rex.crm.ActivityPage;
import com.rex.crm.ContactPage;
import com.rex.crm.CreateEventPage;
import com.rex.crm.UserPage;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;
import com.rex.crm.util.SMTPAuthenticator;
import com.sun.mail.smtp.SMTPTransport;

public class CRUDPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;

    public enum Permissions {
        ADD, DEL, EDIT,NONE,RESETPWD;

    }

    public CRUDPanel(   final String id, final String entityName,final String entityId,EnumSet<Permissions> userPerms) {
    	super(id);
        Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get(entityName);
        
        if (userPerms == null || userPerms.size() == 0) {
            add(new Fragment("addCon","emptyFragment",this));
            add(new Fragment("delCon","emptyFragment",this));
            add(new Fragment("editCon","emptyFragment",this));
        	add(new Fragment("resetPwdCon","emptyFragment",this));
        } else {
            
            if (userPerms.contains(Permissions.ADD)) {
               Fragment addfrag = new Fragment("addCon","addFragment",this);
               addfrag.add(new Link("create_new_data_btn") {

                   @Override
                   public void onClick() {
                	   if(entity.getName().equals("activity")){
            	    	   setResponsePage(new CreateEventPage());
            	       }else{
            	    	   setResponsePage(new CreateDataPage(entity.getName()));            	    	   
            	       }
                   }
               });
                add(addfrag);
            }else{
                add(new Fragment("addCon","emptyFragment",this));
            }
            
            if (userPerms.contains(Permissions.DEL)) {
            	Fragment delfrag = new Fragment("delCon","delFragment",this);
            	delfrag.add(new Link("del_data_btn") {

                    @Override
                    public void onClick() {
                    	if(entityName.equals("account")){
                        	DAOImpl.deleteRecord(entityId, entityName);
                            setResponsePage(new AccountPage());
                    	}else if(entityName.equals("contact")){
                    		DAOImpl.deleteRecord(entityId, entityName);
                            setResponsePage(new ContactPage());
                    	}else if(entityName.equals("activity")) {
                    		DAOImpl.deleteRecord(entityId, entityName);
                            setResponsePage(new ActivityPage());
                    	}else if(entityName.equalsIgnoreCase("crmuser")){
                    		if(DAOImpl.deleteRecord(entityId, entityName)>0){
                    		   DAOImpl.updateCrmUserReport(entityId, "-1");
                    		}
                            setResponsePage(new UserPage());
                    	}
                        
                    }
                });
                 add(delfrag);
            }else{
                add(new Fragment("delCon","emptyFragment",this));
            }
            
            if (userPerms.contains(Permissions.EDIT)) {
            	Fragment editfrag = new Fragment("editCon","editFragment",this);
            	editfrag.addOrReplace(new Link("edit_data_btn") {
            		
                    @Override
                    public void onClick() {
                    	
                        setResponsePage(new EditDataPage(entity.getName(),entityId));
                    }
                });
            	addOrReplace(editfrag);
            }else{
                addOrReplace(new Fragment("editCon","emptyFragment",this));
            }
            //判断如果entityName为crmuser,则添加重置密码按钮
            	if (userPerms.contains(Permissions.RESETPWD)) {
            		if(entityName.equals("crmuser")){
	                	Fragment editfrag = new Fragment("resetPwdCon","resetPwdFragment",this);
	                	editfrag.add(new Link("resetPwd_data_btn") {
	                        @Override
	                        public void onClick() {
	                        	int userId = Integer.parseInt(entityId);
	                        	//重置密码设置密码为null,发送邮件设置
	                        	if(DAOImpl.resetUserPassword(userId)>0){
	                        		//获取对象
		                        	CRMUser crmuser = DAOImpl.getCrmUserById(userId);
		                        	//发送邮件,判断成功与否
		                        	if(sendMail(crmuser.getLoginName(),"brenda.yuan@rexen.com.cn")){
		                        		//promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
		                        		setResponsePage(new UserPage());
		                        	};
		                        	/*if(sendMail(crmuser.getLoginName(),crmuser.getEmail())){
		                        		//promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
		                        		setResponsePage(new UserPage());
		                        	};*/
	                        	};
	                        }
	                    });
	                	add(editfrag);
            		}else{
                        add(new Fragment("resetPwdCon","emptyFragment",this));
                    }
                }else{
                    add(new Fragment("resetPwdCon","emptyFragment",this));
                }
        }
    }
  //发送有邮件方法
  	public boolean sendMail(String getUserByLoginName,String sendEmail){
  		Session sendMailSession = null;
          SMTPTransport transport = null;
          StringBuilder emailContent = new StringBuilder("请点击连接设置密码:");
          Properties systemPeroperties = new Properties();
          try {
          	systemPeroperties.load(NewDataFormPanel.class.getResourceAsStream("/system.properties"));
  		} catch (FileNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		} catch (IOException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
          emailContent.append(systemPeroperties.getProperty("http"));
          emailContent.append(systemPeroperties.getProperty("url"));
          emailContent.append("/");
          emailContent.append(systemPeroperties.getProperty("project"));
          emailContent.append("/");
          emailContent.append(systemPeroperties.getProperty("jumpage"));
          emailContent.append("?");
          emailContent.append(systemPeroperties.getProperty("parameter"));
          emailContent.append("=");
          emailContent.append(getUserByLoginName);
          Properties props = new Properties();
          // 与服务器建立Session的参数设置
          props.put("mail.smtp.host", "smtp.163.com"); // 写上你的SMTP服务器。
          props.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证。
          SMTPAuthenticator auth = new SMTPAuthenticator("accpcui@163.com", "992041099"); // 用户名，密码。
          sendMailSession = Session.getInstance(props, auth); // 建立连接。
          // SMTPTransport用来发送邮件。
          try {
  			transport = (SMTPTransport) sendMailSession.getTransport("smtp");
  			transport.connect();
  	        // 创建邮件。
  	        Message newMessage = new MimeMessage(sendMailSession);
  	        newMessage.setFrom(new InternetAddress("accpcui@163.com"));
  	        newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendEmail));
  	        newMessage.setSubject("用户重置密码！");
  	        newMessage.setSentDate(new Date());
  	        newMessage.setText(emailContent.toString());
  	        Transport.send(newMessage);
  	        transport.close();  
  	        return  true;  
  		} catch (Exception e) {
  			System.err.println("邮件发送失败！"+e);  
            return  false; 
  		}
  	}
}

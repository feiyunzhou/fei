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
import com.rex.crm.util.SendEmail;
import com.sun.mail.smtp.SMTPTransport;

public class CRUDPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;

    public enum Permissions {
        ADD, DEL, EDIT,NONE,RESETPWD,DONE,DOWNLOAD,UPLOAD;

    }

    public CRUDPanel(final String id, final String entityName,
            final String entityId,EnumSet<Permissions> userPerms, final ICRUDActionListener listener) {
    	super(id);
        Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get(entityName);
        
        if (userPerms == null || userPerms.size() == 0) {
            add(new Fragment("addCon","emptyFragment",this));
            add(new Fragment("delCon","emptyFragment",this));
            add(new Fragment("editCon","emptyFragment",this));
        	  add(new Fragment("resetPwdCon","emptyFragment",this));
        	  add(new Fragment("doneCon","emptyFragment",this));
        	  add(new Fragment("downloadCon","emptyFragment",this));
        	 //add(new Fragment("uploadCon","emptyFragment",this));
        } else {
            
            if (userPerms.contains(Permissions.ADD)) {
               Fragment addfrag = new Fragment("addCon","addFragment",this);
               addfrag.add(new Link("create_new_data_btn") {

                   @Override
                   public void onClick() {
                	  
                	   listener.create();
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
                    	
                    	listener.delete();
                    	
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
                        listener.update();
                        //setResponsePage(new EditDataPage(entity.getName(),entityId));
                    }
                });
            	addOrReplace(editfrag);
            }else{
                addOrReplace(new Fragment("editCon","emptyFragment",this));
            }
            //判断如果entityName为crmuser,则添加重置密码按钮
            	if (userPerms.contains(Permissions.RESETPWD)) {
            		if(entityName.equals("userInfo")){
	                	Fragment editfrag = new Fragment("resetPwdCon","resetPwdFragment",this);
	                	editfrag.add(new Link("resetPwd_data_btn") {
	                        @Override
	                        public void onClick() {
	                        	int userId = Integer.parseInt(entityId);
	                        	//重置密码设置密码为null,发送邮件设置
	                        	 listener.resetPassword(userId);
	                        }
	                    });
	                	add(editfrag);
            		}else{
                        add(new Fragment("resetPwdCon","emptyFragment",this));
                    }
                }else{
                    add(new Fragment("resetPwdCon","emptyFragment",this));
                }
            	if (userPerms.contains(Permissions.DONE)) {
                Fragment addfrag = new Fragment("doneCon","doneFragment",this);
                addfrag.add(new Link("done_data_btn") {

                    @Override
                    public void onClick() {
                     
                      listener.doneBtn();
                    }
                });
                 add(addfrag);
             }else{
                 add(new Fragment("doneCon","emptyFragment",this));
             }
            	
            	if (userPerms.contains(Permissions.DOWNLOAD)) {
            	  //downLoad_account_btn
                Fragment downLoadfrag = new Fragment("downloadCon","downLoadFragment",this);
                downLoadfrag.add(new Link("downLoad_account_btn") {

                    @Override
                    public void onClick() {
                     
                      try
                      {
                        listener.downLoadBtn();
                      }
                      catch (Exception e)
                      {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                      }
                    }
                });
                 add(downLoadfrag);
             }else{
                 add(new Fragment("downloadCon","emptyFragment",this));
             }
        }
    }

}

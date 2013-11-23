package com.rex.crm.common;

import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.Map;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.rex.crm.db.DAOImpl;
import com.rex.crm.db.model.Activity;
import com.rex.crm.util.Configuration;

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
            	Link link = new Link("edit_data_btn") {
            		
                    @Override
                    public void onClick() {
                        listener.update();
                        //setResponsePage(new EditDataPage(entity.getName(),entityId));
                    }
                };
            	editfrag.addOrReplace(link);
            	//根据entityId获取对象，获取开始时间，然后判断时间是否未来时则隐藏
				if(entityName.equals("activity")||entityName.equals("coaching")||entityName.equals("willCoaching")){
					Activity activity = DAOImpl.getActivityById(Integer.parseInt(entityId));
	                Date newDate = new Date();
	                Calendar cal = Calendar.getInstance();
	                cal.setTime(newDate);
	                cal.set(Calendar.DATE,cal.get(Calendar.DATE)-7);
	                newDate = cal.getTime();
	                Date startTime = new Date(activity.getStarttime());
	                if(startTime.compareTo(newDate)<0){
	                	editfrag.add(new AttributeAppender("style",new Model("display:none")," "));
	                }
                }
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
                Link link = new Link("done_data_btn") {

                    @Override
                    public void onClick() {
                     
                      listener.doneBtn();
                    }
                };
                addfrag.add(link);
                //根据entityId获取对象，获取开始时间，然后判断时间是否未来时则隐藏
				if(entityName.equals("activity")||entityName.equals("coaching")||entityName.equals("willCoaching")){
					Activity activity = DAOImpl.getActivityById(Integer.parseInt(entityId));
	                Date newDate = new Date();
	                Date startTime = new Date(activity.getStarttime());
	                if(startTime.compareTo(newDate)>=0){
	                	addfrag.add(new AttributeAppender("style",new Model("display:none")," "));
	                }
                }
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

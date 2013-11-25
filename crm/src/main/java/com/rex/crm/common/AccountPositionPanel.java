package com.rex.crm.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.SearchCRMUserPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.admin.PositionTreePage;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.common.tree.Node;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class AccountPositionPanel extends Panel {
    private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(AccountPositionPanel.class);
    private String etId;
    private String currentEntityName;
    List<String> selectedRowIds = Lists.newArrayList();
      
    
    public AccountPositionPanel(String id,final String en,final String entityId,final int level) {
        super(id);
        etId = entityId;
        currentEntityName = en;
        final int roleId = ((SignIn2Session)getSession()).getRoleId();
        final String userId = ((SignIn2Session)getSession()).getUserId();
        //team sql
        String teamSql = "";
                //for the 医院列表

              if(level == 11){
            	  teamSql="select id as rid, user_position_query.* from user_position_query where user_position_query.position_id = ?";
              } else if(level == 21){
            	  teamSql="select id as rid, user_position_query.* from user_position_query where user_position_query.manager_position_id =?";
              }else{
            	  teamSql="select * from user_position_query where manager_position_id in (select id from crmuser where reportto = ?)";
              }
        

        List mapList = DAOImpl.queryEntityRelationList(teamSql, entityId);
        Entity entity=null ;
        	  entity = Configuration.getEntityByName("regionManage");
        	  add(new Label("title","区域管理 "));
        
        List<Field> fields = entity.getFields();
        final String entityName = entity.getName();
        Form form = new Form("form");
        add(form);
        if(roleId != 1){
          WebMarkupContainer con = new WebMarkupContainer("remove_team_member_click");
            add(con);
            con.setVisible(false);
            //con.add(new AttributeAppender("style", new Model("display:none;"), ";"));
       
        }else{
        
           add(new SubmitLink("remove_team_member_click",form){
           @Override      
           public void onSubmit(){

             try{
            	
            	 delete(Integer.parseInt(entityId));
                
             }catch(Exception e){
                 
         }   
             PageParameters param = new PageParameters();
             param.add("positionId", entityId);
             param.add("level",String.valueOf(level));
			 setResponsePage(PositionTreePage.class,param );
            
             }
           });
       }
  
        //add button submission
        if(roleId != 1){
        	 WebMarkupContainer con = new WebMarkupContainer("add_users_link");
             add(con);
             con.setVisible(false);
        }else{
        	add(new Link<Void>("add_users_link"){

                @Override
                public void onClick() {
                  this.setResponsePage(new SearchCRMUserPage(currentEntityName,entityId,userId,0));
                }
                
            });

        }

        //set column name
        RepeatingView columnNameRepeater = new RepeatingView("columnNameRepeater");
        form.add(columnNameRepeater);
        int count=0;
        for(Field f:entity.getFields()){
            if (!f.isVisible()|| f.getPriority() >1)
                continue;
            AbstractItem item = new AbstractItem(columnNameRepeater.newChildId());
            if(count==0){
                item.add(new AttributeAppender("class", new Model("table-first-link"), " "));
                count++;
            }
            columnNameRepeater.add(item);
            item.add(new Label("columnName", f.getDisplay())); 
        }
        RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
        form.add(dataRowRepeater);
        for (int i = 0; i < mapList.size(); i++)
        {
            Map map = (Map)mapList.get(i);
            final String rowId =  String.valueOf(map.get("rid"));     
            final String realId =  String.valueOf(map.get("id"));  
            AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
            dataRowRepeater.add(item);
            RepeatingView columnRepeater = new RepeatingView("columnRepeater");
            item.add(columnRepeater);
            for (Field f : fields) {
                if (!f.isVisible() || f.getPriority() >1)
                    continue;
                AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model() {
                    @Override
                    public Serializable getObject() {
                        Param p = new Param();
                        p.setId(rowId);
                        p.setExtraId(realId);
                        p.setEntityName(entityName);
                        return p;
                       
                    }
                });
                if (f.isDetailLink()) {
                    String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                    if(value.equals("null")||value.equals("")||value.equals("dummy")){
                      value = "无";
                    }
                    columnitem.add(new AttributeAppender("class", new Model("table-first-link"), " "));
                    columnitem.add(new DetailLinkFragment("celldata","detailFragment",this,value));
                } else {
                    if (f.getPicklist() != null) {
                        // get option from picklist
                        String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryPickListByIdCached(f.getPicklist(), String.valueOf(map.get(f.getName()))));
                        if(value.equals("null")||value.equals("")||value.equals("dummy")){
                          value = "无";
                        }
                        columnitem.add(new Label("celldata", value));
                    } else if(f.getRelationTable() != null){
                        String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName()))));
                        if(value.equals("null")||value.equals("")||value.equals("dummy")){
                          value = "无";
                        }
                        columnitem.add(new Label("celldata", value));
                    }else {
                        String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                        if(value.equals("null")||value.equals("")||value.equals("dummy")){
                          value = "无";
                        }
                        columnitem.add(new Label("celldata", value));
                    }
                }
                columnRepeater.add(columnitem);
              } 
            
        }
        add(new NewDataFormPanel("formPanel",entity,null));
    }

    public AccountPositionPanel(String id, IModel<?> model) {
        super(id, model);
    }
    
    private class DetailLinkFragment extends Fragment
    {
        public DetailLinkFragment(String id, String markupId, MarkupContainer markupProvider,String caption)
        {
            super(id, markupId, markupProvider);
            add(new Link("detailclick")
            {
                
                @Override
                public void onClick()
                {
                    Param p = (Param)getParent().getParent().getDefaultModelObject();
                    setResponsePage(new EntityDetailPage(p.getEntityName(),p.getExtraId()));
                    
                }
            }.add(new Label("caption", new Model<String>(caption))));
        }
    }
    
    private class checkboxFragment extends Fragment {

      public checkboxFragment(String id, String markupId, MarkupContainer markupProvider,Model Imodel)
      {
        super(id, markupId, markupProvider);
        // TODO Auto-generated constructor stub
        add(new Check("checkbox",Imodel));
      }
      
    }
    
    private int queryPositionLevel(int positionId)
    {
    	int level = 100;
    	
    	Map position = DAOImpl.queryEntityById("select * from crmuser where id = ?", String.valueOf(positionId));
    	if(position.size() > 0)
    	{
    		level = (int) position.get("level");
    	}
    	
    	return level;
    }
    
    private ArrayList<Integer> queryPositionByParent(int positionId)
    {
    	List list = DAOImpl.searchCRMUserByManager(String.valueOf(positionId), "");
    	ArrayList<Integer> ids = new ArrayList<>();
    	for(Object o : list)
    	{
    		Map map = (Map) o;
    		ids.add(Integer.valueOf((Integer)map.get("id")));
    	}
    	
    	return ids;
    }
    
    private void delete(int positionId)
    {
    	int level = queryPositionLevel(positionId);
    	
    	if(level == 1)
    	{
    		DAOImpl.deleteAccountTeamWithPositionId(positionId);
    	}
    	else if(level > 1)
    	{
    		ArrayList<Integer> ids = queryPositionByParent(positionId);
    		for(int id : ids)
    		{
    			delete(id);
    		}
    	}
    }
}

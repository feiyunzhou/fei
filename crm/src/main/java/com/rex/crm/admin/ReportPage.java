package com.rex.crm.admin;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;
import com.rex.crm.AccountPage;
import com.rex.crm.SignIn2Session;

/**
 * @author Feiyun Zhou
 */
public class ReportPage extends AdminTemplatePage
{
  private String search_target = "";

  /**
   * Constructor
   */
  public ReportPage()
  {
	  initPage(null, null);
  }

  public ReportPage(final Map<String, Boolean> filter, List tdata)
  {
	  initPage(filter, tdata);
  }

  public void initPage(final Map<String, Boolean> filter, List tdata)
  {
    Map<String, Entity> entities = Configuration.getEntityTable();
    //final Entity entity = entities.get("callreport");
    final Entity entity = entities.get("user_position_account");

    final int roleId = ((SignIn2Session) getSession()).getRoleId();
    final String userId = ((SignIn2Session) getSession()).getUserId();
    setPageTitle("系统管理-报表"); 
    
    
    Form form = new Form("form")
    {

      @Override
      protected void onSubmit()
      {
        String sql = entity.getSql();
        switch (roleId)
        {
          case UserRole.USER_ROLE_ADMINISTRATOR:
          sql = entity.getSqlAdmin();
          break;
          case UserRole.USER_ROLE_MANAGER:
          sql = entity.getSqlManager();
          break;
          case UserRole.USER_ROLE_SALES:
          sql = entity.getSql();
          break;
        }

        search_target = (search_target == null || search_target.equalsIgnoreCase("*")) ? "" : search_target;

        // sql = sql + " AND name like '%"+search_target+"%'";
        List<Field> searchableFields = entity.getSearchableFields();
        String joint = " like '%" + search_target + "%'";
        String likequery = "";
        for (Field sf : searchableFields)
        {
          likequery = likequery + " OR " + sf.getName() + joint;
        }
        
        sql = sql + " where userName like '%" + search_target + "%' " + likequery;
        List datalist = null;
        switch (roleId)
        {
          case UserRole.USER_ROLE_ADMINISTRATOR:
          datalist = DAOImpl.queryEntityRelationList(sql);
          break;
          case UserRole.USER_ROLE_MANAGER:
           datalist = DAOImpl.queryEntityRelationList(sql,userId);
          break;
          case UserRole.USER_ROLE_SALES:
          datalist = DAOImpl.queryEntityRelationList(sql, userId);
          break;
        }
        
//        List datalist = DAOImpl.queryEntityRelationList(sql, "dummy");
       // setResponsePage(new UserPage(filter, datalist));
        setResponsePage(new ReportPage(filter, datalist));

      }

    };
    add(form);

    TextField search_input = new TextField("search_input", new PropertyModel(this, "search_target"));
    form.add(search_input);

    String sql = entity.getSql();
    switch (roleId)
    {
      case UserRole.USER_ROLE_ADMINISTRATOR:
      sql = entity.getSqlAdmin();
      break;
      case UserRole.USER_ROLE_MANAGER:
      sql = entity.getSqlManager();
      break;
      case UserRole.USER_ROLE_SALES:
      sql = entity.getSql();
      break;
    }
    // List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
    if (tdata == null || tdata.size() == 0)
    {
      switch (roleId)
      {
        case UserRole.USER_ROLE_ADMINISTRATOR:
        tdata = DAOImpl.queryEntityRelationList(sql);
        break;
        case UserRole.USER_ROLE_MANAGER:
        tdata = DAOImpl.queryEntityRelationList(sql, userId);
        break;
        case UserRole.USER_ROLE_SALES:
        tdata = DAOImpl.queryEntityRelationList(sql, userId);
        break;
      }
      
      if (filter == null)
      {
        switch (roleId)
        {
          case UserRole.USER_ROLE_ADMINISTRATOR:
          tdata = DAOImpl.queryEntityRelationList(sql);
          break;
          case UserRole.USER_ROLE_MANAGER:
          tdata = DAOImpl.queryEntityRelationList(sql, userId );
          break;
          case UserRole.USER_ROLE_SALES:
          tdata = DAOImpl.queryEntityRelationList(sql, userId);
          break;
        }

      }
      else
      {
        List<String> ft = Lists.newArrayList();
        for (String k : filter.keySet())
        {
          if (filter.get(k))
            ft.add(k);
        }
        
        
        switch (roleId)
        {
          case UserRole.USER_ROLE_ADMINISTRATOR:
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft);
          break;
          case UserRole.USER_ROLE_MANAGER:
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, userId);
          break;
          case UserRole.USER_ROLE_SALES:
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft,  userId);
          break;
        }
        
      }
    }
    add(new PageableTablePanel("datalist", entity, tdata, null));

  }
}
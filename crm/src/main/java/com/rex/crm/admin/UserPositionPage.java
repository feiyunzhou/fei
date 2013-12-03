package com.rex.crm.admin;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.rex.crm.beans.Choice;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;
import com.rex.crm.SignIn2Session;

/**
 * @author Feiyun Zhou
 */
public class UserPositionPage extends AdminTemplatePage
{
  private String search_target = "";

  /**
   * Constructor
   */
  public UserPositionPage()
  {
    initPage(null, null);
  }

  public UserPositionPage(final Map<String, Boolean> filter, List tdata)
  {
    initPage(filter, tdata);
  }

  public void initPage(final Map<String, Boolean> filter, List tdata)
  {
    Map<String, Entity> entities = Configuration.getEntityTable();
    final Entity entity = entities.get("user_position");
    final int roleId = ((SignIn2Session) getSession()).getRoleId();
    final String positionId = ((SignIn2Session) getSession()).getPositionId();
    setPageTitle(entity.getDisplay());
    
    
    Form form = new Form("form")
    {

      @Override
      protected void onSubmit()
      {
        String sql = entity.getSql();
        switch (roleId)
        {
          case UserRole.USER_ROLE_ADMINISTRATOR:
          sql = entity.getSql();
          break;
          case UserRole.USER_ROLE_MANAGER:
          sql = entity.getSql();
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
        
        sql = sql + " where name like '%" + search_target + "%' " + likequery;
        List datalist = null;
        switch (roleId)
        {
          case UserRole.USER_ROLE_ADMINISTRATOR:
          datalist = DAOImpl.queryEntityRelationList(sql);
          break;
          case UserRole.USER_ROLE_MANAGER:
           datalist = DAOImpl.queryEntityRelationList(sql,positionId,positionId);
          break;
          case UserRole.USER_ROLE_SALES:
          datalist = DAOImpl.queryEntityRelationList(sql, positionId);
          break;
        }
        
//        List datalist = DAOImpl.queryEntityRelationList(sql, "dummy");
        setResponsePage(new PositionPage(filter, datalist));

      }

    };
    add(form);

    TextField search_input = new TextField("search_input", new PropertyModel(this, "search_target"));
    form.add(search_input);

    String sql = entity.getSql();
    switch (roleId)
    {
      case UserRole.USER_ROLE_ADMINISTRATOR:
      sql = entity.getSql();
      break;
      case UserRole.USER_ROLE_MANAGER:
      sql = entity.getSql();
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
        tdata = DAOImpl.queryEntityRelationList(sql,positionId,positionId);
        break;
        case UserRole.USER_ROLE_SALES:
        tdata = DAOImpl.queryEntityRelationList(sql, positionId);
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
          tdata = DAOImpl.queryEntityRelationList(sql,positionId,positionId);
          break;
          case UserRole.USER_ROLE_SALES:
          tdata = DAOImpl.queryEntityRelationList(sql, positionId);
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
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, positionId,positionId);
          break;
          case UserRole.USER_ROLE_SALES:
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft,  positionId);
          break;
        }
        
      }

    }
    add(new PageableTablePanel("datalist", entity, tdata, null));

//    List<Choice> choices = DAOImpl.queryPickList(entity.getFieldByName(entity.getFilterField()).getPicklist());
//    add(new FilterPanel("filterPanel", choices, filter, PositionPage.class,entity));

  }
}
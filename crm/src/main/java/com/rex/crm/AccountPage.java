package com.rex.crm;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.util.string.StringValue;

import com.google.common.collect.Lists;
import com.rex.crm.beans.AdvancedSearchFilter;
import com.rex.crm.beans.Choice;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.advancedSearch.AdvancedSearchPage;
import com.rex.crm.common.advancedSearch.AdvancedSearchPanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;

/**
 * @author Feiyun Zhou
 */
public class AccountPage extends TemplatePage
{
    private static final Logger logger = Logger.getLogger(AccountPage.class);
  private String search_target = "";

  /**
   * Constructor
   */
  public AccountPage()
  {
    //PageParameters pp = new PageParameters();
    //IRequestParameters params = this.getRequestCycle().getRequest().getRequestParameters();
    initPage(null, null);
  }

  public AccountPage(Map<String, Boolean> map, List tdata)
  {
    initPage(map, tdata);
  }
  
  public AccountPage( List tdata)
  {
    initPage(null, tdata);
  }

  private void initPage(final Map<String, Boolean> filter, List tdata)
  {
    Map<String, Entity> entities = Configuration.getEntityTable();
    final Entity entity = entities.get("account");
    setPageTitle(entity.getDisplay());
    // List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
    // TODO get userId from request's session
    final String positionId = ((SignIn2Session) getSession()).getPositionId();
    final int roleId = ((SignIn2Session) getSession()).getRoleId();

    Form form = new Form("form")
    {

      @Override
      protected void onSubmit()
      {
        String sql = entity.getSql();
        switch (roleId)
        {
          case 1:
          sql = entity.getSqlAdmin();
          break;
          case 2:
          sql = entity.getSqlManager();
          break;
          case 3:
          sql = entity.getSql();
          break;
        }

        search_target = (search_target == null || search_target.equalsIgnoreCase("*")) ? "" : search_target;

        List<Field> searchableFields = entity.getSearchableFields();
        String joint = " like '%" + search_target + "%'";
        String likequery = "";
        for (Field sf : searchableFields)
        {
          likequery = likequery + " OR " + sf.getName() + joint;
        } 
        sql = sql + " where name like '%" + search_target + "%' " + likequery;
        System.out.println(sql);
        List datalist = null;

        switch (roleId)
        {
          case UserRole.USER_ROLE_MANAGER:
          datalist = DAOImpl.queryEntityRelationList(sql, positionId, positionId, positionId);
          break;
          case UserRole.USER_ROLE_SALES:
          datalist = DAOImpl.queryEntityRelationList(sql, positionId, positionId);
          break;
          case UserRole.USER_ROLE_ADMINISTRATOR:
          datalist = DAOImpl.queryEntityRelationList(sql);
        }

        setResponsePage(new AccountPage(filter, datalist));

      }

    };
    add(form);
    
    TextField search_input = new TextField("search_input", new PropertyModel(this, "search_target"));
    form.add(search_input);
    form.add(new Link("ad_search_link"){
                @Override
                public void onClick() {
                    setResponsePage(new AdvancedSearchPage(entity.getName(),null));
                    
                }
        
    } );
//    add(new AdvancedPanel("advancedSearch",entity));
    String sql = entity.getSql();
    switch (roleId)
    {
      case 1:
      sql = entity.getSqlAdmin();
      break;
      case 2:
      sql = entity.getSqlManager();
      break;
      case 3:
      sql = entity.getSql();
      break;
    }
    if (tdata == null || tdata.size() == 0)
    {

      if (filter == null)
      {
//        if(params != null){
//           StringValue ad_search_sql_value = params.getParameterValue("ad_search_sql");
//          if( !ad_search_sql_value.isNull() && !ad_search_sql_value.isEmpty()){
//              sql = sql +  " where " + ad_search_sql_value.toString();
//              logger.debug("sql is: " + sql);
//          }
//        }
        switch (roleId)
        {
          case UserRole.USER_ROLE_ADMINISTRATOR:
          tdata = DAOImpl.queryEntityRelationList(sql);
          break;
          case UserRole.USER_ROLE_MANAGER:
          tdata = DAOImpl.queryEntityRelationList(sql, positionId, positionId, positionId);
          break;
          case UserRole.USER_ROLE_SALES:
          tdata = DAOImpl.queryEntityRelationList(sql, positionId, positionId);
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
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, positionId, positionId, positionId);
          break;
          case UserRole.USER_ROLE_SALES:
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, positionId,positionId);
        }

      }
    }
   // add(new AdvancedSearchPanel("advancedSearch","account"));
    
    add(new PageableTablePanel("datalist", entity, tdata, null));

    List<Choice> choices = DAOImpl.queryPickList(entity.getFieldByName(entity.getFilterField()).getPicklist());
    
    add(new FilterPanel("filterPanel", choices, filter, AccountPage.class,entity));
    
   
   

  }

}